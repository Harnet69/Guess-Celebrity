package com.harnet.guesscelebrity.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.controller.GameController;

public class MainActivity extends AppCompatActivity {
    private GameController gameController;

    private ImageView celebrityImageView;
    private TextView celebrityNumTextView;
    private TextView wrongAnswersQttTextView;
    private LinearLayout answersBlockLinearLayout;
    private Button answer4Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebrityImageView = findViewById(R.id.celebrity_imageView);
        celebrityNumTextView = findViewById(R.id.celebrity_num_textView);
        answersBlockLinearLayout = findViewById(R.id.answers_block_LinearLayout);
        answer4Button = findViewById(R.id.answer4_button);
        wrongAnswersQttTextView = findViewById(R.id.wrong_answers_textView);

        gameController = GameController.getInstance(answersBlockLinearLayout, celebrityNumTextView, wrongAnswersQttTextView,
                                                    celebrityImageView,answer4Button);

        //TODO print all celebrities with photo links
//        for(Celebrity celebrity : celebrityController.getCelebrities()){
//            System.out.println(celebrity.getName() + " : " +celebrity.getPhotoLink() );
//        }
//        Log.i("Link:", "onCreate: " + celebrityController.getCelebrities().get(0).getPhotoLink());
    }
}