package com.yahto.hydra.lucky.draw.service;

/**
 * Created by yahto on 2018/9/12 11:34 PM
 *
 * @author yantong
 */
public interface RedisService {
    /**
     * redis set key value
     *
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 设置奖品数量
     *
     * @param key    redis key
     * @param itemId 奖品Id
     * @param count  奖品数量
     * @return 是否成功
     */
    boolean setPrizeCount(String key, Long itemId, Integer count);

    /**
     * 减少商品数量
     *
     * @param key redis key
     * @param itemId 奖品Id
     * @param countDownNum 需要减少的数量
     * @return 减少后的剩余数量
     */
    Double countDownPrize(String key, Long itemId, Long countDownNum);
}
