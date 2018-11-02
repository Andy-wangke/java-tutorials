package com.it.stack;

import java.util.LinkedList;
import com.it.stack.common.StackException;

/**
 * THis is a stack implementation extends the linkedList class
 * @author K.Andy Wang
 *
 * @param <T>
 */
public class LinkedListStackByInheritance<T> extends LinkedList<T>{
    public LinkedListStackByInheritance(){
        super();
    }
    
    /**
     * Inserts an item onto the top of the stack.
     */
    public void push(T t){
        addFirst(t);
    }
    
    /**
     * Removes and returns the item at the top of the stack
     */
    public T pop(){
        if(isEmpty()){
            throw new StackException("Stack is Empty.");
        }else
            return removeFirst();
    }
    
    /**
     * Returns the top item without its removal
     */
    public T peek()throws StackException{
        return get(0);
    }
    
    public static void main(String[] args) {
        LinkedListStackByInheritance<Integer> stk=new LinkedListStackByInheritance<Integer>();
        stk.push(1);
        stk.push(3);
        stk.push(5);
        stk.push(7);
        System.out.println(stk);
        System.out.println(stk.peek());
        stk.pop();
        System.out.println(stk.peek());
    }
    
}
