package com.mobileprograming.g4.calculator.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobileprograming.g4.calculator.R;
import com.mobileprograming.g4.calculator.adapters.HistoryExpressionsAdapter;
import com.mobileprograming.g4.calculator.business.ExpressionsCalculateService;
import com.mobileprograming.g4.calculator.models.SavedExpression;

import java.io.IOException;
import java.util.ArrayList;

public class SavedExpressionsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_expressions, container, false);

        RecyclerView rclAllExps = view.findViewById(R.id.rclAllExps);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rclAllExps.setHasFixedSize(true);
        rclAllExps.setLayoutManager(layoutManager);

        try {
            ExpressionsCalculateService calculatorService = ExpressionsCalculateService.getInstance(getContext());
            ArrayList<SavedExpression> expressions = calculatorService.getSavedExpressions();

            HistoryExpressionsAdapter adapter = new HistoryExpressionsAdapter(getContext(), expressions);
            rclAllExps.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }
}