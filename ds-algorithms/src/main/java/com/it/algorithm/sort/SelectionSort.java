package com.it.algorithm.sort;


/**
 * <pre>
 *      Selection sort
 *          search the entire list and 'select' the element with the <b>smallest（or largest） value 
 *          and swap it with the element at the top of the list
 *     
 * </pre>
 * @author K.Andy Wang
 *
 */
public class SelectionSort extends BaseSort{

    
    public static void selectionSortMin(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int min=i;
            for (int j = i+1; j < arr.length; j++)
                if(arr[j]<arr[min]) min=j;
            //swap tmp and i
            int tmp=arr[i];
            arr[i]=arr[min];
            arr[min]=tmp;
        }
        printArray(arr);
    }
    
    public static void selectionSortMax(int[] arr){
        for (int i = arr.length-1; i>0; i--) {
            int j,max;
            for (j =0,max=j; j <=i; j++)
                if(arr[j]>arr[max]) max=j;
            //swap
            int tmp=arr[i];
            arr[i]=arr[max];
            arr[max]=tmp;
        }
        printArray(arr);
    }
    
    public static void main(String[] args) {
        int[] arr={29, 64, 73, 34, 20,10};
        System.out.println("selectionSortMin:");
        SelectionSort.selectionSortMin(arr);
        System.out.println("\nselectionSortMax:");
        int[] arr2={500,290, 640, 73, 34, 20,10};
        SelectionSort.selectionSortMax(arr2);
    }
    
}
