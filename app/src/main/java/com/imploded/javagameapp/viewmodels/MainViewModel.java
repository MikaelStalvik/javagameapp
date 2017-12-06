package com.imploded.javagameapp.viewmodels;

import com.imploded.javagameapp.models.Game;
import com.imploded.javagameapp.repository.MainRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by l19548726 on 2017-12-05.
 */

public class MainViewModel {

    private List<Game> games;
    public List<Game> getGames() {
        return games;
    }

    public Game getGame(int position) {
        return games.get(position);
    }

    public void getGamesFromServer() throws ExecutionException, InterruptedException {
        games = new MainRepository().getGames();
    }
}
