package com.yahto.hydra.lucky.draw.service.impl;

import com.yahto.hydra.lucky.draw.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by yahto on 2018/9/12 11:34 PM
 *
 * @author yahto
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
