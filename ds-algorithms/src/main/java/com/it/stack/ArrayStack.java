package com.it.stack;



/**
 * @since
 */
public class ArrayStack<AnyType> implements StackInterface<AnyType> {

    public static final int DEFAULT_CAPACITY = 15;
    private int top;
    private AnyType[] a;

    /**
     * create a Stack with the specified capacity
     */
    public ArrayStack(int initCapacity) {
        if (initCapacity <= 0) {
            a = (AnyType[]) new Object[DEFAULT_CAPACITY];
        } else {
            a = (AnyType[]) new Object[initCapacity];
        }
        top = -1; // stack is empty
    }

    /**
     * create a Stack with the default capacity
     */
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    public boolean isEmpty() {
        return top == -1;
    }

    /*
     * remove and return the item at the top of this stack
     */

    public AnyType pop() throws StackException {
        AnyType x = peek();
        a[top] = null;// make sure the object is destroyed
        top--;
        return x;
    }

    /*
     * return the item without its removal
     */

    public AnyType peek() {
        if (isEmpty())
            throw new StackException("Stack is empty...");
        return a[top];
    }

    public void push(AnyType e) throws StackException {
        if (top == a.length)
            throw new StackException("Stack has overflowed...");
        top++;
        a[top] = e;
    }

    public void clear() {
        for (int i = 0; i <= top; i++)
            a[i] = null;
        top = -1;
    }

    /*
     * returns the String representation of the stack
     */
    @Override
    public String toString() {
        StringBuffer out = new StringBuffer("[");
        for (int i = 0; i < top; i++) {
            out.append(a[i] + ", ");
        }
        out.append(a[top] + "]");
        return out.toString();
    }

    public static void main(String[] args) {
        ArrayStack<Integer> s = new ArrayStack<Integer>(6);

        for (int i = 0; i < 6; i++)
            s.push(i);

        System.out.println(s);

        for (int i = 0; i < 4; i++)
            s.pop();

        System.out.println(s);
    }
}

interface StackInterface<AnyType> {

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

@SuppressWarnings("serial")
class StackException extends RuntimeException {

    public StackException(String name) {
        super(name);
    }

    public StackException() {
        super();
    }
}
