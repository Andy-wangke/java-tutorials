package com.it.linkedList;
import java.util.NoSuchElementException;



/**
 * https://www.cs.cmu.edu/~adamchik/15-121/lectures/Recursions/code/LinkedList.java
 * 
 */
public class LinkedListResursive<AnyType> {

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
    
    
    private Node<AnyType> head = null;

    public LinkedListResursive() {
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
    
    private void addLast(Node node, AnyType value) {
        if (node.next != null) {
            addLast(node.next, value);
        } else {
            node.next = new Node<AnyType>(value, null);
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
    
    /**
     * Recursively insert a new node before a node containing key
     */
    public void insertBeforeHead(AnyType toInsert){
        head=insertBefore(head, head.data, toInsert);
    }
    
    public Node<AnyType> insertBefore(Node<AnyType> node,AnyType key,AnyType toInsert){
        if(node==null) return null;
        else if(node.data.equals(key)){
            return new Node<AnyType>(toInsert, node);
        }else
            node.next=insertBefore(node.next, key, toInsert);
        return node;      
    }
    
    /**
      *  Recursively insert a new node after a node containing key
     */
    public void insertAfterHead(AnyType key,AnyType toInsert){
        insertAfter(head, key, toInsert);
    }
    
    public void insertAfter(Node<AnyType> node,AnyType key,AnyType toInsert){
        if(node==null) return;
        else if(node.data.equals(key))
            node.next=new Node<AnyType>(toInsert,node.next);//TODO:node.next
        else 
            insertAfter(node.next,key,toInsert);
    }

    
    /**
     * return a list representation
     */
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

    
    /**
     * debugging and testing
     * @param args
     */
    public static void main(String[] args) {
        LinkedListResursive<String> list=new LinkedListResursive<>();
        list.addLast("l");
        list.addLast("i");
        list.addLast("s");
        list.addLast("t");
    }
}
