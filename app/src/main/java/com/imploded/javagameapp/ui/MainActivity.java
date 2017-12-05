package com.imploded.javagameapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.imploded.javagameapp.R;
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


        try {
            viewModel.getGames();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
