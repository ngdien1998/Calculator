package com.mobileprograming.g4.calculator;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.mobileprograming.g4.calculator.adapters.ViewPageAdapter;
import com.mobileprograming.g4.calculator.fragments.AllExpressionsFragment;
import com.mobileprograming.g4.calculator.fragments.SavedExpressionsFragment;

import java.util.Objects;

public class HistoryActivity extends AppCompatActivity {

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
        pageAdapter.addFragment("All expressions", new AllExpressionsFragment());
        pageAdapter.addFragment("Saved expressions", new SavedExpressionsFragment());

        TabLayout tabLayout = findViewById(R.id.tablayout);
        ViewPager pgrMain = findViewById(R.id.pgrMain);

        pgrMain.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(pgrMain);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}