package com.imploded.javagameapp.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imploded.javagameapp.models.Game;
import com.imploded.javagameapp.utils.HttpGetRequest;

import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

/**
 * Created by l19548726 on 2017-12-06.
 */

public class DetailRepository {

    private final String GET_GAME_BY_ID_URL = "http://kotlinserver.azurewebsites.net/%s";

    public Game getGameById(String id) throws ExecutionException, InterruptedException {
        String json = new HttpGetRequest().execute(String.format(GET_GAME_BY_ID_URL, id)).get();
        Gson gson = new Gson();
        Type gameType = new TypeToken<Game>(){}.getType();
        return gson.fromJson(json, gameType);
    }

}
