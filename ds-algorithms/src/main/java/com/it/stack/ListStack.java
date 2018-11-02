package com.it.stack;

import com.it.stack.common.StackException;
import com.it.stack.common.StackInterface;

/*****************************************************************************
 * A linked list-based implementation of a stack.
 *****************************************************************************/
public class ListStack<AnyType> implements StackInterface<AnyType> {

    private Node<AnyType> top;

    private static class Node<E> {

        private E data;

        public Node<E> next;

        public Node(E data) {
            this(data, null);
        }
        public Node(E data, Node<E> n) {
            this.data = data;
            next = n;
        }
    }

    /**
     * create an empty stack
     */
    public ListStack() {
        top = null;
    }

    /**
     * test if the stack is empty
     */
    public boolean isEmpty() {
        return top == null;
    }
    
    public AnyType pop() throws StackException {
        if (isEmpty())
            throw new StackException("the stack is empty.");
        AnyType data = top.data;
        top = top.next;
        return data;
    }

    /*
     * return the top item without its removal
     */
    public AnyType peek() {
        if (isEmpty())
            throw new StackException("the stack is empty.");
        return top.data;
    }

    public void push(AnyType e) {
        top = new Node<AnyType>(e, top);
    }

    /*
     * make the stack logically empty
     */
    public void clear() {
        top = null;
    }
    public String toString() {
        StringBuffer sb = new StringBuffer("[");

        Node<AnyType> tmp = top;
        while (tmp != null) {
            sb.append(tmp.data + " ");
            tmp = tmp.next;
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        ListStack<Integer> s = new ListStack<Integer>();

        for (int i = 0; i < 6; i++)
            s.push(i);

        // s.clear();
        System.out.println(s);

        System.out.println(s.top.data);

        for (int i = 0; i < 4; i++) {
            s.pop();
        }
        System.out.println(s);
    }
}


