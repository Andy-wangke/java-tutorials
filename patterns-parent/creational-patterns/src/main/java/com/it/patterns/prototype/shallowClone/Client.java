package com.it.patterns.prototype.shallowClone;


public class Client {

    public static void main(String[] args) {
        WeeklyReport w1 = new WeeklyReport();
        w1.setWeek(1);
        w1.setName("张无忌");
        w1.setDate("第12周");
        w1.setContent("这周工作很忙，每天加班！");
        System.out.println(w1);

        WeeklyReport w2 = null;
        try {
            w2 = w1.shallowClone();
            System.out.println(w2);
        } catch (CloneNotSupportedException e) {

            e.printStackTrace();
        }
        System.out.println(w1 == w2);
        System.out.println(w1.getWeek() == w2.getWeek());// reference variable is not deep clone
        System.out.println(w1.getName() == w2.getName());
    }
}
