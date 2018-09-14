package com.yahto.hydra.lucky.draw.service;

import com.yahto.hydra.lucky.draw.model.DrawResult;

/**
 * Created by yahto on 2018/9/13 9:57 PM
 *
 * @author yantong
 */
public interface LuckyDrawService {

    /**
     * 抽奖接口
     *
     * @param activityId 活动Id
     * @param userId     用户Id
     * @return DrawResult 抽奖结果
     */
    DrawResult luckyDraw(Long activityId, Long userId);

}
