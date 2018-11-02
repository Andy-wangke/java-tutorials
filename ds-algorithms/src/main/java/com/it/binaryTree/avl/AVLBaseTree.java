package com.it.binaryTree.avl;


public abstract class AVLBaseTree<AnyType> {

    
    public static class AVLNode<AnyType>{
        
        AnyType element;//the data in the node
        AVLNode<AnyType> lt;//left child
        AVLNode<AnyType> rt;//right child
        int height;//height
        
        public AVLNode(AnyType element) {
            this(element,null,null);
        }
        
        public AVLNode(AnyType element,AVLNode<AnyType> lt,AVLNode<AnyType> rt) {
            this.element=element;
            this.lt=lt;
            this.rt=rt;
        }
    }
    
    
    /**
     * Insert into the tree
     * duplicates are ignored
     * @param e
     */
    public abstract void insert(AnyType e);
    
    /**
     * 
     * @param e
     */
    public abstract void remove(AnyType e);
    
    /**
     * Internal method to remove from a subtree
     * @param e
     * @param t
     * @return
     */
    //public abstract AVLNode<AnyType> remove(AnyType e,AVLNode<AnyType> t);
    
    
    
    public AVLNode<AnyType> search(AnyType e){
        AVLNode<AnyType> node=new AVLNode<AnyType>();
        
        
        
        return node;
    }
    
}
