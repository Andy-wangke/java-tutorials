package com.it.linkedList.common;


/**
 * * Interface defining methods supports by linked list
 * */
public interface ListInterface<AnyType> {

    
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
}
