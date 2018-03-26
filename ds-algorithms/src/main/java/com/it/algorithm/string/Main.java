
package com.it.algorithm.string;


/**
 * @author ke.wang@hpe.com
 */
class prt {

    void makeline(int k) {
        for (int i = 0; i < k; i++) {
            System.out.println("*");
        }
    }
}

public class Main {

    public static void main(String[] args) {
        // write your code here
        // printnow p =new printnow();
        prt p1 = new prt();
        // System.out.println("hello world");
        for (int i = 5; i >= 0; --i) {
            p1.makeline(i);
            for (int j = 0; j < 12; j += 2) {
                p1.makeline(j);
            }

        }
    }

}
