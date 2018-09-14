package com.yahto.hdyra.lucky.draw.service;

import com.yahto.hydra.lucky.draw.service.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yahto on 2018/9/12 11:39 PM
 *
 * @author yahto
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RedisTest {
    @Autowired
    private RedisService redisService;
    @Test
    public void setTest(){
        redisService.set("kkkk","123");
    }
}
