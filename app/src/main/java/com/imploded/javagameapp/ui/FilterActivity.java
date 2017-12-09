package com.imploded.javagameapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.imploded.javagameapp.R;
import com.imploded.javagameapp.adapters.FilterAdapter;
import com.imploded.javagameapp.utils.AppConstants;
import com.imploded.javagameapp.viewmodels.FilterViewModel;

public class FilterActivity extends AppCompatActivity {

    private FilterViewModel viewModel = new FilterViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        viewModel.restoreActiveFilters(getIntent().getStringExtra(AppConstants.FilterAllPlatformsId), getIntent().getStringExtra(AppConstants.ActiveFilterId));

        RecyclerView recyclerView = findViewById(R.id.filter_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerView.Adapter adapter = new FilterAdapter(getApplicationContext(), viewModel.filterItems);
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.filterButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeView();
            }
        });
    }

    private void closeView() {
        Intent result = new Intent(this, MainActivity.class);
        result.putExtra(AppConstants.ActiveFilterId, viewModel.getFilterItemsAsJson());
        setResult(RESULT_OK, result);
        finish();
    }
}
