package com.it.util.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.junit.Test;


public class SemaphoreManualTest {
    /**
     * <b>test case:</b>
     */
    @Test
    public void givenLoginQueue_whenReachLimit_thenBlocked() {
        int slots = 10;

        final LoginQueueUsingSemaphore loginQueueUsingSemaphore = new LoginQueueUsingSemaphore(slots);
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(slots * 5);
        try {
            for (int i = 0; i < 50; i++) {
                final int NO = i;
                Runnable run = new Runnable() {

                    @Override
                    public void run() {
                        boolean tryLogin = loginQueueUsingSemaphore.tryLogin();
                        System.out.println(tryLogin + ",login...current login user" + NO);
                        try {
                            TimeUnit.SECONDS.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        loginQueueUsingSemaphore.logout();
                        System.out.println("current availableSlots:" + loginQueueUsingSemaphore.availableSlots());
                    }
                };
                newFixedThreadPool.execute(run);
            }
        } finally {
            newFixedThreadPool.shutdown();
        }
        Assert.assertFalse(loginQueueUsingSemaphore.tryLogin());

    }

}
