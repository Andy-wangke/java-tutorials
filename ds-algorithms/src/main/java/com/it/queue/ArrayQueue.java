package com.it.queue;


/*****************************************************************************
 * This is an implementation of a dynamic wraparound queue structure. It implements the Iterator interface for traversing the queue..
 *****************************************************************************/
public class ArrayQueue<E> implements QueueInterface<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private int cap, // total number of elements in the queue
        cur, // current number of elements
        front, // front index
        back; // back index

    private E[] e;

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    public E dequeue() throws QueueException {
        return null;
    }

    public E getFront() throws QueueException {
        return null;
    }

    public void enqueue(E e) {

    }

    public void clear() {
        // TODO Auto-generated method stub

    }

}

/** QueueInterface **/

interface QueueInterface<AnyType> {

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


/** QueueException **/

class QueueException extends RuntimeException {

    public QueueException(String name) {
        super(name);
    }

    public QueueException() {
        super();
    }
}
