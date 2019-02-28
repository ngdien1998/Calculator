package com.mobileprograming.g4.calculator.businesslogic;

import org.mariuszgromada.math.mxparser.Expression;

public class Calculation {

    private static Expression expression = new Expression();

    private Calculation() {
    }

    public static String calculate(String expString) {
        expression.setExpressionString(expString);
        double res = expression.calculate();
        return String.valueOf(res);
    }

    public static void saveExpression(String expString) {

    }
}