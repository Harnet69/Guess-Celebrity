package com.harnet.guesscelebrity.controller;

import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadWebContentController extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        StringBuilder site = new StringBuilder();
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();

            while (data != -1) {
                char current = (char) data;
                site.append(current);
                data = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return site.toString();
    }
}