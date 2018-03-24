package com.it.util.concurrent.semaphore;

import java.util.concurrent.Semaphore;

public class LoginQueueUsingSemaphore {

    private Semaphore semaphore;

    public LoginQueueUsingSemaphore(int slotLimit) {
        semaphore = new Semaphore(slotLimit);
    }

    boolean tryLogin() {
        /**
         * return true if a permit is available immediately,whether or not other threads are currently waiting <br>
         * otherwise return false
         */
        return semaphore.tryAcquire();
    }

    void logout() {
        semaphore.release();
    }

    int availableSlots() {
        return semaphore.availablePermits();
    }

}
