package com.harnet.guesscelebrity.controller;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.model.Celebrity;
import com.harnet.guesscelebrity.view.TrainingFragment;

import java.util.ArrayList;
import java.util.List;

public class TrainingController {
    private ImageController imageController;
    private TrainingFragment.OnMessageSendListener onMessageSendListener;

    private View view;
    private ImageView celebrityImageView;
    private TextView celebrityNameTextView;
    private Button gotItBtn;

    private int numOfUnGuessed;

    private List<Celebrity> unGuessedCelebrities;

    public TrainingController(View view, TrainingFragment.OnMessageSendListener onMessageSendListener) {
        unGuessedCelebrities = CelebrityController.getInstance().getListOfCelebritiesByGuess(false);
        imageController = new ImageController();

        this.onMessageSendListener = onMessageSendListener;
        this.view = view;

        celebrityImageView = view.findViewById(R.id.celebrity_imageView);
        celebrityNameTextView = view.findViewById(R.id.celebrity_name_textView);
        gotItBtn = view.findViewById(R.id.got_it_btn);
    }

    public void trainingLoop() {
        celebrityImageView.setImageBitmap(imageController.getImageByLink(CelebrityController.getInstance().getCelebrityByName(unGuessedCelebrities.get(numOfUnGuessed).getName()).getPhotoLink()));
        celebrityNameTextView.setText(unGuessedCelebrities.get(numOfUnGuessed).getName());

        gotItBtn.setOnClickListener(v -> {
            if (numOfUnGuessed < unGuessedCelebrities.size() - 1) {
                numOfUnGuessed++;
                Celebrity celebrity = CelebrityController.getInstance().getCelebrityByName(unGuessedCelebrities.get(numOfUnGuessed).getName());
                imageController = new ImageController();
                celebrityImageView.setImageBitmap(imageController.getImageByLink(celebrity.getPhotoLink()));
                celebrityNameTextView.setText(unGuessedCelebrities.get(numOfUnGuessed).getName());
                CelebrityController.getInstance().getCelebrityByName(celebrity.getName()).setGuessed(null); //reset celebrity guess status
            } else {
                //goto GameFragment
                onMessageSendListener.onMessageSend("Game");
            }
        });
    }
}
