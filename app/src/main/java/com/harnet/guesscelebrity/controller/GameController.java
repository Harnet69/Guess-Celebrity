package com.harnet.guesscelebrity.controller;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.harnet.guesscelebrity.model.Game;

import java.util.Arrays;

public class GameController {
    private Game game = Game.getInstance();

    private int celebrityNum;

    private CelebrityController celebrityController;
    private ImageController imageController;
    private AnswersController answersController;

    private ImageView celebrityImageView;
    private LinearLayout answersBlockLinearLayout;
    private Button answer4Button;

    public GameController(LinearLayout answersBlockLinearLayout, ImageView celebrityImageView, Button answer4Button) {
        this.answersBlockLinearLayout = answersBlockLinearLayout;
        this.celebrityImageView = celebrityImageView;
        this.answer4Button = answer4Button;

        celebrityController = CelebrityController.getInstance();
        imageController = new ImageController();
        answersController = new AnswersController(answersBlockLinearLayout);

        newGame();
    }


    public void newGame() {
        game.setGame(true);
        nextTurn();
        onClickCreator();
    }

    public void onClickCreator() {
        answer4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextTurn();
            }
        });
    }

    // shows photo, generates answers
    public void nextTurn(){
        imageController = new ImageController();
        answer4Button.setText(celebrityController.getCelebrities().get(celebrityNum).getName());
        celebrityImageView.setImageBitmap(imageController.getImageByLink(celebrityController.getCelebrities().get(celebrityNum).getPhotoLink()));
        answersController.setRightAnswer(celebrityController.getCelebrities().get(celebrityNum).getName());
        celebrityNum++;
        answersController.populateAnswerBtns();
    }
}