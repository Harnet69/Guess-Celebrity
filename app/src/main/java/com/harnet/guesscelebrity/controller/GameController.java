package com.harnet.guesscelebrity.controller;

import android.annotation.SuppressLint;
import android.util.Log;
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
    private ImageController imageController;
    private AnswersController answersController;
    private ScoreController scoreController;

    private ImageView celebrityImageView;
    private TextView celebrityNumTextView;
    private TextView wrongAnswersQttTextView;
    private LinearLayout answersBlockLinearLayout;
    private Button answer4Button;

    private GameController(LinearLayout answersBlockLinearLayout, TextView celebrityNumTextView, TextView wrongAnswersQttTextView,
                           ImageView celebrityImageView, Button answer4Button) {
        this.answersBlockLinearLayout = answersBlockLinearLayout;
        this.celebrityNumTextView = celebrityNumTextView;
        this.wrongAnswersQttTextView = wrongAnswersQttTextView;
        this.celebrityImageView = celebrityImageView;
        this.answer4Button = answer4Button;

        celebrityController = CelebrityController.getInstance();
        imageController = new ImageController();
        scoreController = new ScoreController();
        answersController = new AnswersController(answersBlockLinearLayout, wrongAnswersQttTextView, scoreController);
        newGame();
    }

    // used to get access to class methods
    public static GameController getInstance() {
        return instance;
    }

    public static GameController getInstance(LinearLayout answersBlockLinearLayout, TextView celebrityNumTextView,
                                             TextView wrongAnswersQttTextView, ImageView celebrityImageView, Button answer4Button) {
        if(instance == null){
            instance = new GameController(answersBlockLinearLayout, celebrityNumTextView, wrongAnswersQttTextView,
                                            celebrityImageView, answer4Button);
        }
        return instance;
    }

    public void newGame() {
        game.setGame(true);
        celebrityNum = 0;
        scoreController.resetScores();
        nextTurn();
    }

    // shows photo, generates answers
    @SuppressLint("SetTextI18n")
    public void nextTurn(){
        if(celebrityNum < celebrityController.getCelebrities().size()) {
            imageController = new ImageController();
//            answer4Button.setText(celebrityController.getCelebrities().get(celebrityNum).getName());
            celebrityImageView.setImageBitmap(imageController.getImageByLink(celebrityController.getCelebrities().get(celebrityNum).getPhotoLink()));
            answersController.setRightAnswer(celebrityController.getCelebrities().get(celebrityNum).getName());
            answersController.populateAnswerBtns();
            celebrityNumTextView.setText(Integer.toString(celebrityNum+1));
            wrongAnswersQttTextView.setText(Integer.toString(scoreController.getWrongAnswersQtt()));

            celebrityNum++;
        }else{
            //TODO here will be a new game incantation
            newGame();
            Game.getInstance().setGame(false);
            celebrityNum = 0;
        }
    }
}