package com.it.hashing;


public class HashCodeDemo {

    public static void main(String[] args) {
        Integer obj1 = new Integer(2009);
        System.out.println("hashcode for an integer " + obj1.hashCode());

        String obj2 = new String("2009");
        System.out.println("hashcode for a String " + obj2.hashCode());

        StringBuffer obj3 = new StringBuffer("2009");
        System.out.println("hashcode for a StringBuffer" + obj3.hashCode());

        String obj7 = new String("19999999999999999");
        System.out.println("\nhashCode can be negative " + obj7.hashCode());
    }
}
