package com.it.binaryTree.avl;

/**
 * Refer to http://www.cs.yale.edu/homes/aspnes/pinewiki/C(2f)AvlTree.html
 * @author andywang
 *
 */
public interface AVLTreeInterface {

    /**
     * free a tree
     * @param t
     */
    public void avlDestroy(AVLBaseTree.AVLNode t);
    
    /**
     * return the height of a tree
     * @param t
     * @return
     */
    public int avlGetHeight(AVLBaseTree.AVLNode t);
    
    /**
     * return non-zero if key is present in tree
     * @param t
     * @param key
     * @return
     */
    public int avlSearch(AVLBaseTree.AVLNode t,int key);
    
    /**
     * insert a new element into a tree
     * @param t
     * @param key
     * @return
     */
    public void avlInsert(AVLBaseTree.AVLNode t,int key);
    
    /**
     * run sanity check on tree(for debugging)
     * assert will fail if heights are wrong
     * @param t
     */
    public void avlSanityCheck(AVLBaseTree.AVLNode t);
    
    /**
     * print all keys of the tree in order
     * @param t
     */
    public void avlPrintKeys(AVLBaseTree.AVLNode t);
    
    /**
     * delete and return minimum value in a tree
     * @param t
     * @return
     */
    public int avlDeleteMin(AVLBaseTree.AVLNode t);
}
