package com.it.util.concurrent.locks;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ke.wang@hpe.com
 */
public class ReentrantLockTest {

    private int flag = 1;
    ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition1 = reentrantLock.newCondition();
    Condition condition2 = reentrantLock.newCondition();
    Condition condition3 = reentrantLock.newCondition();

    public void thread1() {
        reentrantLock.lock();
        if (flag != 1) {
            condition2.signal();
        }

        System.out.println("running thread1");

        reentrantLock.unlock();
    }

    public void thread2() {
        System.out.println("running thread2....");
    }

    public void thread3() {
        System.out.println("running thread3....");
    }

    public static void main(String[] args) {
        final ReentrantLockTest r = new ReentrantLockTest();
        new Thread() {

            /*
             * (non-Javadoc)
             * @see java.lang.Thread#run()
             */
            @Override
            public void run() {
                while (true) {
                    r.thread1();
                }
            }

        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    r.thread2();
                }
            }

        }.start();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    r.thread3();
                }
            }
        }.start();
    }
}
