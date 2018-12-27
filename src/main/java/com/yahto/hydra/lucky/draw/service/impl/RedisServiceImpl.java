package com.yahto.hydra.lucky.draw.service.impl;

import com.google.common.collect.Lists;
import com.yahto.hydra.lucky.draw.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;

import java.util.List;

/**
 * Created by yahto on 2018/9/12 11:34 PM
 *
 * @author yahto
 */
@Service
public class RedisServiceImpl implements RedisService {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisServiceImpl.class);
    @Autowired
    StringRedisTemplate redisTemplate;

    public static final String UNLOCK_LUA;

    static {
        StringBuilder sb = new StringBuilder();
        sb.append("if redis.call(\"get\",KEYS[1]) == ARGV[1] ");
        sb.append("then ");
        sb.append("    return redis.call(\"del\",KEYS[1]) ");
        sb.append("else ");
        sb.append("    return 0 ");
        sb.append("end ");
        UNLOCK_LUA = sb.toString();
    }

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
    public boolean getDistributedLock(String lockKey, String randomValue) {

        try {
            RedisCallback<String> callback = (connection) -> {
                JedisCommands commands = (JedisCommands) connection.getNativeConnection();
                //set key value nx(只在键不存在时，才对键进行设置操作) px(设置键的过期时间为 millisecond 毫秒)
                return commands.set(lockKey, randomValue, "NX", "PX", 5000);
            };
            String result = redisTemplate.execute(callback);
            return !StringUtils.isEmpty(result);
        } catch (Exception e) {
            LOGGER.error("获取分布式锁异常", e);
        }
        return false;
    }

    @Override
    public boolean releaseDistributedLock(String lockKey, String randomValue) {
        List<String> keys = Lists.newArrayList();
        keys.add(lockKey);
        List<String> args = Lists.newArrayList();
        args.add(randomValue);
        try {
            RedisCallback<Long> callback = (connection) -> {
                Object nativeConnection = connection.getNativeConnection();
                // 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
                // 集群模式
                if (nativeConnection instanceof JedisCluster) {
                    return (Long) ((JedisCluster) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                // 单机模式
                else if (nativeConnection instanceof Jedis) {
                    return (Long) ((Jedis) nativeConnection).eval(UNLOCK_LUA, keys, args);
                }
                return 0L;
            };
            Long result = redisTemplate.execute(callback);
            return result != null && result > 0;
        } catch (Exception e) {
            LOGGER.error("释放分布式锁异常", e);
        }
        return false;
    }
}
