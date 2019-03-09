package com.mobileprograming.g4.calculator.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mobileprograming.g4.calculator.HistoryActivity;
import com.mobileprograming.g4.calculator.R;

import java.util.Objects;

public class CalculatorFragment extends Fragment {

    private static final String IS_ON_RAD_MODE = "mIsOnRadMode";

    private Button btnNum0, btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9,
            btnPlusMinus, btnEqual, btnPlus, btnMinus, btnMultifly, btnDevide,
            btnDot, btnPercent, btnParentheses, btnClear,
            btnE_FactorialOfX, btnPi_CubeOfX, btnAbsX_2PowersX, btnXPowersN_TanhPowersMinus1,
            btnXPowers2_CoshPowersMinus1, btnEPowersN_SinhPowersMinus1, btnLn_Sinh, btnLog_Cosh,
            btn1DevideX_Tanh, btnTan_Arctan, btnCos_Arccos, btnSin_Arcsin, btnSquareRoot_CubeRoot,
            btnRad, btnMore;
    private ImageButton btnRotate, btnBackspace, btnHistory, btnSave;
    private EditText edtExpression;
    private TextView txtResult;
    private TextView txtRad;

    private TextInputLayout tilTitle;
    private EditText edtTitle;

    private boolean mIsPortrait;
    private AppCompatActivity mParent;
    private boolean mRadModeIsOn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);

        mParent = ((AppCompatActivity) getContext());
        mIsPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;

        mapControls(view);
        addEvents();

        mRadModeIsOn = false;
        // Check on the last session whether rad mode is saved or not?
        if (savedInstanceState != null) {
            mRadModeIsOn = savedInstanceState.getBoolean(IS_ON_RAD_MODE);
        }

        // Disable keyboard when focusing on the edittext
        edtExpression.setShowSoftInputOnFocus(false);

        return view;
    }

    /**
     * Mapping view controls from view to activity
     * @param view View that contains mapped view controls
     */
    private void mapControls(View view) {
        btnHistory = view.findViewById(R.id.btnHistory);
        btnRotate = view.findViewById(R.id.btnRotate);
        btnSave = view.findViewById(R.id.btnSave);
        btnBackspace = view.findViewById(R.id.btnBackspace);
        edtExpression = view.findViewById(R.id.edtExpression);
        txtResult = view.findViewById(R.id.txtResult);
        txtRad = view.findViewById(R.id.txtRad);

        btnNum0 = view.findViewById(R.id.btnNum0);
        btnNum1 = view.findViewById(R.id.btnNum1);
        btnNum2 = view.findViewById(R.id.btnNum2);
        btnNum3 = view.findViewById(R.id.btnNum3);
        btnNum4 = view.findViewById(R.id.btnNum4);
        btnNum5 = view.findViewById(R.id.btnNum5);
        btnNum6 = view.findViewById(R.id.btnNum6);
        btnNum7 = view.findViewById(R.id.btnNum7);
        btnNum8 = view.findViewById(R.id.btnNum8);
        btnNum9 = view.findViewById(R.id.btnNum9);
        btnPlusMinus = view.findViewById(R.id.btnPlusMinus);
        btnEqual = view.findViewById(R.id.btnEqual);
        btnPlus = view.findViewById(R.id.btnPlus);
        btnMinus = view.findViewById(R.id.btnMinus);
        btnMultifly = view.findViewById(R.id.btnMultifly);
        btnDevide = view.findViewById(R.id.btnDevide);
        btnDot = view.findViewById(R.id.btnDot);
        btnPercent = view.findViewById(R.id.btnPercent);
        btnParentheses = view.findViewById(R.id.btnParentheses);
        btnClear = view.findViewById(R.id.btnClear);

        btnE_FactorialOfX = view.findViewById(R.id.btnE_FactorialOfX);
        btnPi_CubeOfX = view.findViewById(R.id.btnPi_CubeOfX);
        btnAbsX_2PowersX = view.findViewById(R.id.btnAbsX_2PowersX);
        btnXPowersN_TanhPowersMinus1 = view.findViewById(R.id.btnXPowersN_TanhPowersMinus1);
        btnXPowers2_CoshPowersMinus1 = view.findViewById(R.id.btnXPowers2_CoshPowersMinus1);
        btnEPowersN_SinhPowersMinus1 = view.findViewById(R.id.btnEPowersN_SinhPowersMinus1);
        btnLn_Sinh = view.findViewById(R.id.btnLn_Sinh);
        btnLog_Cosh = view.findViewById(R.id.btnLog_Cosh);
        btn1DevideX_Tanh = view.findViewById(R.id.btn1DevideX_Tanh);
        btnTan_Arctan = view.findViewById(R.id.btnTan_Arctan);
        btnCos_Arccos = view.findViewById(R.id.btnCos_Arccos);
        btnSin_Arcsin = view.findViewById(R.id.btnSin_Arcsin);
        btnSquareRoot_CubeRoot = view.findViewById(R.id.btnSquareRoot_CubeRoot);
        btnRad = view.findViewById(R.id.btnRad);
        btnMore = view.findViewById(R.id.btnMore);
    }

    /**
     * Add events for view controls
     */
    private void addEvents() {
        btnHistory.setOnClickListener(this::btnHistoryOnClick);
        btnBackspace.setOnClickListener(this::btnBackspaceOnClick);
        btnSave.setOnClickListener(this::btnSaveOnClick);

        btnNum0.setOnClickListener(this::btnNum0OnClick);
        btnNum1.setOnClickListener(this::btnNum1OnClick);
        btnNum2.setOnClickListener(this::btnNum2OnClick);
        btnNum3.setOnClickListener(this::btnNum3OnClick);
        btnNum4.setOnClickListener(this::btnNum4OnClick);
        btnNum5.setOnClickListener(this::btnNum5OnClick);
        btnNum6.setOnClickListener(this::btnNum6OnClick);
        btnNum7.setOnClickListener(this::btnNum7OnClick);
        btnNum8.setOnClickListener(this::btnNum8OnClick);
        btnNum9.setOnClickListener(this::btnNum9OnClick);
        btnPlusMinus.setOnClickListener(this::btnPlusMinusOnClick);
        btnEqual.setOnClickListener(this::btnEqualOnClick);
        btnPlus.setOnClickListener(this::btnPlusOnClick);
        btnMinus.setOnClickListener(this::btnMinusOnClick);
        btnMultifly.setOnClickListener(this::btnMultiflyOnClick);
        btnDevide.setOnClickListener(this::btnDevideOnClick);
        btnDot.setOnClickListener(this::btnDotOnClick);
        btnPercent.setOnClickListener(this::btnPercentOnClick);
        btnParentheses.setOnClickListener(this::btnParenthesesOnClick);
        btnClear.setOnClickListener(this::btnClearOnClick);

        if (!mIsPortrait) {
            // Screen orientation is landscape
            btnE_FactorialOfX.setOnClickListener(this::btnE_FactorialOfXOnClick);
            btnPi_CubeOfX.setOnClickListener(this::btnPi_CubeOfXOnClick);
            btnAbsX_2PowersX.setOnClickListener(this::btnAbsX_2PowersXOnClick);
            btnXPowersN_TanhPowersMinus1.setOnClickListener(this::btnXPowersN_TanhPowersMinus1OnClick);
            btnXPowers2_CoshPowersMinus1.setOnClickListener(this::btnXPowers2_CoshPowersMinus1OnClick);
            btnEPowersN_SinhPowersMinus1.setOnClickListener(this::btnEPowersN_SinhPowersMinus1OnClick);
            btnLn_Sinh.setOnClickListener(this::btnLn_SinhOnClick);
            btnLog_Cosh.setOnClickListener(this::btn1DevideX_TanhOnClick);
            btn1DevideX_Tanh.setOnClickListener(this::btn1DevideX_TanhOnClick);
            btnTan_Arctan.setOnClickListener(this::btnTan_ArctanOnClick);
            btnCos_Arccos.setOnClickListener(this::btnCos_ArccosOnClick);
            btnSin_Arcsin.setOnClickListener(this::btnSin_ArcsinOnClick);
            btnSquareRoot_CubeRoot.setOnClickListener(this::btnSquareRoot_CubeRootOnClick);
            btnRad.setOnClickListener(this::btnRadOnClick);
            btnMore.setOnClickListener(this::btnMoreOnClick);
        }

        try {
            if (android.provider.Settings.System.getInt(mParent.getContentResolver(),
                    Settings.System.ACCELEROMETER_ROTATION) == 0) {
                // System setting disables auto rotation
                btnRotate.setOnClickListener(this::btnRotateOnClick);
                btnRotate.setVisibility(View.VISIBLE);
            } else {
                // Auto rotation is enabled
                btnRotate.setVisibility(View.INVISIBLE);
            }
        } catch (Settings.SettingNotFoundException e) {
            Log.e("ERROR_ROTATION", e.getMessage());
        }
    }

    /**
     * Handle button btnRotate click event
     * @param view btnSave
     */
    private void btnSaveOnClick(View view) {
        LayoutInflater inflater = getLayoutInflater();

        @SuppressLint("InflateParams")
        View enterTitleDialog = inflater.inflate(R.layout.layout_dialog, null);

        tilTitle = enterTitleDialog.findViewById(R.id.tilTitle);
        edtTitle = enterTitleDialog.findViewById(R.id.edtTitle);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Objects.requireNonNull(getContext()));
        dialogBuilder.setTitle("Enter expression's title");
        dialogBuilder.setView(enterTitleDialog);
        dialogBuilder.setCancelable(false);
        dialogBuilder.setNegativeButton("Cancel", this::dialogOnNegativeButtonClick);
        dialogBuilder.setPositiveButton("Save", this::dialogOnPositiveButtonClick);

        dialogBuilder.show();
    }

    private void dialogOnPositiveButtonClick(DialogInterface dialog, int which) {
        dialog.dismiss();
    }

    private void dialogOnNegativeButtonClick(DialogInterface dialog, int which) {
        String title = edtTitle.getText().toString().trim();
        if (title.isEmpty()) {
            tilTitle.setError("The title cannot be empty");
            return;
        }

        tilTitle.setErrorEnabled(false);
        // TODO insert saved expression into database
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(IS_ON_RAD_MODE, mRadModeIsOn);
    }

    /**
     * Set expression for textview txtExpression
     */
    private void setExpression(String expression) {
        edtExpression.setText(expression);
    }

    /**
     * Get expression from textview txtExpression
     */
    private String getExpression() {
        return edtExpression.getText().toString();
    }

    /**
     * Get current cursor position from textview txtExpression
     */
    private int getCursorPosition() {
        return edtExpression.getSelectionStart();
    }

    /**
     * Set result for textview txtResult
     */
    private void setResult(String result) {
        txtResult.setText(result);
    }

    /**
     * Handle button btnRotate click event
     * @param view btnBackspace
     */
    private void btnBackspaceOnClick(View view) {
        String ex = getExpression();
        setExpression(ex.substring(0, ex.length() - 2));
    }

    /**
     * Handle button btnRotate click event
     * @param view btnRotate
     */
    private void btnRotateOnClick(View view) {
        assert mParent != null;
        if (mIsPortrait) {
            mParent.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            mParent.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    /**
     * Handle button btnHistory click event
     * @param view btnHistory
     */
    private void btnHistoryOnClick(View view) {
        Intent intent = new Intent(getContext(), HistoryActivity.class);
        startActivity(intent);
    }

    private void btnNum0OnClick(View view) {
    }

    private void btnNum1OnClick(View view) {
    }

    private void btnNum2OnClick(View view) {
    }

    private void btnNum3OnClick(View view) {
    }

    private void btnNum4OnClick(View view) {
    }

    private void btnNum5OnClick(View view) {
    }

    private void btnNum6OnClick(View view) {
    }

    private void btnNum7OnClick(View view) {
    }

    private void btnNum8OnClick(View view) {
    }

    private void btnNum9OnClick(View view) {
    }

    private void btnPlusMinusOnClick(View view) {
    }

    private void btnEqualOnClick(View view) {
    }

    private void btnPlusOnClick(View view) {
    }

    private void btnMinusOnClick(View view) {
    }

    private void btnMultiflyOnClick(View view) {
    }

    private void btnDevideOnClick(View view) {
    }

    private void btnDotOnClick(View view) {
    }

    private void btnPercentOnClick(View view) {
    }

    private void btnParenthesesOnClick(View view) {
    }

    private void btnClearOnClick(View view) {
    }

    private void btnE_FactorialOfXOnClick(View view) {
    }

    private void btnPi_CubeOfXOnClick(View view) {
    }

    private void btnAbsX_2PowersXOnClick(View view) {
    }

    private void btnXPowersN_TanhPowersMinus1OnClick(View view) {
    }

    private void btnXPowers2_CoshPowersMinus1OnClick(View view) {
    }

    private void btnEPowersN_SinhPowersMinus1OnClick(View view) {
    }

    private void btnLn_SinhOnClick(View view) {
    }

    private void btn1DevideX_TanhOnClick(View view) {
    }

    private void btnTan_ArctanOnClick(View view) {
    }

    private void btnCos_ArccosOnClick(View view) {
    }

    private void btnSin_ArcsinOnClick(View view) {
    }

    private void btnSquareRoot_CubeRootOnClick(View view) {
    }

    /**
     * Handle buttun btnRad click event
     * @param view btnRad
     */
    private void btnRadOnClick(View view) {
        if (mRadModeIsOn) {
            txtRad.setVisibility(View.INVISIBLE);
        } else {
            txtRad.setVisibility(View.VISIBLE);
        }
        mRadModeIsOn = !mRadModeIsOn;
    }

    private void btnMoreOnClick(View view) {
    }
}