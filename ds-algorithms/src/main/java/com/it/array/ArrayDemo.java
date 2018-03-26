package com.it.array;

import java.util.Arrays;
import java.util.Random;

/*************************************************************************
 * Demonstrates insertion and deletion from an array of primitives.
 *************************************************************************/
public class ArrayDemo {

    private char[] data;

    public ArrayDemo(int size) {
        // generates an array of random letters
        Random random = new Random();
        data = new char[size];

        for (int i = 0; i < size; i++) {
            data[i] = (char) ('a' + random.nextInt(26));
        }
    }

    // remove an item at the specified position
    public void delete(int pos) {
        if (pos > 0 && pos < data.length) {
            char[] tmp = new char[data.length - 1];

            System.arraycopy(data, 0, tmp, 0, pos);
            System.arraycopy(data, pos + 1, tmp, pos, data.length - pos - 1);
            data = tmp;
        }
    }

    // remove an item at the specified position and returns the new array
    public char[] delete(char[] data, int pos) {
        if (pos > 0 && pos < data.length) {
            char[] tmp = new char[data.length - 1];
            System.arraycopy(data, 0, tmp, 0, pos);
            System.arraycopy(data, pos + 1, tmp, pos, data.length - pos - 1);
            return tmp;
        } else
        return data;
    }

    // insert an item at the specified position
    public void insert(int pos, char x) {
        // validation check
        if (pos > 0 && pos < data.length) {
            char[] tmp = new char[data.length + 1];
            System.arraycopy(data, 0, tmp, 0, pos);
            System.arraycopy(data, pos, tmp, pos + 1, data.length - pos);
            tmp[pos] = x;
            data = tmp;
        }
    }

    // string representation for this class
    public String toString() {
        return "ArrayDemo [data=" + Arrays.toString(data) + "]";
    }

    public static void main(String[] args) {
        ArrayDemo arrayDemo = new ArrayDemo(10);
        // print
        System.out.println(arrayDemo);

        // remove at position index 5
        System.out.println("\ndelete at index 5");
        arrayDemo.delete(5);

        System.out.println(arrayDemo + "\n");

        System.out.println("\ndelete at index 5");
        arrayDemo.data = arrayDemo.delete(arrayDemo.data, 5);
        System.out.println(arrayDemo + "\n");

        // insert
        arrayDemo.insert(5, 'x');
        System.out.println(arrayDemo);
    }
}
