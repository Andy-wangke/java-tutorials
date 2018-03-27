package com.it.patterns.prototype.deepClone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * <pre>
 * 
 * </pre>
 * 
 * @author ke.wang@hpe.com
 */
class Attachment implements Serializable {

    private String name;

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

    public void download() {
        System.out.println("download attachments,which name is " + this.name);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Attachment [name=" + name + "]";
    }

}

public class WeeklyReport implements Serializable {


    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private int week;
    private String name;
    private String date;
    private Attachment attachment;
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

    /**
     * @return the attachment
     */
    public Attachment getAttachment() {
        return attachment;
    }

    /**
     * @param attachment
     *            the attachment to set
     */
    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    protected WeeklyReport deepClone() throws IOException, ClassNotFoundException {
        // 将对象写入流中
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);
        // 将对象从流中取出
        ByteArrayInputStream bai = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bai);
        return (WeeklyReport) ois.readObject();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WeeklyReport [week=" + week + ", name=" + name + ", date=" + date + ", attachment=" + attachment + ", content=" + content + "]";
    }


}
