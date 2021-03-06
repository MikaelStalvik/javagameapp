package com.imploded.javagameapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.imploded.javagameapp.R;
import com.imploded.javagameapp.adapters.GamesAdapter;
import com.imploded.javagameapp.models.Game;
import com.imploded.javagameapp.utils.AppConstants;
import com.imploded.javagameapp.viewmodels.MainViewModel;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel = new MainViewModel();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.game_list);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        try {
            viewModel.getGamesFromServer();
            updateView(viewModel.activeSorting);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_filter:
                showFilter();
                return true;
            case R.id.action_sorting:
                showSorting();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                switch (requestCode) {
                    case AppConstants.RequestSortCode:
                        updateView(data.getStringExtra(AppConstants.SortingId));
                        break;
                    case AppConstants.RequestFilterCode:
                        updateViewWithFilterData(data.getStringExtra(AppConstants.ActiveFilterId));
                        break;
                }
            }
        }
    }

    private void updateViewWithFilterData(String newFilter) {
        viewModel.activeFilter = newFilter;
        updateView(viewModel.activeSorting);
    }

    private void updateView(String sorting) {
        viewModel.activeSorting = sorting;
        RecyclerView.Adapter adapter = new GamesAdapter(getApplicationContext(), viewModel.getGamesForView());
        ((GamesAdapter) adapter).setOnItemClickListener(new GamesAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Game game = viewModel.getGame(position);
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra("id", game.getId());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    private void showFilter() {
        Gson gson = new Gson();
        String json = gson.toJson(viewModel.getAllPlatforms());

        Intent intent = new Intent(this, FilterActivity.class);
        intent.putExtra(AppConstants.ActiveFilterId, viewModel.activeFilter);
        intent.putExtra(AppConstants.FilterAllPlatformsId, json);
        startActivityForResult(intent, AppConstants.RequestFilterCode);
    }

    private void showSorting() {
        Gson gson = new Gson();
        String json = gson.toJson(viewModel.getAllPlatforms());

        Intent intent = new Intent(this, SortingActivity.class);
        intent.putExtra(AppConstants.FilterAllPlatformsId, json);
        intent.putExtra(AppConstants.SortingId, viewModel.activeSorting);
        startActivityForResult(intent, AppConstants.RequestSortCode);
    }
}
