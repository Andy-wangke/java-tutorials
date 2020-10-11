package com.it.biz.util.xml.model;

import java.util.ArrayList;

public class Question {
    private long questionId;
    private String questionIdStr;
    
    private ArrayList<Choice> choices;
    
    private int index;
    private boolean required = false;
    private WebformQuestion webformQuestion = null;
}
