package com.it.util.concurrent.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class SemaphoreTest {

    public void simpleSemaphore(){
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(5,true);
        
        try {
            for (int i = 0; i < 50; i++) {
                final int NO = i;
                Runnable run = new Runnable() {

                    public void run() {
                        try {
                            semaphore.acquire();
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        System.out.println("current Access=" + NO);

                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        semaphore.release();
                        System.out.println("current available permits-" + semaphore.availablePermits());
                    }
                };
                newCachedThreadPool.execute(run);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            newCachedThreadPool.shutdown();
        }
    }

    public static void main(String[] args) {
        SemaphoreTest test = new SemaphoreTest();
        test.simpleSemaphore();
    }
}
