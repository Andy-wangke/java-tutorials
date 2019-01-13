package com.it.linkedList.common;


/**
 * * Interface defining methods supports by linked list
 * <pre>
 * ref https://www.cs.dartmouth.edu/~scot/cs10/lectures/6/CS10LinkedList.java
 * 
 * </pre>
 * */
public interface LinkedListInterface<AnyType> {

    
    /**
     * add 
     * @param e
     */
    public void addFirst(AnyType e);
    
    public void addLast(AnyType e);
    
    
    /**
     * 
     * @param e
     */
    public void removeFirst(AnyType e);
    
    public AnyType getFirst();
    
    public AnyType getLast();
    
    public int size();
    
    public void clear();
    
    /**
     * Moves current to the next position.
     * Error if there is no next item.  
     * @return the data in the new current
     */
    public AnyType next();
}
