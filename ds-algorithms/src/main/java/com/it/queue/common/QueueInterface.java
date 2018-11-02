package com.it.queue.common;

import com.it.queue.common.QueueException;

/** QueueInterface **/

public interface QueueInterface<AnyType> {

    /**
     * Tests if the Queue is empty.
     */
    public boolean isEmpty();

    /**
     * Removes and returns the front item
     */
    public AnyType dequeue() throws QueueException;

    /**
     * Returns the front item without its removal
     */
    public AnyType getFront() throws QueueException;

    /**
     * Inserts an item to teh back
     */
    public void enqueue(AnyType e);

    /**
     * Removes all items from the Queue.
     */
    public void clear();
}
