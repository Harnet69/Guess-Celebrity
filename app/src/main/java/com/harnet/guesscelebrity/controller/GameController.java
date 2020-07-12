package com.harnet.guesscelebrity.controller;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harnet.guesscelebrity.model.Game;

public class GameController {
    @SuppressLint("StaticFieldLeak")
    private static GameController instance;
    private Game game = Game.getInstance();

    private int celebrityNum;

    private CelebrityController celebrityController;
    private TextView celebrityNumTextView;
    private ImageController imageController;
    private AnswersController answersController;

    private ImageView celebrityImageView;
    private LinearLayout answersBlockLinearLayout;
    private Button answer4Button;

    private GameController(LinearLayout answersBlockLinearLayout, TextView celebrityNumTextView, ImageView celebrityImageView, Button answer4Button) {
        this.answersBlockLinearLayout = answersBlockLinearLayout;
        this.celebrityNumTextView = celebrityNumTextView;
        this.celebrityImageView = celebrityImageView;
        this.answer4Button = answer4Button;

        celebrityController = CelebrityController.getInstance();
        imageController = new ImageController();
        answersController = new AnswersController(answersBlockLinearLayout);

        newGame();
    }

    // used to get access to class methods
    public static GameController getInstance() {
        return instance;
    }

    public static GameController getInstance(LinearLayout answersBlockLinearLayout, TextView celebrityNumTextView, ImageView celebrityImageView, Button answer4Button) {
        if(instance == null){
            instance = new GameController(answersBlockLinearLayout, celebrityNumTextView, celebrityImageView, answer4Button);
        }
        return instance;
    }

    public void newGame() {
        game.setGame(true);
        celebrityNum = 0;
        nextTurn();
    }

    // shows photo, generates answers
    @SuppressLint("SetTextI18n")
    public void nextTurn(){
        if(celebrityNum < celebrityController.getCelebrities().size()) {
            imageController = new ImageController();
            answer4Button.setText(celebrityController.getCelebrities().get(celebrityNum).getName());
            celebrityImageView.setImageBitmap(imageController.getImageByLink(celebrityController.getCelebrities().get(celebrityNum).getPhotoLink()));
            answersController.setRightAnswer(celebrityController.getCelebrities().get(celebrityNum).getName());
            answersController.populateAnswerBtns();
            celebrityNumTextView.setText(Integer.toString(celebrityNum+1));
            celebrityNum++;
        }else{
            //TODO here will be a new game incantation
            newGame();
            Game.getInstance().setGame(false);
            celebrityNum = 0;
        }
    }
}