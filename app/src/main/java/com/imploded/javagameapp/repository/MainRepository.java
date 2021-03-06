package com.imploded.javagameapp.repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imploded.javagameapp.models.Game;
import com.imploded.javagameapp.utils.HttpGetRequest;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainRepository {

    private final String GET_GAMES_URL = "http://kotlinserver.azurewebsites.net/games";

    public List<Game> getGames() throws ExecutionException, InterruptedException {
        String json = new HttpGetRequest().execute(GET_GAMES_URL).get();
        Gson gson = new Gson();
        Type listType = new TypeToken<List<Game>>(){}.getType();
        return gson.fromJson(json, listType);
    }

}
