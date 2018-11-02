package com.it.vector.common;



/**
 * Vector ADT
 * @author K.Andy Wang
 *
 * @param <AnyType>
 */
public interface VectorInterface<AnyType>{

    /**
     * return the element of Vector with rank r
     * @param rank
     * @return
     */
    public AnyType elemAtRank(int rank);
    
    /**
     * replace the element at rank r with e and return the old element
     * @param rank
     * @param element
     */
    public AnyType replaceAtRank(int r,AnyType e);
    
    /**
     * insert a new element which will have rank r
     * @param r
     * @param e
     */
    public void insertAtRank(int r,AnyType e);
    
    /**
     * remove from vector the element at rank r
     * @param r
     * @return
     */
    public AnyType removeAtRank(int r);
}
