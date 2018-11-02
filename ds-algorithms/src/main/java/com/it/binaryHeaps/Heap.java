package com.it.binaryHeaps;

import java.util.Arrays;


/**
 * @author ke.wang
 */
@SuppressWarnings("unchecked")
public class Heap<AnyType extends Comparable<AnyType>> {

    private static final int CAPACITY = 2;

    private int size;

    private AnyType[] heap;

    public Heap() {
        size = 0;
        heap = (AnyType[]) new Comparable[CAPACITY];
    }

    public Heap(AnyType[] array) {
        // copy to new array
        size = array.length;
        heap = (AnyType[]) new Comparable[array.length + 1];
        System.arraycopy(array, 0, heap, 1, array.length);// do not use 0 index

        buildHeap();


    }

    /**
     * run at O(size)
     */
    private void buildHeap() {
        for (int k = size / 2; k > 0; k--) {
            percolatingDown(k);
        }
    }
    /**
     * Construct the binary heap given an array of items.
     */
    private void percolatingDown(int k) {
        AnyType tmp = heap[k];
        int child;
        for (; 2 * k <= size; k = child) {
            child = 2 * k;
            if (child != size && heap[child].compareTo(heap[child + 1]) > 0)
                child++;
            if (tmp.compareTo(heap[child]) > 0) {
                heap[k] = heap[child];
            } else
                break;
        }
        heap[k] = tmp;
    }

    /**
     * delete the top item
     * 
     * @return
     * @throws RuntimeException
     */
    public AnyType deleteMin() throws RuntimeException {
        if (size == 0)
            throw new RuntimeException();

        AnyType min = heap[1];
        heap[1] = heap[size--];
        percolatingDown(1);
        return min;
    }

    /**
     * sort a given array of items
     * 
     * @param array
     */
    public AnyType[] heapSort(AnyType[] array) {
        // 1.build up heap
        size = array.length;
        heap = (AnyType[]) new Comparable[size + 1];
        System.arraycopy(array, 0, heap, 1, size);
        buildHeap();

        // deleteMin
        for (int i = size; i > 0; i--) {
            // swap the min with last element
            AnyType tmp = heap[i];
            heap[i] = heap[1];
            heap[1] = tmp;
            size--;

            percolatingDown(1);
        }
        for (int i = 0; i < heap.length - 1; i++) {
            array[i] = heap[heap.length - 1 - i];// reverse
        }
        return array;
    }
    /**
     * Insert a new item
     */

    public void insert(AnyType x) {
        // 1.check the size
        if (size == heap.length - 1)
            doubleSize();
        // Insert a new item to the end of the array
        int pos = ++size;

        // 2.percolate up
        for (; pos > 1 && x.compareTo(heap[pos / 2]) < 0; pos = pos / 2)
            heap[pos] = heap[pos / 2];

        heap[pos] = x;
    }

    private void doubleSize() {
        AnyType[] old = heap;
        heap = (AnyType[]) new Comparable[heap.length * 2];
        System.arraycopy(old, 1, heap, 1, size);

    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 1; i <= size; i++) {
            out += heap[i] + " ";
        }
        return out;
    }

    public static void main(String[] args) {
        Heap<String> h = new Heap<String>();
        h.insert("p");
        h.insert("r");
        h.insert("i");
        h.insert("o");
        h.insert("a");
        h.insert("b");
        h.insert("z");
        h.insert("x");
        h.insert("v");

        System.out.println(h);

        Heap<Integer> tmp = new Heap<Integer>();
        Integer[] a = { 3, 1, 6, 5, 2, 4 };
        a = tmp.heapSort(a);
        System.out.println(tmp);
        System.out.println(Arrays.toString(a));
    }

}
