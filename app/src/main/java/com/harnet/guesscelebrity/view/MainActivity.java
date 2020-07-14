package com.harnet.guesscelebrity.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.controller.GameController;

public class MainActivity extends AppCompatActivity {
    private GameController gameController;

    private Fragment fragment;
    private Bundle exchangeBundle; // bundle to keep data for exchanging

    private ImageView celebrityImageView;
    private TextView celebrityNumTextView;
    private TextView wrongAnswersQttTextView;
    private LinearLayout answersBlockLinearLayout;
    private Button answer4Button;
    private FrameLayout gameContentFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        celebrityImageView = findViewById(R.id.celebrity_imageView);
        celebrityNumTextView = findViewById(R.id.celebrity_num_textView);
        answersBlockLinearLayout = findViewById(R.id.answers_block_LinearLayout);
        answer4Button = findViewById(R.id.answer4_button);
        wrongAnswersQttTextView = findViewById(R.id.wrong_answers_textView);
        gameContentFrameLayout = findViewById(R.id.game_content_FrameLayout);

        gameController = GameController.getInstance(this, answersBlockLinearLayout, celebrityNumTextView, wrongAnswersQttTextView,
                                                    celebrityImageView,answer4Button);
        fragment = new Fragment();
        // fragments migration
        if(savedInstanceState == null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.game_content_FrameLayout, new TrainingFragment())
                    .commit();
        }


        //TODO print all celebrities with photo links
//        for(Celebrity celebrity : celebrityController.getCelebrities()){
//            System.out.println(celebrity.getName() + " : " +celebrity.getPhotoLink() );
//        }
//        Log.i("Link:", "onCreate: " + celebrityController.getCelebrities().get(0).getPhotoLink());
    }
}