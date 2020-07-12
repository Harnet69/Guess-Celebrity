package com.harnet.guesscelebrity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.controller.CelebrityController;
import com.harnet.guesscelebrity.model.Celebrity;

public class MainActivity extends AppCompatActivity {
    private CelebrityController celebrityController;

    private ImageView celebrityImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebrityController = CelebrityController.getInstance();

        celebrityImageView = findViewById(R.id.celebrity_imageView);

        for(Celebrity celebrity : celebrityController.getCelebrities()){
            System.out.println(celebrity.getName() + " : " +celebrity.getPhotoLink() );
        }
        Log.i("Link:", "onCreate: " + celebrityController.getCelebrities().get(0).getPhotoLink());
    }
}