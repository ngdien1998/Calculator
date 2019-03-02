package com.mobileprograming.g4.calculator.models;

import java.util.Date;

public class SavedExpression {
    private String title;
    private Date savedTime;
    private String expression;
    private String result;

    public SavedExpression() {
    }

    public SavedExpression(String title, Date savedTime, String expression, String result) {
        this.title = title;
        this.savedTime = savedTime;
        this.expression = expression;
        this.result = result;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getSavedTime() {
        return savedTime;
    }

    public void setSavedTime(Date savedTime) {
        this.savedTime = savedTime;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
