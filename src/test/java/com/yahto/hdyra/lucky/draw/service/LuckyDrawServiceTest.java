package com.yahto.hdyra.lucky.draw.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.yahto.hydra.lucky.draw.model.DrawResult;
import com.yahto.hydra.lucky.draw.service.LuckyDrawService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.*;

/**
 * Created by yahto on 2018/9/13 10:54 PM
 *
 * @author yahto
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LuckyDrawServiceTest {

    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
    private static ExecutorService executorService = new ThreadPoolExecutor(20000, 20000,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(20480), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
    @Autowired
    private LuckyDrawService luckyDrawService;

    private CountDownLatch countDownLatch = new CountDownLatch(20000);

    @Test
    public void luckyDraw() throws InterruptedException {
        for (int i = 0; i < 20000; i++) {
            executorService.execute(() -> {
                countDownLatch.countDown();
                DrawResult drawResult = luckyDrawService.luckyDraw(1L, 12311L);
                System.out.println(drawResult);
            });
        }
        countDownLatch.await();
        Thread.sleep(50000);
    }
}
