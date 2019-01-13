/**
 * 
 */
package com.it.linkedList;

import java.util.Iterator;

/**
 * 
 * 
 * @author K.Andy Wang
 *
 */
public class DoublyLinkedList<AnyType> implements Iterable<AnyType>{

    private int size=0;
    
    private DoubleNode head;
    private DoubleNode tailer;
    
    
    public DoublyLinkedList(DoubleNode head, DoubleNode tailer) {
        this.head = head;
        this.tailer = tailer;
    }

    
    
    public void insertAfter(DoubleNode cur,AnyType key,AnyType toInsert){
        
    }

    @Override
    public Iterator<AnyType> iterator() {
        // TODO Auto-generated method stub
        return null;
    }

    
    /**
     * the inner doubly node class
     * @author K.Andy Wang
     *
     * @param <AnyType>
     */
    public static class DoubleNode<AnyType>{
        private AnyType data;
        private DoubleNode<AnyType> prev;
        private DoubleNode<AnyType> next;//refer to next item in the list
        public DoubleNode(AnyType data, DoubleNode<AnyType> prev, DoubleNode<AnyType> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        public DoubleNode(AnyType data) {
            this.data = data;
        }   
    }
}
