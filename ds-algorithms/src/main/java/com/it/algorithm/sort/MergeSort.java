package com.it.algorithm.sort;

import java.util.Arrays;

/**
 * <pre>
 *      Merge sort
 *      
 *      Recursive algorithm.
        Runs in O(NlogN) time
        
        
        cons:
            need additional memory
            
 *      https://www.cs.cmu.edu/~adamchik/15-121/lectures/Sorting%20Algorithms/code/MergeSort.java
 * </pre>
 * 
 * @author K.Andy Wang
 */
public class MergeSort extends BaseSort {

    
    /**
     * 
     * 
     * ################################
     *  Refer
     *   https://www.cs.cmu.edu/~adamchik/15-121/lectures/Sorting%20Algorithms/code/MergeSort.java
     *      
     * 
     * ################################
     * @param a
     * @param tmp
     * @param left
     * @param right
     */
    public static void mergeSort(Comparable[] a) {
        Comparable[] tmp = new Comparable[a.length];
        mergeSort(a, tmp, 0, a.length - 1);
    }

    public static void mergeSort(Comparable[] a, Comparable[] tmp, int left, int right) {
        if (left < right) {
            int center = left + (right - left) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
        }
    }

    public static void merge(Comparable[] arr, Comparable[] tmp, int lPos, int rPos, int rEnd) {
        int lEnd = rPos - 1;
        int k = lPos;
        int num = rEnd - lPos + 1;

        while (lPos <= lEnd && rPos <= rEnd)
            if (arr[lPos].compareTo(arr[rPos]) <= 0)
                tmp[k++] = arr[lPos++];
            else
                tmp[k++] = arr[rPos++];
        while (lPos <= lEnd)
            tmp[k++] = arr[lPos++];
        while (rPos <= rEnd)
            tmp[k++] = arr[rPos++];
        // copy tmp back
        for (int i = 0; i < num; i++, rEnd--) {
            arr[rEnd] = tmp[rEnd];
        }
        System.out.println("tmp.toString():" + Arrays.toString(tmp));
    }
    
/**
 *      * ################################
     *  Refer
     *   https://www.cs.cmu.edu/~adamchik/15-121/lectures/Sorting%20Algorithms/code/MergeSort.java
     *      
     * 
     * ################################
 * @param args
 */

    public static void main(String[] args) {
        Integer[] a = { 7, 8, 2, 6, 3, 5, 1 };
        mergeSort(a);
        System.out.println(Arrays.toString(a));
    }
}
