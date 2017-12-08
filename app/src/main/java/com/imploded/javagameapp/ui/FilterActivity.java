package com.imploded.javagameapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imploded.javagameapp.R;
import com.imploded.javagameapp.adapters.FilterAdapter;
import com.imploded.javagameapp.adapters.GamesAdapter;
import com.imploded.javagameapp.models.FilterItem;
import com.imploded.javagameapp.models.Game;
import com.imploded.javagameapp.utils.AppConstants;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class FilterActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        String allPlatformsJson = getIntent().getStringExtra(AppConstants.FilterPlatformId);
        Gson gson = new Gson();
        Type platformType = new TypeToken<Map<String, Integer>>(){}.getType();
        Map<String, Integer> allPlatforms = gson.fromJson(allPlatformsJson, platformType);
        List<FilterItem> items = new ArrayList<FilterItem>();

        for(String key : allPlatforms.keySet()) {
            FilterItem item = new FilterItem();
            item.name = key;
            item.count = allPlatforms.get(key);
            items.add(item);
        }
        Collections.sort(items, new Comparator<FilterItem>() {
            @Override
            public int compare(FilterItem o1, FilterItem o2) {
                return o1.name.compareTo(o2.name);
            }
        });

        recyclerView = findViewById(R.id.filter_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new FilterAdapter(getApplicationContext(), items);
        recyclerView.setAdapter(adapter);

        Button button = findViewById(R.id.filterButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
