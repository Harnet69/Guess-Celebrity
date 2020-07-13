package com.harnet.guesscelebrity.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.harnet.guesscelebrity.model.Game;

public class GameController {
    @SuppressLint("StaticFieldLeak")
    private static GameController instance;
    private Game game = Game.getInstance();

    private int celebrityNum;

    private Context gameContext;
    private CelebrityController celebrityController;
    private ImageController imageController;
    private AnswersController answersController;
    private ScoreController scoreController;

    private ImageView celebrityImageView;
    private TextView celebrityNumTextView;
    private TextView wrongAnswersQttTextView;
    private LinearLayout answersBlockLinearLayout;
    private Button answer4Button;

    private GameController(Context gameContext, LinearLayout answersBlockLinearLayout, TextView celebrityNumTextView, TextView wrongAnswersQttTextView,
                           ImageView celebrityImageView, Button answer4Button) {
        this.gameContext = gameContext;
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

    public static GameController getInstance(Context gameContext, LinearLayout answersBlockLinearLayout, TextView celebrityNumTextView,
                                             TextView wrongAnswersQttTextView, ImageView celebrityImageView, Button answer4Button) {
        if(instance == null){
            instance = new GameController(gameContext, answersBlockLinearLayout, celebrityNumTextView, wrongAnswersQttTextView,
                                            celebrityImageView, answer4Button);
        }
        return instance;
    }

    public void newGame() {
        game.setGame(true);
        celebrityNum = 0;
        scoreController.resetScores();
        nextTurn();
        Toast.makeText(gameContext, "Good luck!!!", Toast.LENGTH_SHORT).show();
    }

    // main game loop
    @SuppressLint("SetTextI18n")
    public void nextTurn(){
        //TODO start new game if num of celebrity >100
        if(celebrityNum < celebrityController.getCelebrities().size()) {
            imageController = new ImageController();
            celebrityImageView.setImageBitmap(imageController.getImageByLink(celebrityController.getCelebrities().get(celebrityNum).getPhotoLink()));
            answersController.setRightAnswer(celebrityController.getCelebrities().get(celebrityNum).getName());
            answersController.populateAnswerBtns();// refresh answer btns
            celebrityNumTextView.setText(Integer.toString(celebrityNum+1));// refresh celebrity number TextView
            wrongAnswersQttTextView.setText(Integer.toString(scoreController.getWrongAnswersQtt()));// refresh wrong answers qtt TextView

            celebrityNum++; // increment celebrity num
        }else{
            //TODO here will be a new game incantation
            Toast.makeText(gameContext, "Game over", Toast.LENGTH_LONG).show();
            newGame();
            Game.getInstance().setGame(false);
//            celebrityNum = 0;
        }
    }
}