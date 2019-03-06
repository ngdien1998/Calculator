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
import com.mobileprograming.g4.calculator.models.HistoryExpression;

import java.util.ArrayList;

public class AllExpressionsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_expressions, container, false);

        RecyclerView rclAllExps = view.findViewById(R.id.rclAllExps);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rclAllExps.setHasFixedSize(true);
        rclAllExps.setLayoutManager(layoutManager);

        ArrayList<HistoryExpression> expressions = new ArrayList<>();
        expressions.add(new HistoryExpression("19833-9273+7827", "-8739.25741217576"));
        expressions.add(new HistoryExpression("928*374/53", "6548.52830188679"));
        expressions.add(new HistoryExpression("(40+3)/2", "21.5"));
        expressions.add(new HistoryExpression("sqrt(100)/4", "2.5"));
        expressions.add(new HistoryExpression("(2^100)!", "NaN"));
        expressions.add(new HistoryExpression("6!+4*cosh(0.837398)", "725.486365842776"));
        expressions.add(new HistoryExpression("4^20/10^3", "1099511627.776"));
        expressions.add(new HistoryExpression("122*3+(1946/7)-9384+sin(0.836926)", "-8739.25741217576"));

        HistoryExpressionsAdapter adapter = new HistoryExpressionsAdapter(getContext(), expressions);
        rclAllExps.setAdapter(adapter);

        return view;
    }
}