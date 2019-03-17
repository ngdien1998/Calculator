package com.mobileprograming.g4.calculator.databaseaccess;

import android.database.Cursor;

import com.mobileprograming.g4.calculator.models.HistoryExpression;
import com.mobileprograming.g4.calculator.models.SavedExpression;

public interface ExpressionDatabaseAccess {
    Cursor readHistoryExpressions();
    Cursor readSavedExpressions();
    void saveHistoryExpression(HistoryExpression expression);
    void saveExpression(SavedExpression expression);
    void clearSavedExpressions();
    void clearHistoryExpressions();
}
