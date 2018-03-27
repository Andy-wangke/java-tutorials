package com.it.patterns.prototype.shallowClone;

/**
 * <pre>
 * java中分为基本数据类型与引用数据类型；深浅拷贝表现的差异在是否支持引用数据类型的拷贝上，
 * 即浅拷贝只会拷贝引用数据类型的地址，that is ,共用一个堆对象
 * </pre>
 * 
 * @author ke.wang@hpe.com
 */

public class WeeklyReport implements Cloneable {


    private int week;
    private String name;
    private String date;
    private String content;

    /**
     * @return the week
     */
    public int getWeek() {
        return week;
    }

    /**
     * @param week
     *            the week to set
     */
    public void setWeek(int week) {
        this.week = week;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date
     *            the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     *            the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    protected WeeklyReport shallowClone() throws CloneNotSupportedException {
        Object obj;
        try {
            obj = super.clone();
            return (WeeklyReport) obj;
        } catch (CloneNotSupportedException e) {
            System.out.println("CloneNotSupported");
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WeeklyReport [week=" + week + ", name=" + name + ", date=" + date + ", content=" + content + "]";
    }

}
