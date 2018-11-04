package com.yahto.hydra.lucky.draw.service.impl;

import com.yahto.hydra.lucky.draw.common.exception.BusinessException;
import com.yahto.hydra.lucky.draw.dao.KillItemDao;
import com.yahto.hydra.lucky.draw.dao.KillResultDao;
import com.yahto.hydra.lucky.draw.model.KillItem;
import com.yahto.hydra.lucky.draw.model.KillResult;
import com.yahto.hydra.lucky.draw.service.PrizeKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by yahto on 2018/10/17 11:31 AM
 *
 * @author yahto
 */
@Service("prizeKillByPessimisticLockServiceImpl")
public class PrizeKillByPessimisticLockServiceImpl implements PrizeKillService {
    @Autowired
    private KillItemDao killItemDao;
    @Autowired
    private KillResultDao killResultDao;

    @Override
    public KillResult prizeKill(Long killItemId, Long userId) {
        KillItem killItem = killItemDao.selectByPrimaryKey(killItemId);
        if (killItem == null) {
            throw new BusinessException("商品不存在");
        }
        int result = killItemDao.prizeKill(killItemId);
        KillResult killResult = new KillResult();
        killResult.setCreateAt(new Date());
        killResult.setUpdateAt(new Date());
        killResult.setKillItemId(killItemId);
        killResult.setUserId(userId);
        killResult.setActivityId(killItem.getActivityId());
        if (result < 1) {
            killResult.setIsLucky(0);
        } else {
            killResult.setIsLucky(1);
        }
        killResultDao.insertSelective(killResult);
        return killResult;
    }
}
