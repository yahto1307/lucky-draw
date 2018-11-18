package com.yahto.hydra.lucky.draw.service.impl;

import com.yahto.hydra.lucky.draw.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by yahto on 2018/9/12 11:34 PM
 *
 * @author yahto
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public boolean setPrizeCount(String key, Long itemId, Integer count) {
        return redisTemplate.opsForZSet().add(key, itemId.toString(), count.doubleValue());
    }

    @Override
    public Double countDownPrize(String key, Long itemId, Long countDownNum) {
        return redisTemplate.opsForZSet().incrementScore(key, itemId.toString(), -countDownNum);
    }

    @Override
    public boolean getDistributedLock(String lockString) {
        return false;
    }
}
