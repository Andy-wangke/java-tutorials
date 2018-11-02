package com.it.algorithm.sort;


/**
 * 
 * <pre>
 *      Insertion Sort algorithm
 *          Examine the element at position x and 
 *          'insert' it in the correct order in the list from 0 through x-1
 *          O(n^2)
 *          
 *          
 * </pre>
 */
public class InsertionSort {


    
    /**
     * ascend order
     * @param matrix
     */
    public static void insertSort(int[] matrix) {
        for (int i = 1; i < matrix.length; i++) {//n
            for (int j = i; j > 0 && matrix[j - 1] > matrix[j]; j--) {//n
                // swap(j - 1, j);
                int mid = matrix[j - 1];
                matrix[j - 1] = matrix[j];
                matrix[j] = mid;
            }
            //print current matrix
            //System.out.println(matrix);
        }
        System.out.print("InsertSort1:");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + ",");
        }
        System.out.println();
    }
    
    public static void insertSortByTmp2(int[] matrix){
        for(int i=1;i<matrix.length;i++){
            int tmp=matrix[i];
            int j;
            for(j=i;j>0&matrix[j-1]>tmp;j--){
                matrix[j]=matrix[j-1];
            }
            matrix[j]=tmp;
        }
        System.out.print("\nInsertSort3:");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + ",");
        }
    }
    
    public static void insertSortByTmp(int[] matrix){
        for(int i=1;i<matrix.length;i++){
            int tmp=matrix[i];int j=i;
            while(j>0&&matrix[j-1]>tmp){
                matrix[j]=matrix[j-1];
                j--;
            }
            matrix[j]=tmp;
        }
        System.out.print("\nInsertSort2:");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(matrix[i] + ",");
        }
    }
    
    //TODO:do while
    public static void insertSortByTmp3(int[] matrix){
        
    }


    public static void main(String[] args) {
        int[] matrix = { 3, 1, 4, 2,11,5,0 };
        InsertionSort.insertSort(matrix);
        InsertionSort.insertSortByTmp(matrix);
        InsertionSort.insertSortByTmp2(matrix);
    }
}
