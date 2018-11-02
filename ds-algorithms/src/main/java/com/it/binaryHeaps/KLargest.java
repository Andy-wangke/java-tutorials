/**
 * Copyright 2014 Hewlett-Packard. All rights reserved. <br>
 * HP Confidential. Use is subject to license terms.
 */
package com.it.binaryHeaps;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.PriorityQueue;


/**
 * find the kth largest element
 * 
 * 
 * <pre>
 * Ref below to get four solutions
 * http://faculty.simpson.edu/lydia.sinapova/www/cmsc250/LN250_Weiss/L10-SelectionProblem.htm
 * 
 * 
 * </pre>
 * 
 * @author ke.wang@hpe.com
 */
public class KLargest {

    /**
     * O(nlogk) based on the min-heap(inside in PriorityQueue)
     * 
     * @param lists
     * @param k
     *            insert first k elements of the array into the heap
     * @return
     */
    public static int findKthLargest(List<? extends Object> lists, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((Collection<? extends Integer>) lists.subList(0, k));

        for (int i = k; i < lists.size(); i++) {
            if ((Integer) lists.get(i) > pq.peek()) {
                // replace root with the current element
                pq.poll();
                pq.add((Integer) lists.get(i));
            }
        }

        return pq.peek();

    }

    /**
     * O(nlogk) based on the max-heap(inside in PriorityQueue)
     * 
     * @param lists
     * @param k
     *            insert first k elements of the array into the heap
     * @return
     */

    public static int findKthLargest() {
        // PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a,b) -> b-a);
        return 0;
    }

    public static void main(String[] args) {
        List<Integer> ints = Arrays.asList(7, 4, 6, 10, 8, 1, 2, 15);
        int k = 2;

        System.out.println("K'th largest element in the array is " + KLargest.findKthLargest(ints, 3));
    }
}
