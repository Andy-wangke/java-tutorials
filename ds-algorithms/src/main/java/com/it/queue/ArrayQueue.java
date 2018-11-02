package com.it.queue;

import java.util.Iterator;
import com.it.queue.common.QueueException;
import com.it.queue.common.QueueInterface;

/*****************************************************************************
 * This is an implementation of a dynamic wrap around queue(circle queue) structure. 
 * It implements the Iterator interface for traversing the queue..
 *****************************************************************************/
public class ArrayQueue<AnyType> implements QueueInterface<AnyType>,Iterable<AnyType> {

    private static final int DEFAULT_CAPACITY = 10;

    private int cap, // total number of elements in the queue
        cur, // current number of elements
        front, // front index
        back; // back index

    private AnyType[] E;

    public ArrayQueue() {
        cap=DEFAULT_CAPACITY;
        E=(AnyType[])new Object[DEFAULT_CAPACITY];
        back=-1;front=0;
    }

    /**
     * Test if the queue is logically empty
     */
    public boolean isEmpty() {
        return cur == 0;
    }

    /**
     *  Puts a value into the back of the queue. It works with wraparound.
     *  If the queue is full, it doubles its size.
     *
     *  @param value the item to insert.
     */
    public void enqueue(AnyType value) {
        //isFull
        if(isFull())
            doubleSize();
        
        back++;
        E[back%cap] = value;
        cur++;
    }

    /**
    *  Returns and removes the front element of the queue. It works with wraparound.
    *
    *  @return element at front of the queue
    *  @throws NoSuchElementException if the queue is empty.
    */
    public AnyType dequeue() throws QueueException {
        
        AnyType frontValue=getFront();
        E[front%cap]=null; //for garbage collection
        
        front++;
        cur--;
        return frontValue;
    }

    /**
    *  Returns the first element in the queue.
    *
    *  @return element at front of the queue
    *  @throws NoSuchElementException if the queue is empty.
    */
    public AnyType getFront() throws QueueException {
        if(isEmpty())
            throw new QueueException();
        else
            return E[front%cap];
    }

    /**
    *  Makes the queue physically empty.
    *
    */
    public void clear() {
        for(int i=0;i<cap;i++) E[i]=null;
        
        cur=0;
        front=0;
        back=-1;
    }
    
    public boolean contains(AnyType value){
        boolean isFound=false;
        
        
        
        return isFound;
    }
    
    
    /********** Helper Function *******************/

    /**
     *  Test if the queue is logically full
     * @return
     */
    public boolean isFull(){
        return cur == cap;
    }
    /**
     *  Increase the queue capacity by doubling the size.
     */
    public void doubleSize(){
        AnyType[] newArray = (AnyType[])new Object[2*cap];
        
        //copy item into new Array
        for(int i =front;i<back;i++){
            newArray[i-front]=E[i%cap];
        }
        
        E=newArray;
        front=0;
        back=cur-1;
        cap*=2;
    }

    /***************    Iterator      *************** */

    /**
    * Obtains an Iterator object used to traverse the Queue from its front to back.
    *
    * @return an iterator.
    *
    * @throws UnsupportedOperationException if you remove using the iterator
    */
    @Override
    public Iterator<AnyType> iterator() {
        
    return new QueueIterator<AnyType>();
    }
    
    
    private class QueueIterator<AnyType> implements Iterator<AnyType>{

        
        private int index; //traversal index
        
        
        
        public QueueIterator() {
            index=front;
        }

        @Override
        public boolean hasNext() {
            
            return index<=back;
        }

        @Override
        public AnyType next() {
            
            return (AnyType) E[(index++)%cap];
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }
        
        
    }
    
    
    public static void main(String[] args) {
        ArrayQueue<String> queue=new ArrayQueue<>();
        String[] people = {"Tom", "Jay", "Pat", "Meghan", "Tom", "Mark","Kasey","John","Helen"};
        
        for (int i = 0; i < people.length; i++) {
            queue.enqueue(people[i]);
        }
        
        for (int i = 0; i <3; i++) queue.dequeue();
        
        Iterator itr=queue.iterator();
        while(itr.hasNext())
            System.out.println(itr.next()+" ");
        
        System.out.println("=======================");
        
        queue.enqueue("Mike");
        queue.enqueue("Bo");
        
        itr=queue.iterator();
        while(itr.hasNext())
            System.out.print(itr.next()+" ");
        
        System.out.println();
    }
}
