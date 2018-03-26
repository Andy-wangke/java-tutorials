package com.it.array;

import java.util.Arrays;


/*************************************************************************
 * Demonstrates three ways of copying arrays of primitives
 *************************************************************************/
public class ArrayCopyPrimitives {

    public static void main(String[] args) {

        int[] obj1 = { 1, 2, 3 };

        // coping by using a loop structure
        System.out.println("coping by using a loop structure....");
        int[] obj2 = new int[obj1.length];
        for (int i = 0; i < obj2.length; i++) {
            obj2[i] = obj1[i];
        }
        System.out.println(Arrays.toString(obj2));

        // coping by using arrayCopy
        System.out.println("coping by using arrayCopy...");
        int[] obj4 = new int[obj1.length];
        System.arraycopy(obj1, 0, obj4, 0, obj1.length);
        System.out.println(Arrays.toString(obj4));

        // coping by using copyOf()
        System.out.println("coping by using copyOf()");
        int[] obj3 = Arrays.copyOf(obj1, obj1.length);
        System.out.println(Arrays.toString(obj3));

        // copying by using clone()
        System.out.println("copying by using clone()....");
        int[] obj5 = (int[]) obj1.clone();
        System.out.println(Arrays.toString(obj5));
    }
}
