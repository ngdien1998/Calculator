package com.mobileprograming.g4.calculator.business;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

import com.mobileprograming.g4.calculator.databaseaccess.ExpressionSaving;
import com.mobileprograming.g4.calculator.exceptions.InvalidExpressionFormatException;
import com.mobileprograming.g4.calculator.models.HistoryExpression;
import com.mobileprograming.g4.calculator.models.SavedExpression;

import org.mariuszgromada.math.mxparser.Expression;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ExpressionsCalculateService implements CalculateService, ExpressionsService {

    private Expression expression;
    private ExpressionSaving databaseHelper;

    private static ExpressionsCalculateService instance = null;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());

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
        this.expression.setExpressionString(expression);
        double result = this.expression.calculate();
        if (String.valueOf(result).toLowerCase().equals("nan")) {
            throw new InvalidExpressionFormatException("Expression format invalid");
        }
        return String.valueOf(result);
    }

    @Override
    public ArrayList<SavedExpression> getSavedExpressions() {
        ArrayList<SavedExpression> expressions = new ArrayList<>();
        try {
            Cursor cursor = databaseHelper.readSavedExpressions();
            while (cursor.moveToNext()) {
                SavedExpression expression = new SavedExpression();

                expression.setId(cursor.getInt(0));
                expression.setTitle(cursor.getString(1));
                expression.setSavedTime(dateFormat.parse(cursor.getString(2)));
                expression.setExpression(cursor.getString(3));
                expression.setResult(cursor.getString(4));

                expressions.add(expression);
            }
        } catch (SQLException e) {
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return expressions;
    }

    @Override
    public ArrayList<HistoryExpression> getHistoryExpressions() {
        ArrayList<HistoryExpression> expressions = new ArrayList<>();
        try {
            Cursor cursor = databaseHelper.readHistoryExpressions();
            while (cursor.moveToNext()) {
                HistoryExpression expression = new HistoryExpression();

                expression.setId(cursor.getInt(0));
                expression.setExpression(cursor.getString(1));
                expression.setResult(cursor.getString(2));

                expressions.add(expression);
            }
        } catch (SQLException e) {
            return null;
        }
        return expressions;
    }

    @Override
    public boolean saveExpression(SavedExpression expression) {
        try {
            databaseHelper.saveExpression(expression);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean saveHistoryExpression(HistoryExpression expression) {
        try {
            databaseHelper.saveHistoryExpression(expression);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean clearSavedExpressions() {
        try {
            databaseHelper.clearSavedExpressions();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean clearHistoryExpressions() {
        try {
            databaseHelper.clearHistoryExpressions();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteHistoryExpression(int id) {
        try
        {
            databaseHelper.deleteHistoryExpression(id);
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }

    }

    @Override
    public boolean deleteSaveExpression(int id) {
        try
        {
            databaseHelper.deleteSavedExpression(id);
            return true;
        }
        catch (SQLException e)
        {
            return false;
        }
    }
}