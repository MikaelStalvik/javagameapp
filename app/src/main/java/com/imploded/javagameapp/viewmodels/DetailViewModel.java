package com.imploded.javagameapp.viewmodels;

import com.imploded.javagameapp.models.Game;
import com.imploded.javagameapp.repository.DetailRepository;

import java.util.concurrent.ExecutionException;

/**
 * Created by l19548726 on 2017-12-06.
 */

public class DetailViewModel {

    private Game game;
    public Game getGame() {
        return game;
    }

    public void getGameFromServer(String id) throws ExecutionException, InterruptedException {
        game = new DetailRepository().getGameById(id);
    }

}
