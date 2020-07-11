package com.harnet.guesscelebrity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.controller.CelebrityController;
import com.harnet.guesscelebrity.controller.SiteParserController;

public class MainActivity extends AppCompatActivity {
    private CelebrityController celebrityController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebrityController = CelebrityController.getInstance();
        System.out.println(celebrityController.getCelebrities().get(39).getName() + " : " + celebrityController.getCelebrities().get(39).getPhotoLink());
//        Log.i("Result:", "onCreate: " + celebrityController.getSiteParserController().getCelebrityNames());
//        Log.i("Result:", "onCreate: " + celebrityController.getSiteParserController().getCelebrityPhotos());
//        siteParserController.downloadContent();
//        siteParserController.parseContent();
    }
}