package com.harnet.guesscelebrity.controller;

import android.graphics.Bitmap;

import java.util.concurrent.ExecutionException;

public class ImageController {
    private ImageDownloader imageDownloader = new ImageDownloader();

    public Bitmap getImageByLink(String imageLink){
        try {
            Bitmap img = imageDownloader.execute(imageLink).get();
            imageDownloader.cancel(true); // close a thread
            return img;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
