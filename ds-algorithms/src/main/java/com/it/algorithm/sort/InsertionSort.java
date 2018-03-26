/**
 * Copyright 2014 Hewlett-Packard. All rights reserved. <br>
 * HP Confidential. Use is subject to license terms.
 */
package com.it.algorithm.sort;


/**
 * @author kewan Insertion Sort algorithm
 */
public class InsertionSort {

    private int[] matrix = { 3, 1, 4, 2 };

    public void insertSort() {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = i; j > 0 && matrix[j - 1] > matrix[j]; j--) {
                // swap(j - 1, j);
                int mid = matrix[j - 1];
                matrix[j - 1] = matrix[j];
                matrix[j] = mid;
            }
            System.out.println(matrix);
        }
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + ",");
        }
    }


    public static void main(String[] args) {

        new InsertionSort().insertSort();
    }
}
