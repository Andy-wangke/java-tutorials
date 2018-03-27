package com.it.patterns.builder;

public class Actor {

    private String type;// 角色类型
    private String sex;
    private String face;
    private String costume;// clothes
    private String hairStyle;

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * @param sex
     *            the sex to set
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * @return the face
     */
    public String getFace() {
        return face;
    }

    /**
     * @param face
     *            the face to set
     */
    public void setFace(String face) {
        this.face = face;
    }

    /**
     * @return the costume
     */
    public String getCostume() {
        return costume;
    }

    /**
     * @param costume
     *            the costume to set
     */
    public void setCostume(String costume) {
        this.costume = costume;
    }

    /**
     * @return the hairStyle
     */
    public String getHairStyle() {
        return hairStyle;
    }

    /**
     * @param hairStyle
     *            the hairStyle to set
     */
    public void setHairStyle(String hairStyle) {
        this.hairStyle = hairStyle;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Actor [type=" + type + ", sex=" + sex + ", face=" + face + ", costume=" + costume + ", hairStyle=" + hairStyle + "]";
    }

}
