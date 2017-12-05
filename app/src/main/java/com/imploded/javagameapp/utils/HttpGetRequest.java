package com.imploded.javagameapp.utils;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by l19548726 on 2017-12-05.
 */

public class HttpGetRequest extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String stringUrl = strings[0];
        String result = null;
        String inputLine;

        try {
            URL url = new URL(stringUrl);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.connect();

            InputStreamReader streamReader = new InputStreamReader(con.getInputStream());
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder builder = new StringBuilder();
            while((inputLine = reader.readLine()) != null) {
                builder.append(inputLine);
            }
            reader.close();
            streamReader.close();
            result = builder.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
