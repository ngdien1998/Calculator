package com.mobileprograming.g4.calculator.business;

import com.mobileprograming.g4.calculator.models.HistoryExpression;
import com.mobileprograming.g4.calculator.models.SavedExpression;

import java.util.ArrayList;

public interface ExpressionsService {
    ArrayList<SavedExpression> getSavedExpressions();
    ArrayList<HistoryExpression> getHistoryExpressions();
    void saveExpression(SavedExpression expression);
    void saveHistoryExpression(HistoryExpression expression);
}
