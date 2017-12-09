package com.imploded.javagameapp.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.imploded.javagameapp.R;
import com.imploded.javagameapp.models.Game;
import com.imploded.javagameapp.viewmodels.DetailViewModel;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

public class DetailActivity extends AppCompatActivity {

    private DetailViewModel viewModel = new DetailViewModel();
    private TextView titleTextView;
    private ImageView imageView;
    private TextView publisherTextView;
    private TextView releaseyearTextView;
    private TextView platformTextView;
    private TextView descriptionTextView;

    private void updateUi() {
        Game game = viewModel.getGame();
        setTitle(game.getName());
        titleTextView.setText(game.getName());
        publisherTextView.setText(String.format("%s: %s", getString(R.string.publisher), game.getPublisher()));
        releaseyearTextView.setText(String.format("%s: %s", getString(R.string.releaseYear), game.getReleaseYear()));
        platformTextView.setText(String.format("%s: %s", getString(R.string.platforms), android.text.TextUtils.join(",", game.getPlatforms())));
        descriptionTextView.setText(game.getDescription());
        Picasso.with(DetailActivity.this).load(game.getPicture()).into(imageView);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleTextView = findViewById(R.id.textViewDetailTitle);
        imageView = findViewById(R.id.imageViewLarge);
        publisherTextView = findViewById(R.id.textViewPublisher);
        releaseyearTextView = findViewById(R.id.textViewReleaseYear);
        platformTextView = findViewById(R.id.textViewPlatform);
        descriptionTextView = findViewById(R.id.textViewDescription);

        String id = getIntent().getStringExtra("id");
        try {
            viewModel.getGameFromServer(id);
            updateUi();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
