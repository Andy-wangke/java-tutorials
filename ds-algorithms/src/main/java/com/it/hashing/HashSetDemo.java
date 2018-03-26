/*************************************************************************
 * Demonstrates working with Hashsets
 *************************************************************************/
package com.it.hashing;

import java.util.HashSet;
import java.util.Iterator;


/**
 * @since
 */
public class HashSetDemo {

    public static void main(String[] args) {
        String[] colors = { "white", "pink", "green", "red", "orange", "Aa", "BB" };

        HashSet<String> hs = new HashSet<String>();

        for (int i = 0; i < colors.length; i++) {
            hs.add(colors[i]);
        }

        System.out.println(hs);

        System.out.println("Does it contain green?" + hs.contains("green"));

        Iterator<String> iterator = hs.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + "  ");
        }
        System.out.println();

        for (String string : hs) {
            System.out.print(string + "  ");
        }
        System.out.println();
    }
}
