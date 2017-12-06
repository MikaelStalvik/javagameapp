package com.imploded.javagameapp.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.imploded.javagameapp.R;
import com.imploded.javagameapp.adapters.GamesAdapter;
import com.imploded.javagameapp.models.Game;
import com.imploded.javagameapp.viewmodels.MainViewModel;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel = new MainViewModel();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.game_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        try {
            viewModel.getGamesFromServer();
            adapter = new GamesAdapter(getApplicationContext(), viewModel.getGames());
            ((GamesAdapter)adapter).setOnItemClickListener(new GamesAdapter.ClickListener() {
                @Override
                public void onItemClick(int position, View v) {
                    Game game = viewModel.getGame(position);
                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("id", game.getId());
                    startActivity(intent);
                }
            });

            recyclerView.setAdapter(adapter);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
