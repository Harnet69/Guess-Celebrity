package com.harnet.knowyourstaff.controller;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.harnet.knowyourstaff.R;
import com.harnet.knowyourstaff.view.GameFragment;

public class GameController {
    private int personNum;

    private Context gameContext;
    private PersonController personController;
    private ImageController imageController;
    private AnswersController answersController;
    private ScoreController scoreController;

    private ImageView personImageView;
    private TextView personNumTextView;
    private TextView wrongAnswersQttTextView;

    private GameFragment.OnMessageSendListener onMessageSendListener;

    private View view;

    public GameController(View view, GameFragment.OnMessageSendListener onMessageSendListener, Context gameContext) {
        this.onMessageSendListener = onMessageSendListener;
        this.gameContext = gameContext;
        this.view = view;

        personImageView = view.findViewById(R.id.person_imageView);
        personNumTextView = view.findViewById(R.id.person_num_textView);
        wrongAnswersQttTextView = view.findViewById(R.id.wrong_answers_textView);

        personController = PersonController.getInstance(); // parse a web page
        imageController = new ImageController();
        scoreController = new ScoreController();
        answersController = new AnswersController(this, onMessageSendListener, view.findViewById(R.id.answers_block_LinearLayout), wrongAnswersQttTextView, scoreController);
        newGame();
    }

    public void newGame() {
        personNum = 0;
        scoreController.resetScores();
        nextTurn();
        Toast.makeText(gameContext, "Good luck!!!", Toast.LENGTH_SHORT).show();
    }

    // main game loop
    @SuppressLint("SetTextI18n")
    public void nextTurn(){
        //TODO start new game if num of person > staff size
        if(personNum < personController.getStaff().size()) {
            imageController = new ImageController();
            personImageView.setImageBitmap(imageController.getImageByLink(personController.getStaff().get(personNum).getPhotoLink()));
            answersController.setRightAnswer(personController.getStaff().get(personNum).getName());
            answersController.populateAnswerBtns();// refresh answer btns
            personNumTextView.setText(Integer.toString(personNum +1));// refresh person number TextView
            wrongAnswersQttTextView.setText(Integer.toString(scoreController.getWrongAnswersQtt()));// refresh wrong answers qtt TextView

            personNum++; // increment person num
//            Log.i("Not guessed:", "nextTurn: " + PersonController.getInstance`().getStaffListByGuess(false));
        }else{
            Toast.makeText(gameContext, "Game over", Toast.LENGTH_LONG).show();
            newGame();
        }
    }
}