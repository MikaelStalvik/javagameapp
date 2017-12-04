package com.imploded.javagameapp.repository;

import android.os.AsyncTask;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.gson.Gson;
import com.imploded.javagameapp.models.LoginRequest;

import java.nio.charset.Charset;

/**
 * Created by Mikael on 2017-12-04.
 */

public class LoginRepository {

    private static final String LOGIN_URL = "http://kotlinserver.azurewebsites.net/login";

    public void login(String username, String password) {
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        String body = new Gson().toJson(request);
        new LoginTask().execute(LOGIN_URL, body);
    }

    private class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            String body = strings[1];

            Request req = Fuel.post(url).body(body, Charset.forName("UTF-8"));
            req.header()
            Fuel.post(url).body(body, Charset.forName("UTF-8")).responseString(new Handler<String>() {
                @Override
                public void success(Request request, Response response, String s) {
                    int k = 123;
                }

                @Override
                public void failure(Request request, Response response, FuelError fuelError) {
                    int j = 2;
                }
            });


            return null;
        }
    }
}
