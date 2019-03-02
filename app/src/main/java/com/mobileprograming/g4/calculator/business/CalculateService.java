package com.mobileprograming.g4.calculator.business;

import com.mobileprograming.g4.calculator.exceptions.InvalidExpressionFormatException;

public interface CalculateService {
    String calculate(String expression) throws InvalidExpressionFormatException;
}
