package com.it.patterns.prototype.deepClone;

import java.io.IOException;


public class Client {

    public static void main(String[] args) {

        WeeklyReport report_old = new WeeklyReport();
        report_old.setName("13th week");
        Attachment attachment = new Attachment();
        report_old.setAttachment(attachment);

        WeeklyReport report_new = null;
        try {
            report_new = report_old.deepClone();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(report_old == report_new);
        System.out.println(report_old.getAttachment() == report_new.getAttachment());
    }
}
