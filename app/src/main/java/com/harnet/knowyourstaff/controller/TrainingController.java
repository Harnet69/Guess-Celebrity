package com.harnet.knowyourstaff.controller;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.harnet.knowyourstaff.R;
import com.harnet.knowyourstaff.model.Person;
import com.harnet.knowyourstaff.view.TrainingFragment;
import java.util.List;

public class TrainingController {
    private ImageController imageController;
    private TrainingFragment.OnMessageSendListener onMessageSendListener;

    private View view;
    private ImageView celebrityImageView;
    private TextView celebrityNameTextView;
    private Button gotItBtn;

    private int numOfUnGuessed;

    private List<Person> unGuessedCelebrities;

    public TrainingController(View view, TrainingFragment.OnMessageSendListener onMessageSendListener) {
        unGuessedCelebrities = PersonController.getInstance().getStaffListByGuess(false);
        imageController = new ImageController();

        this.onMessageSendListener = onMessageSendListener;
        this.view = view;

        celebrityImageView = view.findViewById(R.id.celebrity_imageView);
        celebrityNameTextView = view.findViewById(R.id.celebrity_name_textView);
        gotItBtn = view.findViewById(R.id.got_it_btn);
    }

    public void trainingLoop() {
        celebrityImageView.setImageBitmap(imageController.getImageByLink(PersonController.getInstance().getPersonByName(unGuessedCelebrities.get(numOfUnGuessed).getName()).getPhotoLink()));
        celebrityNameTextView.setText(unGuessedCelebrities.get(numOfUnGuessed).getName());

        gotItBtn.setOnClickListener(v -> {
            if (numOfUnGuessed < unGuessedCelebrities.size() - 1) {
                numOfUnGuessed++;
                Person celebrity = PersonController.getInstance().getPersonByName(unGuessedCelebrities.get(numOfUnGuessed).getName());
                imageController = new ImageController();
                celebrityImageView.setImageBitmap(imageController.getImageByLink(celebrity.getPhotoLink()));
                celebrityNameTextView.setText(unGuessedCelebrities.get(numOfUnGuessed).getName());
                PersonController.getInstance().getPersonByName(celebrity.getName()).setGuessed(null); //reset celebrity guess status
            } else {
                //goto GameFragment
                onMessageSendListener.onMessageSend("Game");
            }
        });
    }
}
