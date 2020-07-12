package com.harnet.guesscelebrity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.controller.CelebrityController;
import com.harnet.guesscelebrity.controller.ImageController;
import com.harnet.guesscelebrity.model.Celebrity;

public class MainActivity extends AppCompatActivity {
    private CelebrityController celebrityController;
    private ImageController imageController;

    private int celebrityNum;

    private ImageView celebrityImageView;
    private Button answer4Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebrityImageView = findViewById(R.id.celebrity_imageView);
        answer4Button = findViewById(R.id.answer4_button);

        celebrityController = CelebrityController.getInstance();
        imageController = new ImageController();

        celebrityImageView.setImageBitmap(imageController.getImageByLink(celebrityController.getCelebrities().get(celebrityNum).getPhotoLink()));
        answer4Button.setText(celebrityController.getCelebrities().get(celebrityNum).getName());

        onClickCreator();

        //TODO print all celebrities with photo links
//        for(Celebrity celebrity : celebrityController.getCelebrities()){
//            System.out.println(celebrity.getName() + " : " +celebrity.getPhotoLink() );
//        }
//        Log.i("Link:", "onCreate: " + celebrityController.getCelebrities().get(0).getPhotoLink());
    }

    public void onClickCreator(){
        answer4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                celebrityNum++;
                imageController = new ImageController();
                answer4Button.setText(celebrityController.getCelebrities().get(celebrityNum).getName());
                celebrityImageView.setImageBitmap(imageController.getImageByLink(celebrityController.getCelebrities().get(celebrityNum).getPhotoLink()));
            }
        });
    }
}