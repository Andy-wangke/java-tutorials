package com.it.stack.common;

public interface StackInterface<AnyType> {

    /**
     * Tests if the stack is empty.
     */
    public boolean isEmpty();

    /**
     * Removes and returns the item at the top of this stack.
     */
    public AnyType pop() throws StackException;

    /**
     * 
     */
    public AnyType peek();

    /**
     * 
     */
    public void push(AnyType e);

    /**
     * Removes all items from the stack.
     */
    public void clear();

}


