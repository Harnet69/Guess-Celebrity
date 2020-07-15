package com.harnet.guesscelebrity.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.model.Game;
import com.harnet.guesscelebrity.view.GameFragment;

public class GameController {
    private static final String TAG = "GameController";
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

    // TODO cn be a wrong import
    private GameFragment.OnMessageSendListener onMessageSendListener;

    private View view;

    private GameController(View view, GameFragment.OnMessageSendListener onMessageSendListener, Context gameContext) {
        this.onMessageSendListener = onMessageSendListener;
        this.gameContext = gameContext;
        this.view = view;

        celebrityImageView = view.findViewById(R.id.celebrity_imageView);
        celebrityNumTextView = view.findViewById(R.id.celebrity_num_textView);
        wrongAnswersQttTextView = view.findViewById(R.id.wrong_answers_textView);


        celebrityController = CelebrityController.getInstance();
        imageController = new ImageController();
        scoreController = new ScoreController();
        answersController = new AnswersController(onMessageSendListener, view.findViewById(R.id.answers_block_LinearLayout), wrongAnswersQttTextView, scoreController);
        newGame();
    }

    // used to get access to class methods
    public static GameController getInstance() {
        return instance;
    }

    public static GameController getInstance(View view, GameFragment.OnMessageSendListener onMessageSendListener, Context gameContext) {
        if(instance == null){
            instance = new GameController(view, onMessageSendListener, gameContext);
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
            Log.i("Non-GUEssed:", "nextTurn: " + CelebrityController.getInstance().getListOfCelebritiesByGuess(false));
        }else{
            //TODO here will be a new game incantation
            Toast.makeText(gameContext, "Game over", Toast.LENGTH_LONG).show();
            newGame();
            Game.getInstance().setGame(false);
//            celebrityNum = 0;
        }
    }
}