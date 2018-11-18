package com.yahto.hydra.lucky.draw.service.impl;

import com.yahto.hydra.lucky.draw.model.KillResult;
import com.yahto.hydra.lucky.draw.service.PrizeKillService;
import com.yahto.hydra.lucky.draw.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by yahto on 2018/11/10 12:03 AM
 *
 * @author yahto
 */
@Service("prizeKillByRedisLockImpl")
public class PrizeKillByRedisLockImpl implements PrizeKillService {
    @Autowired
    private RedisService redisService;

    @Override
    public KillResult prizeKill(Long killItemId, Long userId) {
        try {
            String randomString = UUID.randomUUID().toString();
            if (redisService.getDistributedLock(randomString)) {

            }
        } catch (Exception e) {

        }
        return null;
    }
}
