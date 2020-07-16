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
    private ImageView personImageView;
    private TextView personNameTextView;
    private Button gotItBtn;

    private int numOfUnGuessed;

    private List<Person> notGuessedStaff;

    public TrainingController(View view, TrainingFragment.OnMessageSendListener onMessageSendListener) {
        notGuessedStaff = PersonController.getInstance().getStaffListByGuess(false);
        imageController = new ImageController();

        this.onMessageSendListener = onMessageSendListener;
        this.view = view;

        personImageView = view.findViewById(R.id.person_imageView);
        personNameTextView = view.findViewById(R.id.person_name_textView);
        gotItBtn = view.findViewById(R.id.got_it_btn);
    }

    public void trainingLoop() {
        personImageView.setImageBitmap(imageController.getImageByLink(PersonController.getInstance().getPersonByName(notGuessedStaff.get(numOfUnGuessed).getName()).getPhotoLink()));
        personNameTextView.setText(notGuessedStaff.get(numOfUnGuessed).getName());

        gotItBtn.setOnClickListener(v -> {
            if (numOfUnGuessed < notGuessedStaff.size() - 1) {
                numOfUnGuessed++;
                Person person = PersonController.getInstance().getPersonByName(notGuessedStaff.get(numOfUnGuessed).getName());
                imageController = new ImageController();
                personImageView.setImageBitmap(imageController.getImageByLink(person.getPhotoLink()));
                personNameTextView.setText(notGuessedStaff.get(numOfUnGuessed).getName());
                PersonController.getInstance().getPersonByName(person.getName()).setGuessed(null); //reset person guess status
            } else {
                //goto GameFragment
                onMessageSendListener.onMessageSend("Game");
            }
        });
    }
}
