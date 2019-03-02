package com.mobileprograming.g4.calculator.databaseaccess;

import android.content.Context;
import android.database.Cursor;

import java.io.IOException;

public class ExpressionSaving extends SqliteHelper implements ExpressionDatabaseAccess {
    public ExpressionSaving(Context context) throws IOException {
        super(context);
    }

    public Cursor readHistoryExpression() {
        return null;
    }

    @Override
    public Cursor readAllHistoryExpression() {
        return null;
    }

    @Override
    public Cursor readAllSavedExpression() {
        return null;
    }

    @Override
    public boolean saveHistoryExpression() {
        return false;
    }

    @Override
    public boolean saveExpression() {
        return false;
    }
}
