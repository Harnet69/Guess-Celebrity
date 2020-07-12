package com.harnet.guesscelebrity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.controller.CelebrityController;
import com.harnet.guesscelebrity.controller.ImageController;
import com.harnet.guesscelebrity.model.Celebrity;

public class MainActivity extends AppCompatActivity {
    private CelebrityController celebrityController;
    private ImageController imageController;

    private ImageView celebrityImageView;
    private Button answer1Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebrityImageView = findViewById(R.id.celebrity_imageView);

        celebrityController = CelebrityController.getInstance();
        imageController = new ImageController();

        celebrityImageView.setImageBitmap(imageController.getImageByLink(celebrityController.getCelebrities().get(99).getPhotoLink()));


        for(Celebrity celebrity : celebrityController.getCelebrities()){
            System.out.println(celebrity.getName() + " : " +celebrity.getPhotoLink() );
        }
//        Log.i("Link:", "onCreate: " + celebrityController.getCelebrities().get(0).getPhotoLink());
    }
}