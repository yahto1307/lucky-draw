package com.yahto.hydra.lucky.draw.prize;

import com.yahto.hydra.lucky.draw.model.Item;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yahto on 2018/9/13 9:50 PM
 *
 * @author yahto
 */
@Component
public class PrizePoolInitFactory {
    public PrizePool initPrizePool(List<Item> prizeList) {
        int total = 0;
        List<PrizePoolBean> poolBeanList = new ArrayList<>(prizeList.size());
        for (Item item : prizeList) {
            PrizePoolBean prizePoolBean = new PrizePoolBean();
            prizePoolBean.setId(item.getId());
            prizePoolBean.setBegin(total);
            total += item.getProbability();
            prizePoolBean.setEnd(total);
            prizePoolBean.setCount(item.getTotalCount());
            poolBeanList.add(prizePoolBean);
        }
        PrizePool prizePool = new PrizePool();
        prizePool.setTotal(total);
        prizePool.setPoolBeanList(poolBeanList);
        return prizePool;
    }
}
