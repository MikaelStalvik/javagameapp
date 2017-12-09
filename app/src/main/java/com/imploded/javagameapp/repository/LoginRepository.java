package com.imploded.javagameapp.repository;

import android.os.AsyncTask;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.gson.Gson;
import com.imploded.javagameapp.interfaces.OnLoginCallback;
import com.imploded.javagameapp.models.LoginRequest;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class LoginRepository {

    private static final String LOGIN_URL = "http://kotlinserver.azurewebsites.net/login";

    public void login(String username, String password, final OnLoginCallback loginCallback) {
        LoginRequest request = new LoginRequest();
        request.setUsername(username);
        request.setPassword(password);
        String body = new Gson().toJson(request);
        new LoginTask(new loginTaskCompletedListener() {
            @Override
            public void onLoginTaskCompleted(Boolean result) {
                loginCallback.OnLogin(result);
            }
        }).execute(LOGIN_URL, body);
    }

    private class LoginTask extends AsyncTask<String, Void, Boolean> {

        private boolean isValid;
        private loginTaskCompletedListener listener;

        public LoginTask(loginTaskCompletedListener listener) {
            this.listener = listener;
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            String url = strings[0];
            String body = strings[1];

            isValid = false;
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            Fuel.post(url).body(body, Charset.forName("UTF-8")).header(headers).responseString(new Handler<String>() {
                @Override
                public void success(Request request, Response response, String s) {
                    isValid = response.getHttpStatusCode() == 200;
                    listener.onLoginTaskCompleted(isValid);
                }

                @Override
                public void failure(Request request, Response response, FuelError fuelError) {
                }
            });

            return true;
        }

    }

    private interface loginTaskCompletedListener {
        void onLoginTaskCompleted(Boolean result);
    }
}



