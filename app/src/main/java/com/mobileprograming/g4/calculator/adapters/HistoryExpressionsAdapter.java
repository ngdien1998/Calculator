package com.mobileprograming.g4.calculator.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobileprograming.g4.calculator.R;
import com.mobileprograming.g4.calculator.business.ExpressionsCalculateService;
import com.mobileprograming.g4.calculator.fragments.ExpressionDeletedCallback;
import com.mobileprograming.g4.calculator.models.HistoryExpression;
import com.mobileprograming.g4.calculator.models.SavedExpression;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class HistoryExpressionsAdapter extends RecyclerView.Adapter<HistoryExpressionsAdapter.ViewHolder> {

    private ArrayList<? extends HistoryExpression> expressions;
    private ExpressionsCalculateService calculateService;
    private ExpressionDeletedCallback deletedCallback;
    private Context context;

    private LayoutInflater inflater;
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm", Locale.getDefault());

    public HistoryExpressionsAdapter(Context context, ArrayList<? extends HistoryExpression> expressions, ExpressionDeletedCallback deletedCallback) throws IOException {
        this.expressions = expressions;
        this.context= context;
        this.deletedCallback = deletedCallback;
        calculateService = ExpressionsCalculateService.getInstance(context);
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

        viewHolder.btnDelete.setOnClickListener(view -> {
            int id = expression.getId();

            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            dialogBuilder.setTitle("Delete expression");
            dialogBuilder.setMessage("This won't be able to undo. Are you sure to delele it from history?");
            dialogBuilder.setCancelable(true);
            dialogBuilder.setNegativeButton("Delete", (dialog, which) -> {
                if (expression instanceof SavedExpression) {
                    calculateService.deleteSaveExpression(id);
                    deletedCallback.onDeleted();
                } else {
                    calculateService.deleteHistoryExpression(id);
                    deletedCallback.onDeleted();
                }
            });
            dialogBuilder.setPositiveButton("Cancel", (dialog, which) -> dialog.dismiss());

            dialogBuilder.show();
        });
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
        ImageButton btnDelete;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtExpression = itemView.findViewById(R.id.txtExpression);
            txtTime = itemView.findViewById(R.id.txtTime);
            txtResult = itemView.findViewById(R.id.txtResult);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
