package com.harnet.guesscelebrity.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
    private Bitmap myBitmap;

    // the method return an image asynchronicity
    @Override
    protected Bitmap doInBackground(String... urls) {
        myBitmap = manageImageInputStream(urls);
        return myBitmap;
    }

    // connect to URL
    private HttpURLConnection createConnectionToURL(String[] urls){
        URL url;
        HttpURLConnection connection;

        try{
            url = new URL(urls[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            return connection;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // handle an Input Stream
    private Bitmap manageImageInputStream(String[] urls){
        HttpURLConnection connection = createConnectionToURL(urls);
        try{
            assert connection != null;
            connection.connect();

            InputStream in = connection.getInputStream();
            myBitmap = BitmapFactory.decodeStream(in);

            return myBitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
