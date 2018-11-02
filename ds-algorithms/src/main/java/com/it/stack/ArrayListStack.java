package com.it.stack;

import java.util.ArrayList;
import com.it.stack.common.StackException;
import com.it.stack.common.StackInterface;

/*************************************************************************
 * This is a stack implementation that extends the ArrayList class
 *************************************************************************/
public class ArrayListStack<AnyType> extends ArrayList<AnyType> implements StackInterface<AnyType> {

    public ArrayListStack() {
        super();
    }

    /**
     * return and remove the item at the top of the stack
     */
    @Override
    public AnyType pop() throws StackException {
        if (isEmpty())
            throw new StackException("stack is empty");
        else
            return remove(size() - 1);
    }

    /**
     * return the top item without its removal
     */
    @Override
    public AnyType peek() {
        return get(size() - 1);
    }

    /**
     * Insert an item into the top of the stack
     */
    @Override
    public void push(AnyType e) {
        add(e);
    }

    /**
     * return a String representation
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(super.toString());
        sb.setCharAt(0, '<');
        sb.setCharAt(sb.length()-1, '>');
        return sb.toString();
    }

    public static void main(String[] args) {
        ArrayListStack<Integer> stk=new ArrayListStack<>();
        stk.push(1);
        stk.push(5);
        stk.push(10);
        stk.push(15);
        System.out.println(stk);
        System.out.println(stk.peek());
        stk.pop();
        System.out.println(stk.peek());
    }
}
