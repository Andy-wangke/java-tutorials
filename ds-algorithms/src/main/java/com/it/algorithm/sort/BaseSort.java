package com.it.algorithm.sort;


public abstract class BaseSort {

    
    protected static void printArray(int[] arr ){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }  
    }
    
    protected static void printArray(Integer[] arr ){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }  
    }
}
