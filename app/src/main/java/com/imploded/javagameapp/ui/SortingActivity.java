package com.imploded.javagameapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.imploded.javagameapp.R;
import com.imploded.javagameapp.utils.AppConstants;

public class SortingActivity extends AppCompatActivity {

    private RadioButton sortingNameRadioButton;
    private RadioButton sortingPublisherRadioButton;
    private RadioButton sortingReleaseYearRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting);

        sortingNameRadioButton = findViewById(R.id.sortingNameRadioButton);
        sortingPublisherRadioButton = findViewById(R.id.sortingPublisherRadioButton);
        sortingReleaseYearRadioButton = findViewById(R.id.sortingReleaseYearRadioButton);

        String activeSorting = getIntent().getStringExtra(AppConstants.SortingId);
        switch(activeSorting) {
            case AppConstants.SortingNameId:
                sortingNameRadioButton.setChecked(true);
                break;
            case AppConstants.SortingPublisherId:
                sortingPublisherRadioButton.setChecked(true);
                break;
            case AppConstants.SortingReleaseYearId:
                sortingReleaseYearRadioButton.setChecked(true);
                break;
        }

        Button applySortingButton = findViewById(R.id.applySortingButton);
        applySortingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeView();
            }
        });
    }

    private void closeView() {
        Intent result = new Intent(this, MainActivity.class);
        if (sortingNameRadioButton.isChecked()) {
            result.putExtra(AppConstants.SortingId, AppConstants.SortingNameId);
        }
        else if (sortingPublisherRadioButton.isChecked()) {
            result.putExtra(AppConstants.SortingId, AppConstants.SortingPublisherId);
        }
        else if (sortingReleaseYearRadioButton.isChecked()) {
            result.putExtra(AppConstants.SortingId, AppConstants.SortingReleaseYearId);
        }
        setResult(RESULT_OK, result);
        finish();
    }
}
