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

public class AllExpressionsFragment extends Fragment {

    private RecyclerView rclAllExps;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_expressions, container, false);

        rclAllExps = view.findViewById(R.id.rclAllExps);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rclAllExps.setHasFixedSize(true);
        rclAllExps.setLayoutManager(layoutManager);

        return view;
    }
}