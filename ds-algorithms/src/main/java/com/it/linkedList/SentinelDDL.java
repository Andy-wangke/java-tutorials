package com.it.linkedList;



/**
 * 
   * a circular,doubly linked list with a sentinel.
 * @author K.Andy Wang
 *
 * @param <AnyType>
 */
public class SentinelDDL<AnyType> {

    private Node<AnyType> sentinel;// sentinel,serve as head and tail
    
    private Node<AnyType> current;
    
    private int n=0;
    
    
    
    
    
    public SentinelDDL() {
        sentinel = new Node<AnyType>(null);
         
        clear();
    }
    
    public void clear(){
        //Make the list be empty by having the sentinel point to
        //itself in both directions
        sentinel.prev=sentinel.next=sentinel;
        //there's only one place to put the current reference
        current=sentinel;
    }

    
    public void add(AnyType data){
        Node<AnyType> newNode=new Node<AnyType>(data);
        
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
            this.data = data;
        }
      
    }
}
