package com.it.linkedList;
import java.util.NoSuchElementException;



/**
 * https://www.cs.cmu.edu/~adamchik/15-121/lectures/Recursions/code/LinkedList.java
 * 
 * @author ke.wang@hpe.com
 */
public class LinkedList<AnyType> {


    private Node<AnyType> head = null;

    public LinkedList() {
        head = null;
    }

    /**
     * Returns true if the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    public Node<AnyType> addFirst(AnyType item) {

        head = new Node<AnyType>(item, head);
        return head;
    }
    /**
     * Recursively inserts a new node to the end of this list.
     */
    public void addLast(AnyType item) {
        if (head == null) {
            addFirst(item);
        }else{
            addLast(head, item);
        }
    }
    
    public void addLast(Node node, AnyType value) {
        if (node.next != null) {
            addLast(node.next, value);
        } else {
            node.next = new Node(value, null);
        }
    }

    public AnyType getFirst() {
        if (head == null)
            throw new NoSuchElementException();
        return head.data;
    }

    /**
     * remove the first element in the list logically
     * 
     * @return
     */
    public AnyType removeFirst(){
        AnyType tmp = getFirst();
        head = head.next;
        return tmp;
    }

    public String toString() {
        return toString(head);
    }

    private String toString(Node node) {
        if (node == null)
            return "";
        else
            return node.data + " " + toString(node.next);
    }

    // create a deep copy

    /*******************************************************
     * The Node class
     ********************************************************/
    private static class Node<AnyType> {

        private AnyType data;
        private Node<AnyType> next;

        public Node(AnyType data, Node<AnyType> next) {
            this.data = data;
            this.next = next;
        }
    }
}
