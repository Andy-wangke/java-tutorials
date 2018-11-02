package com.it.algorithm.sort;

/**
 * <pre>
 *      Quick sort
 *          Features:
                Similar to mergesort - divide-and-conquer recursive algorithm
                One of the fastest sorting algorithms
                Average running time O(NlogN)
                Worst-case running time O(N2)
 *      
 *      Step One/Strategy of Choosing the pivot
 *          1.Some fixed element: e.g. the first, the last, the one in the middle
            This is a bad choice - the pivot may turn to be the smallest or the largest element, 
            then one of the partitions will be empty.

            2.Randomly chosen (by random generator ) - still a bad choice.
            3.The median of the array (if the array has N numbers, the median is the [N/2] largest number. This is difficult to compute - increases the complexity.
            4.The median-of-three choice: take the first, the last and the middle element. 
             Choose the median of these three elements
             
       Step two/Partitioning
 * </pre>
 * 
 * @author K.Andy Wang
 */
public class QuickSort extends BaseSort {

    private static final int CUT_OFF = 10;

    /**
     * 1.select a pivot The median-of-three choice: take the first, the last and the middle element. Choose the median of these three elements.
     * 2.partition
     * 
     * @param arr
     */
    public static void quickSort(int[] arr) {

    }

    public static void quickSort2(Comparable[] arr) {

    }

    public static <AnyType extends Comparable<? super AnyType>> void quickSort3(AnyType[] arr) {
        qSort(arr, 0, arr.length);

    }

    // helper

    //TODO: why occurred indexOutbound exception
    private static <AnyType extends Comparable<? super AnyType>> void qSort(AnyType[] arr, int left, int right) {
        if(right-left<=1) return;
        // pick a pivot
        AnyType pivot = median3(arr, left, right);
        //AnyType pivot = arr[(left+right)/2];
        System.out.println("current pivot:" + pivot.toString());
        // partition
        int i = left, j = right-1;
        for (;;) {
            while (i>0&arr[++i].compareTo(pivot) < 0) {}// move the left finger
            while (j>0&&arr[--j].compareTo(pivot) > 0) {}// move the right finger

            if (i < j)
                swap(arr, i, j);
            else
                break;// break if fingers have crossed
        }
        swap(arr, i, right - 1);// restore pivot

        qSort(arr, left, i - 1);// sort left elements
        qSort(arr, i + 1, right);// sort right elements
    }

    /**
     * Return median of left,center and right Order these and hide the pivot
     * 
     * @param arr
     * @param left
     * @param right
     * @return
     */
    private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType[] arr, int left, int right) {

        int center = ( left + right ) / 2;
        if( arr[ center ].compareTo( arr[ left ] ) < 0 )
            swap( arr, left, center );
        if( arr[ right-1 ].compareTo( arr[ left ] ) < 0 )
            swap( arr, left, right-1 );
        if( arr[ right-1 ].compareTo( arr[ center ] ) < 0 )
            swap( arr, center, right-1 );

        // Place pivot at position right - 1
        swap( arr, center, right - 1 );
        return arr[ right - 1 ];
    }

    private static final <AnyType extends Comparable<? super AnyType>> void swap(AnyType[] arr, int left, int right) {
        AnyType tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }

    // private static <AnyType extends Comparable<? super AnyType>> int partition(AnyType[] arr,int left,int right,AnyType pivot){
    // //partition
    // int i=left,j=right-1;
    // for(;;)
    // {
    // while(arr[++i].compareTo(pivot)<0){}
    // while(pivot.compareTo(arr[--j])<0){}
    //
    // if(i<j) swap(arr[i], arr[j]);
    // else break;
    // }
    //
    // swap(arr[i], arr[right=1]);//restore pivot
    //
    // partition(arr,left,i-1);
    //
    // return 0;
    // }

    // [29, 64, 73, 34, 20, 10, 129, 164, 173, 134, 120, 110]
    //  {8, 3, 25, 6, 10, 17, 1, 2, 18, 5}
    public static void main(String[] args) {
        int[] arr = {29, 64, 73, 34,10, 20, 129, 164, 173, 134, 120, 110};
        //QuickSort.quickSort3(arr);
        QuickSort3.quickSort(arr);
        printArray(arr);
    }
}


/**
 * pivot is last element
 * @author K.Andy Wang
 *
 */
class QuickSort2{
    
    
    public static void quickSort(int[] arr){
        qSort(arr, 0, arr.length);
    }
    private static void qSort(int[] arr,int low,int high){
        if(high-low<=1) return;
        int pivot=arr[high-1];
        System.out.println("current pivot:"+pivot);
        int split=low;
        for(int i=low;i<high-1;i++){
            if(arr[i]<pivot){
                swapReference(arr, i, split);
                split++;
            }
        }
        swapReference(arr, high-1, split);
        qSort(arr, low, split);
        qSort(arr, split+1, high);
        
    }
    
    
    private static void swapReference(int[] arr,int left,int right){
        int tmp=arr[left];
        arr[left]=arr[right];
        arr[right]=tmp;
    }
}


/**
 * pivot = median of 3
 * @author K.Andy Wang
 *
 */
class QuickSort3{
    
    
    public static void quickSort(int[] arr){
        qSort(arr, 0, arr.length);
    }
    private static int medianOf3(int[] arr,int left,int right){
        int center = ( left + right ) / 2;
        if( arr[ center ]<arr[ left ])
            swapReference( arr, left, center );
        if( arr[ right-1 ]<arr[ left ])
            swapReference( arr, left, right-1 );
        if( arr[ right-1 ]<arr[ center ])
            swapReference( arr, center, right-1 );

        // Place pivot at position right - 1
        swapReference( arr, center, right - 1 );
        return arr[ right - 1 ];
    }
    private static void qSort(int[] arr,int left,int right){
        if(right-left<=1) return;
        int pivot = medianOf3(arr, left, right);
        System.out.println("current pivot="+pivot);
        int split  = left;
        for(int i=left;i<right-1;i++){
            if(arr[i]<pivot){
                swapReference(arr, i, split);
                split++;
            }
        }
        swapReference(arr, right-1, split);
        qSort(arr, left, split);
        qSort(arr, split+1, right);
        
    }
    private static void swapReference(int[] arr,int left,int right){
        int tmp=arr[left];
        arr[left]=arr[right];
        arr[right]=tmp;
    }
    
    
}
