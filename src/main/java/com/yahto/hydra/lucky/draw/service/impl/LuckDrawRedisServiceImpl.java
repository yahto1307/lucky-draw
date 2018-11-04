package com.yahto.hydra.lucky.draw.service.impl;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.yahto.hydra.lucky.draw.common.exception.BusinessException;
import com.yahto.hydra.lucky.draw.dao.ActivityDao;
import com.yahto.hydra.lucky.draw.dao.DrawResultDao;
import com.yahto.hydra.lucky.draw.dao.ItemDao;
import com.yahto.hydra.lucky.draw.model.Activity;
import com.yahto.hydra.lucky.draw.model.DrawResult;
import com.yahto.hydra.lucky.draw.model.Item;
import com.yahto.hydra.lucky.draw.model.ItemExample;
import com.yahto.hydra.lucky.draw.prize.PrizePool;
import com.yahto.hydra.lucky.draw.prize.PrizePoolBean;
import com.yahto.hydra.lucky.draw.prize.PrizePoolInitFactory;
import com.yahto.hydra.lucky.draw.service.LuckyDrawService;
import com.yahto.hydra.lucky.draw.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by yahto on 2018/9/21 9:03 PM
 *
 * @author yahto
 */
@Service("luckDrawRedisServiceImpl")
public class LuckDrawRedisServiceImpl implements LuckyDrawService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private ActivityDao activityDao;

    @Autowired
    private DrawResultDao drawResultDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private PrizePoolInitFactory prizePoolInitFactory;

    @Value("${activity.prefix}")
    private String activityPrefix;

    private volatile static Map<Long, List<Item>> itemListMap = Maps.newHashMap();

    private volatile static Map<Long, PrizePool> prizePoolMap = Maps.newHashMap();

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
    private static ExecutorService executorService = new ThreadPoolExecutor(20, 100,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(100), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    @PostConstruct
    public void init() {
        List<Activity> activityList = activityDao.getActiveActivity();
        ItemExample example = new ItemExample();
        for (Activity activity : activityList) {
            example.createCriteria().andActivityIdEqualTo(activity.getId());
            List<Item> itemList = itemDao.selectByExample(example);
            if (!CollectionUtils.isEmpty(itemList)) {
                itemListMap.put(activity.getId(), itemList);
            }
            example.clear();
        }
        Iterator<Map.Entry<Long, List<Item>>> iterator = itemListMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, List<Item>> entry = iterator.next();
            prizePoolMap.put(entry.getKey(), prizePoolInitFactory.initPrizePool(entry.getValue()));
        }
        for (Map.Entry<Long, PrizePool> entry : prizePoolMap.entrySet()) {
            for (PrizePoolBean prizePoolBean : entry.getValue().getPoolBeanList()) {
                if (!redisService.setPrizeCount(activityPrefix + entry.getKey(), prizePoolBean.getId(), prizePoolBean.getCount())) {
                    throw new BusinessException("初始化redis失败");
                }
            }
        }
    }

    @Override
    public DrawResult luckyDraw(Long activityId, Long userId) {
        PrizePool prizePool = prizePoolMap.get(activityId);
        if (null == prizePool) {
            throw new BusinessException("活动不存在");
        }
        Random random = new Random();
        Integer result = random.nextInt(prizePool.getTotal());
        PrizePoolBean prizePoolBean = judgePrize(result, prizePool.getPoolBeanList());
        if (null == prizePoolBean) {
            throw new BusinessException("系统错误");
        }
        Double leftCount = redisService.countDownPrize(activityPrefix + activityId, prizePoolBean.getId(), 1L);
        DrawResult drawResult = new DrawResult();
        drawResult.setActivityId(activityId);
        drawResult.setCreateAt(new Date());
        drawResult.setUpdateAt(new Date());
        drawResult.setItemId(prizePoolBean.getId());
        drawResult.setUserId(userId);
        if (leftCount >= 0) {
            //减库存成功
            drawResult.setIsLucky(1);
        } else {
            drawResult.setIsLucky(0);
        }
        executorService.execute(() -> {
            drawResultDao.insert(drawResult);
            if (leftCount >= 0) {
                itemDao.reduceItemCount(activityId, prizePoolBean.getId());
            }
        });
        return drawResult;
    }

    private PrizePoolBean judgePrize(Integer result, List<PrizePoolBean> poolBeanList) {
        for (PrizePoolBean prizeBean : poolBeanList) {
            if (result >= prizeBean.getBegin() && result <= prizeBean.getEnd()) {
                return prizeBean;
            }
        }
        return null;
    }
}
