package com.it.algorithm.sort;

/**
 * <pre>
 *      Shell sort
 *      which improves on insertion sort
 * </pre>
 * 
 * @author K.Andy Wang
 */
public class ShellSort {

    private ShellSort() {
    }

    // TODO
    public static void shellsort(int[] arr) {
        int n = arr.length;
        for (int md = n / 2; md > 0; md /= 2) {
            for (int i = md; i < n; i++) {
                // do an insertion sort
                for (int j = i; j >= md && arr[j] < arr[j - md]; j -= md) {
                    // exchange i j
                    int tmp = arr[i];
                    arr[i] = arr[j-md];
                    arr[j-md] = tmp;
                }

            }
        }

        System.out.print("\nShellSort:");
        printArray(arr);
    }
    
    /**
     * increment sequences N/2,N/4,...(repeatedly divide by 2)
     * @param arr
     */
    public static void shellSortBytmp(int[] arr){
        int n= arr.length;
        for (int gap = n/2; gap >0; gap/=2) {
            for(int i=gap;i<n;i++){
                //
                int tmp=arr[i];
                int j;
                for(j=i;j>=gap&&arr[j-gap]>tmp;j-=gap){
                    arr[j]=arr[j-gap];
                }
                arr[j]=tmp;
            }
        }
        System.out.print("\nShellSort2:");
        printArray(arr);
    }
    
    
    /**
     * Hibbard's increments: 1, 3, 7, ..., 2^k - 1 ;
     * @param arr
     */
    public static void shellSortIncrementByHibbard(int[] arr){
        int n= arr.length;
        for (int gap = n/2; gap >0; gap/=2) {
            for(int i=gap;i<n;i++){
                //
                int tmp=arr[i];
                int j;
                for(j=i;j>=gap&&arr[j-gap]>tmp;j-=gap){
                    arr[j]=arr[j-gap];
                }
                arr[j]=tmp;
            }
        }
        System.out.print("\nShellSort2:");
        printArray(arr);
    }
    
    /**
     * TODO:
     * http://www.iti.fh-flensburg.de/lang/algorithmen/sortieren/shell/shellen.htm
     * @param arr
     */
    public static void shellSort3(int[] arr){
        
        
        
    }
    
    private static void printArray(int[] arr ){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }  
    }

    
    /**ref 
     * https://algs4.cs.princeton.edu/21elementary/Shell.java.html
     * @param arr
     */
    public static void shellsort(Comparable[] arr) {

    }

    public static void main(String[] args) {
        int arr[] = { 12, 34, 54, 2, 3, 5, 11, 24, 35 };
        ShellSort.shellsort(arr);
        ShellSort.shellSortBytmp(arr);
    }
}
