package com.mobileprograming.g4.calculator.databaseaccess;

import android.content.Context;
import android.database.Cursor;

import com.mobileprograming.g4.calculator.models.HistoryExpression;
import com.mobileprograming.g4.calculator.models.SavedExpression;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ExpressionSaving extends SqliteHelper implements ExpressionDatabaseAccess {

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());

    public ExpressionSaving(Context context) throws IOException {
        super(context);
    }

    @Override
    public Cursor readHistoryExpressions() {
        String query = "SELECT * FROM HistoryExpression";
        return database.rawQuery(query, null);
    }

    @Override
    public Cursor readSavedExpressions() {
        String query = "SELECT * FROM SavedExpression";
        return database.rawQuery(query, null);
    }

    @Override
    public void saveHistoryExpression(HistoryExpression expression) {
        String query = "INSERT INTO HistoryExpression(Expression, Result) VALUES (?, ?)";
        database.execSQL(query, new String[] {expression.getExpression(), expression.getResult()});
    }

    @Override
    public void saveExpression(SavedExpression expression) {
        String query = "INSERT INTO SavedExpression(Title, SavedTime, Expression, Result) VALUES (?, ?, ?, ?)";
        String savedTime = dateFormat.format(expression.getSavedTime());
        database.execSQL(query, new String[] {
                expression.getTitle(),
                savedTime,
                expression.getExpression(),
                expression.getResult()
        });
    }

    @Override
    public void clearSavedExpressions() {
        String query = "DELETE FROM SavedExpression";
        database.execSQL(query);
    }

    @Override
    public void clearHistoryExpressions() {
        String query = "DELETE FROM HistoryExpression";
        database.execSQL(query);
    }

    @Override
    public void deleteHistoryExpression(int id) {
        String query = "DELETE FROM HistoryExpression WHERE ID = ?";
        database.execSQL(query,new Integer[]{id});
    }

    @Override
    public void deleteSavedExpression(int id) {
        String query = "DELETE FROM SavedExpression WHERE ID = ?";
        database.execSQL(query,new Integer[]{id});
    }
}
