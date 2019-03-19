package com.mobileprograming.g4.calculator.business;

import com.mobileprograming.g4.calculator.models.HistoryExpression;
import com.mobileprograming.g4.calculator.models.SavedExpression;

import java.util.ArrayList;

public interface ExpressionsService {
    ArrayList<SavedExpression> getSavedExpressions();
    ArrayList<HistoryExpression> getHistoryExpressions();
    boolean saveExpression(SavedExpression expression);
    boolean saveHistoryExpression(HistoryExpression expression);
    boolean clearSavedExpressions();
    boolean clearHistoryExpressions();
    boolean deleteHistoryExpression(int id);
    boolean deleteSaveExpression(int id);
}
