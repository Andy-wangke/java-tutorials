/**
 * Copyright 2014 Hewlett-Packard. All rights reserved. <br>
 * HP Confidential. Use is subject to license terms.
 */
package com.it.algorithm;



/**
 * @author ke.wang@hpe.com
 */
public class Fibonacci {

    /**
     * this program is spectacularly inefficient and is meant to illustrate a performance issue.
     * 
     * @param n
     * @return
     */
    private static long fibonacci1(int n) {
        if (n <= 1)
            return n;
        else
            return fibonacci1(n - 1) + fibonacci1(n - 2);
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        int n = 5;
        for (int i = 0; i <= n; i++) {
            System.out.println(i + ":" + fibonacci1(i));
        }
        long endTime = System.currentTimeMillis();

        System.out.println("last for...." + (endTime - startTime));

        int i[] = { 1 };
        example.change_i(i);
        System.out.println(i[0]);
    }
}

class example {

    int i[] = { 0 };

    public static void change_i(int i[]) {
        i[0] = 2;
        i[0] *= 2;
    }
}
