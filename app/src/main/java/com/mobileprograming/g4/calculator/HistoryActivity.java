package com.mobileprograming.g4.calculator;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mobileprograming.g4.calculator.adapters.ViewPageAdapter;
import com.mobileprograming.g4.calculator.business.ExpressionsCalculateService;
import com.mobileprograming.g4.calculator.fragments.AllExpressionsFragment;
import com.mobileprograming.g4.calculator.fragments.ItemClearedCallback;
import com.mobileprograming.g4.calculator.fragments.SavedExpressionsFragment;

import java.io.IOException;
import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {

    private ItemClearedCallback historyExpressionsClear;
    private ItemClearedCallback savedExpressionsClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        settingLayout();
    }

    private void settingLayout() {
        Toolbar toolbarMain = findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbarMain);

        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("History");

        toolbarMain.setNavigationOnClickListener(view -> finish());

        ViewPageAdapter pageAdapter = new ViewPageAdapter(getSupportFragmentManager());

        AllExpressionsFragment allExpressionsFragment = new AllExpressionsFragment();
        Bundle bundle = allExpressionsFragment.getArguments();
        if (bundle != null) {
            historyExpressionsClear = (ItemClearedCallback) bundle.getSerializable("callback");
        }

        pageAdapter.addFragment("All expressions", allExpressionsFragment);

        SavedExpressionsFragment savedExpressionsFragment = new SavedExpressionsFragment();
        bundle = savedExpressionsFragment.getArguments();
        if (bundle != null) {
            savedExpressionsClear = (ItemClearedCallback) bundle.getSerializable("callback");
        }

        pageAdapter.addFragment("Saved expressions", savedExpressionsFragment);

        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager pgrMain = findViewById(R.id.pgrMain);

        pgrMain.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(pgrMain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_expression_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            switch (item.getItemId()) {
                case R.id.item_clear_all_history:
                    ExpressionsCalculateService.getInstance(getBaseContext()).clearHistoryExpressions();
                    historyExpressionsClear.onCleared();
                    return true;
                case R.id.item_clear_all_saved_history:
                    ExpressionsCalculateService.getInstance(getBaseContext()).clearSavedExpressions();
                    savedExpressionsClear.onCleared();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
            return false;
        }
    }
}