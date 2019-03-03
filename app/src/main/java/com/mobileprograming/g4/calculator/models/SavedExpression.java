package com.mobileprograming.g4.calculator.models;

import java.util.Date;

public class SavedExpression extends HistoryExpression{
    private String title;
    private Date savedTime;

    public SavedExpression() {
    }

    public SavedExpression(String title, Date savedTime, String expression, String result) {
        super(expression, result);
        this.title = title;
        this.savedTime = savedTime;
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
}
