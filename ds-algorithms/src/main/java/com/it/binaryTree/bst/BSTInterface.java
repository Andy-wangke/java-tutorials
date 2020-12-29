package com.it.binaryTree.bst;

public interface BSTInterface<T> {

    /*****************************************************
    *
    *            INSERT
    *
    ******************************************************/
    public void insert(T data);
    
    //public void insert(BST.Node<T> clazz,T data);
    
    /*****************************************************
    *
    *            SEARCH
    *if toSearched item at the BST
    ******************************************************/
    public boolean search(T toSearch);
    
    //public boolean search(Class<T> clazz,T toSearch); 
    
    /*****************************************************
    *
    *            DELETE
    *
    ******************************************************/
    public void delete(T toDelete);
    
    /*************************************************
    *
    *            TRAVERSAL
    *
    **************************************************/
    public void preOrderTraversal();
    
    public void inOrderTraversal();
    
    public void postOrderTraversal();
    
    /*************************************************
    *
    *            MISC
    *
    **************************************************/
    public int height();
    
    /**
     * count tree leaves
     * @return
     */
    public int countLeaves();
    
    //The width of a binary tree is the maximum number of elements on one level of the tree.
    public int width();
    
    //The diameter of a tree is the number of nodes
    //on the longest path between two leaves in the tree.
    public int diameter();
    
}
