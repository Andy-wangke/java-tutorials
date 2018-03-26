package com.it.hashing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author ke.wang@hpe.com
 */
public class SetMapDemo {

    public static void main(String[] args) {
        String[] data = new String("nothing is as easy as it looks").split(" ");

        HashSet<String> hs = new HashSet<String>();
        LinkedHashSet<String> lhs = new LinkedHashSet<String>();
        TreeSet<String> ts = new TreeSet<String>();

        HashMap<String, Integer> hm = new HashMap<String, Integer>();
        TreeMap<String, Integer> tm = new TreeMap<String, Integer>();
        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<String, Integer>();

        for (String str : data) {
            Integer freq = hm.get(str);
            hm.put(str, freq == null ? 1 : freq + 1);
            lhm.put(str, freq == null ? 1 : freq + 1);
            tm.put(str, freq == null ? 1 : freq + 1);

            hs.add(str);
            lhs.add(str);
            ts.add(str);

        }

        /* this prints the HashSet */
        System.out.println("Hash  set: " + hs);
        System.out.println();

        /* this prints the hashtable in sorted order */
        System.out.println("Tree set: " + ts);
        System.out.println();

        /* this prints the hashtable in the order items were inserted */
        System.out.println("Linked set: " + lhs);
        System.out.println();

        System.out.println(hm.size() + " distinct words detected:");
        System.out.println();

        System.out.println("Hash map: " + hm);
        System.out.println();

        System.out.println("Tree map: " + tm);
        System.out.println();

        System.out.println("Linked map: " + lhm);
        System.out.println();
    }
}
