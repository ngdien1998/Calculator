package com.mobileprograming.g4.calculator.databaseaccess;

import android.database.Cursor;

public interface ExpressionDatabaseAccess {
    Cursor readAllHistoryExpression();
    Cursor readAllSavedExpression();
    boolean saveHistoryExpression();
    boolean saveExpression();
}
