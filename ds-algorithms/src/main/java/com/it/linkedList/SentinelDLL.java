package com.it.linkedList;



/**
 * ref https://www.cs.dartmouth.edu/~scot/cs10/lectures/6/SentinelDLL.java
   * a circular,doubly linked list with a sentinel.
 * @author K.Andy Wang
 *
 * @param <AnyType>
 */
public class SentinelDLL<AnyType> {

    private Node<AnyType> sentinel;// sentinel,serve as head and tail
    
    private Node<AnyType> current;
    
    private int n=0;
    
    
    
    
    /**
     * Constructor for a circular,doubly linked list with a sentinel 
     */
    public SentinelDLL() {
        sentinel = new Node<AnyType>(null);
         
        clear();
    }
    
    //sentinel node should not ever be deleted in sentinel list
    public boolean isEmpty(){
        return sentinel.next == sentinel;
    }
    
    public boolean hasCurrent(){
        return current != sentinel;
    }
    
    public boolean hasNext(){
        return hasCurrent()&&current.next !=sentinel;
    }
    
    public void clear(){
        //Make the list be empty by having the sentinel point to
        //itself in both directions
        sentinel.prev=sentinel.next=sentinel;
        //there's only one place to put the current reference
        current=sentinel;
    }
    
    
    public void remove(){
        //do not ever let the 'sentinel' be deleted
        if(hasCurrent()){
            //splice out the current element
            
            current.prev.next = current.next;
            current.next.prev = current.prev;
            
            current = current.next;//make successor the new current
        }else{
            System.err.println("No current Item");
        }
    }

    
    public void add(AnyType data){
        Node<AnyType> newNode=new Node<AnyType>(data); //allocate a new element
        
        //splice in the new element
        newNode.next = current.next;
        newNode.prev = current;
        current.next.prev = newNode;
        current.next = newNode;
        
        current = newNode; //new element is current position
        
    }
    
    public AnyType getFirst(){
        //none check
        current = sentinel.next;
        return get();
    }
    public AnyType get(){
        if(hasCurrent())
            return current.data;
        else{
            System.err.println("No current item");
            return null;
        }
    }

    public void set(AnyType obj){
        if(hasCurrent()){
            current.data = obj;
        }
    }


    /**
     * inner class representing the node in the list
     * @author K.Andy Wang
     *
     * @param <AnyType>
     */
    private static class Node<AnyType>{
        private AnyType data;
        private Node<AnyType> next;
        private Node<AnyType> prev;
        public Node(AnyType data) {
            next=prev=null;//no Node before or after this one yet
            this.data = data;//okay to copy reference ,since obj references an immutable object
        }
        
        
      
    }
}
