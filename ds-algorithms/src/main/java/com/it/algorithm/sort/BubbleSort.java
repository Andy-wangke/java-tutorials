package com.it.algorithm.sort;

import javax.swing.plaf.synth.SynthSeparatorUI;

/**
 * <pre>
 *      Bubble sort
 *          
 *          O(n^2)
 *          work by comparing each item in the list with the item next to it,and swapping them if required
 * 
 * 
 * </pre>
 * @author K.Andy Wang
 *
 */
public class BubbleSort {

    
    public static void bubbleSort(int[] arr){
        for(int i=arr.length-1;i>=0;i--){
            for(int j=1;j<=i;j++){
                if(arr[j-1]>arr[j]){
                    //swap(j-1,j)
                    int tmp=arr[j-1];
                    arr[j-1]=arr[j];
                    arr[j]=tmp;
                }
            }
        }
        System.out.print("\nBubbleSort:");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        
    }
    
    public static void main(String[] args) {
        int[] arr = { 3, 1, 4, 2,11,5,0 };
        BubbleSort.bubbleSort(arr);
    }
}
