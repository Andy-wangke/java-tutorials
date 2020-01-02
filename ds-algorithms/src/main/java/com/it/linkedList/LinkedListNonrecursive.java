package com.it.linkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * without recursively
 */
public class LinkedListNonrecursive<AnyType> implements Iterable<AnyType> {

    /*******************************************************
     * The Node class
     ********************************************************/
     public static class Node<AnyType> {

        private AnyType data;
        private Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<AnyType> head = null;

    public LinkedListNonrecursive() {
        head = null;
    }

    /**
     * Returns true if the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Insert a new node of this list
     * 
     * @param item
     * @return
     */
    public Node<AnyType> addFirst(AnyType item) {

        head = new Node<AnyType>(item, head);
        return head;
    }

    /**
     * get the first element of this list
     * 
     * @return
     */
    public Node<AnyType> getFirst() {

        if (head == null)
            throw new NoSuchElementException("LinkedList is null.");
        return head;
    }

    /**
     * Removes the first element of this list
     * 
     * @return
     */
    public AnyType removeFirst() {
        Node<AnyType> first = getFirst();
        head = head.next;
        return first.data;
    }

    /**
     * add a new node to the end of the list
     * 
     * @param data
     */
    public void addLast(AnyType data) {
        if (head == null)
            addFirst(data);
        else {
            Node<AnyType> tmp = head;
            while (tmp.next != null)
                tmp = tmp.next;
            tmp.next = new Node<AnyType>(data, null);
        }
    }

    public AnyType getLast() {
        if (head == null)
            throw new NoSuchElementException();

        Node<AnyType> tmp = head;
        while (tmp.next != null)
            tmp = tmp.next;
        return tmp.data;
    }

    public void clear() {
        head = null;
    }

    // create a deep copy

    /**
     * Return a deep copy of a list Complexity O(n^2)
     * 
     * @return
     */
    public LinkedListNonrecursive<AnyType> copy1() {
        LinkedListNonrecursive<AnyType> twin = new LinkedListNonrecursive<AnyType>();
        Node<AnyType> tmp = head;
        while (tmp != null) {
            twin.addLast(tmp.data);// n
            tmp = tmp.next;
        }
        return twin;
    }

    /**
     * return a deep copy of the list
     * 
     * @return
     */
    public LinkedListNonrecursive<AnyType> copy2() {
        return null;
    }

    /**
     * Return a deep copy of the immutable list
     * It uses a tail reference
     * 
     * @return
     */
    public LinkedListNonrecursive<AnyType> copy3() {
        return null;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (Object x : this)
            sb.append(x + " ");

        return sb.toString();
    }

    /**
     * debugging and testing
     * 
     * @param args
     */
    public static void main(String[] args) {
        LinkedListNonrecursive<String> list = new LinkedListNonrecursive<>();
        list.addLast("l");
        list.addLast("i");
        list.addLast("s");
        list.addLast("t");
    }

    @Override
    public Iterator<AnyType> iterator() {
        return null;
    }
}
