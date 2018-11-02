package com.it.queue.common;


/** QueueException **/

public class QueueException extends RuntimeException {

    public QueueException(String name) {
        super(name);
    }

    public QueueException() {
        super();
    }
}
