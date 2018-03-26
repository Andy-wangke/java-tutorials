package com.it.array;

import java.util.Arrays;


/*************************************************************************
 * Demonstrates copying arrays of references
 *************************************************************************/
public class ArrayCopyReferences {

    public static void main(String[] args) {
        Object[] obj1 = { new Integer(10), new StringBuffer("foobar"), new Double(12.95) };

        Object[] obj2 = new Object[obj1.length];

        // 1.copy by loop
        // for (int i = 0; i < obj2.length; i++) {
        // obj2[i] = obj1[i];
        // }

        // 2.copy by Arrays.copyOf()
        // obj2 = Arrays.copyOf(obj1, obj1.length);

        // 3.copy by System.arrayCopy()
        // System.arraycopy(obj1, 0, obj2, 0, obj1.length);

        // 4.copy by Object.clone()
        // obj2 = obj1.clone();

        obj1[0] = new Integer(5);

        System.out.println(Arrays.toString(obj1));
        System.out.println(Arrays.toString(obj2));
        System.out.println();

        ((StringBuffer) obj1[1]).append('s');

        System.out.println(Arrays.toString(obj1));
        System.out.println(Arrays.toString(obj2));

    }
}
