package com.yahto.hdyra.lucky.draw.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.yahto.hydra.lucky.draw.model.KillResult;
import com.yahto.hydra.lucky.draw.service.PrizeKillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.concurrent.*;

/**
 * Created by yahto on 2018/10/17 12:40 PM
 *
 * @author yahto
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PrizeKillServiceTest {
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
    private static ExecutorService executorService = new ThreadPoolExecutor(1000, 1000,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingDeque<>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    private CountDownLatch countDownLatch = new CountDownLatch(1000);

    @Resource(name = "prizeKillByPessimisticLockServiceImpl")
    private PrizeKillService prizeKillService;

    @Test
    public void prizeKillTest() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            executorService.execute(() -> {
                countDownLatch.countDown();
                KillResult killResult = prizeKillService.prizeKill(1L, 000001L);
                System.out.println(killResult + Thread.currentThread().getName());
            });
        }
        countDownLatch.await();
        Thread.sleep(5000);
    }
}
