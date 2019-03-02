package com.mobileprograming.g4.calculator.business;

import android.content.Context;

import com.mobileprograming.g4.calculator.databaseaccess.ExpressionSaving;
import com.mobileprograming.g4.calculator.exceptions.InvalidExpressionFormatException;
import com.mobileprograming.g4.calculator.models.HistoryExpression;
import com.mobileprograming.g4.calculator.models.SavedExpression;

import org.mariuszgromada.math.mxparser.Expression;

import java.io.IOException;
import java.net.ContentHandler;
import java.util.ArrayList;

public class ExpressionsCalculateService implements CalculateService, ExpressionsService {

    private Expression expression;
    private ExpressionSaving databaseHelper;

    private static ExpressionsCalculateService instance = null;

    private ExpressionsCalculateService(Context context) throws IOException {
        expression = new Expression();
        databaseHelper = new ExpressionSaving(context);
    }

    public static ExpressionsCalculateService getInstance(Context context) throws IOException {
        if (instance == null) {
            instance = new ExpressionsCalculateService(context);
        }
        return instance;
    }

    @Override
    public String calculate(String expression) throws InvalidExpressionFormatException {
        if (!isValidExpressionFormat(expression)) {
            throw new InvalidExpressionFormatException("Expression format is not valid");
        }
        this.expression.setExpressionString(expression);
        double result = this.expression.calculate();
        return String.valueOf(result);
    }

    private boolean isValidExpressionFormat(String expression) {
        // TODO check expression format valid
        return false;
    }

    @Override
    public ArrayList<SavedExpression> getSavedExpressions() {
        return null;
    }

    @Override
    public ArrayList<HistoryExpression> getHistoryExpressions() {
        return null;
    }

    @Override
    public void saveExpression(SavedExpression expression) {

    }

    @Override
    public void saveHistoryExpression(HistoryExpression expression) {

    }
}