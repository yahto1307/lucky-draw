package com.yahto.hydra.lucky.draw.service;

import com.yahto.hydra.lucky.draw.model.KillResult;

/**
 * Created by yahto on 2018/10/17 11:29 AM
 *
 * @author yantong
 */
public interface PrizeKillService {
    /**
     * 秒杀商品
     *
     * @param killItemId 活动Id
     * @param userId     用户Id
     * @return killResult 秒杀结果
     */
    KillResult prizeKill(Long killItemId, Long userId);
}
