package com.yahto.hydra.lucky.draw.prize;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yahto on 2018/9/13 9:46 PM
 *
 * @author yahto
 */
public class PrizePool implements Serializable {
    private static final long serialVersionUID = 4966008716308051094L;

    private Integer total;

    private List<PrizePoolBean> poolBeanList;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<PrizePoolBean> getPoolBeanList() {
        return poolBeanList;
    }

    public void setPoolBeanList(List<PrizePoolBean> poolBeanList) {
        this.poolBeanList = poolBeanList;
    }
}
