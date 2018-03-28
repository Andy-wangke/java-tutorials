package com.it.util.concurrent;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * 测试Atomic 原子性
 * 
 * @author ke.wang@hpe.com
 */

public class AtomicTest {

    public static void main(String[] args) {
        // VolatileAtomicTest.testVolatileAtomic();
        // VolatileSyncTest.testVolatileAtomic();
        VolatileAtomicIntegerTest.testVolatileAtomic();
    }
}

/**
 * volatile atomicInteger
 */
class VolatileAtomicIntegerTest {

    private static AtomicInteger race = new AtomicInteger();

    /**
     * synchronized是heavyweight
     */
    public static void increase() {
        race.incrementAndGet();
    }

    public static final int THREAD_COUNT = 20;

    /**
     * volatile 不具有原子性
     */
    // 预期：20*1000
    public static void testVolatileAtomic() {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }

            });
            threads[i].start();
        }

        // 等待所有累加线程都结束
        while (Thread.activeCount() > 1)
            Thread.yield();
        System.out.println(race);
    }
}

/**
 * volatile synchronized实现
 */
class VolatileSyncTest {

    private static volatile int race = 0;

    /**
     * synchronized是heavyweight
     */
    public static synchronized void increase() {
        race++;// 会拆分成多个机器指令
    }

    public static final int THREAD_COUNT = 20;

    /**
     * volatile 不具有原子性
     */
    // 预期：20*1000
    public static void testVolatileAtomic() {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {

                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }

            });
            threads[i].start();
        }

        // 等待所有累加线程都结束
        while (Thread.activeCount() > 1)
            Thread.yield();
        System.out.println(race);
    }
}

/**
 * volatile 自增运算测试
 */
class VolatileAtomicTest {

    private static volatile int race = 0;

    public static void increase() {
        race++;// 会拆分成多个机器指令
    }

    public static final int THREAD_COUNT = 20;

    /**
     * volatile 不具有原子性
     */
    // 预期：20*1000
    public static void testVolatileAtomic() {
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 10000; j++) {
                        increase();
                    }
                }

            });
            threads[i].start();
        }

        // 等待所有累加线程都结束
        while (Thread.activeCount() > 1)
            Thread.yield();
        System.out.println(race);
    }
}