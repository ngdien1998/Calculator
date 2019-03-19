package com.mobileprograming.g4.calculator.models;

public class HistoryExpression {
    private String expression;
    private String result;
    private  int id;

    public HistoryExpression() {
    }

    public HistoryExpression(String expression, String result) {
        this.expression = expression;
        this.result = result;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
