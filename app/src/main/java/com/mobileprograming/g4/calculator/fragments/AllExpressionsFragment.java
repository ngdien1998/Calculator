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
import com.mobileprograming.g4.calculator.models.HistoryExpression;

import java.io.IOException;
import java.util.ArrayList;

public class AllExpressionsFragment extends Fragment {

    private ArrayList<HistoryExpression> expressions;
    private HistoryExpressionsAdapter adapter;
    private ExpressionsCalculateService calculatorService;

    public AllExpressionsFragment() {
        ItemClearedCallback callback = this::historyExpressionOnCleared;

        Bundle bundle = new Bundle();
        bundle.putSerializable("callback", callback);
        setArguments(bundle);
    }

    private void historyExpressionOnCleared() {
        expressions.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_expressions, container, false);

        RecyclerView rclAllExps = view.findViewById(R.id.rclAllExps);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rclAllExps.setHasFixedSize(true);
        rclAllExps.setLayoutManager(layoutManager);

        try {
            calculatorService = ExpressionsCalculateService.getInstance(getContext());
            expressions = calculatorService.getHistoryExpressions();

            ExpressionDeletedCallback deletedCallback = this::expressionOnDelete;

            if (expressions != null) {
                adapter = new HistoryExpressionsAdapter(getContext(), expressions, deletedCallback);
                rclAllExps.setAdapter(adapter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    private void expressionOnDelete() {
        expressions.clear();
        expressions.addAll(calculatorService.getHistoryExpressions());
        adapter.notifyDataSetChanged();
    }
}