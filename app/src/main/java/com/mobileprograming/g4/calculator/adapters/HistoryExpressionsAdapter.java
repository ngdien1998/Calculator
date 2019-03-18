package com.mobileprograming.g4.calculator.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobileprograming.g4.calculator.R;
import com.mobileprograming.g4.calculator.models.HistoryExpression;
import com.mobileprograming.g4.calculator.models.SavedExpression;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HistoryExpressionsAdapter extends RecyclerView.Adapter<HistoryExpressionsAdapter.ViewHolder> {

    private ArrayList<? extends HistoryExpression> expressions;

    private LayoutInflater inflater;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());

    public HistoryExpressionsAdapter(Context context, ArrayList<? extends HistoryExpression> expressions) {
        this.expressions = expressions;

        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View itemExp = inflater.inflate(R.layout.item_expression, viewGroup, false);
        return new HistoryExpressionsAdapter.ViewHolder(itemExp);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        HistoryExpression expression = expressions.get(position);

        viewHolder.txtExpression.setText(expression.getExpression());
        viewHolder.txtResult.setText(String.format("=%s", expression.getResult()));

        if (expression instanceof SavedExpression) {
            SavedExpression savedExpression = (SavedExpression) expression;

            viewHolder.txtTitle.setText(savedExpression.getTitle());
            viewHolder.txtTime.setText(dateFormat.format(savedExpression.getSavedTime()));
        }
    }

    @Override
    public int getItemCount() {
        return expressions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtExpression;
        TextView txtTime;
        TextView txtResult;

        ViewHolder(@NonNull View itemView) {
            super(itemView);


        }
    }
}
