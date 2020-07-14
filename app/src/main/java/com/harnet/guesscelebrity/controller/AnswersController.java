package com.harnet.guesscelebrity.controller;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.harnet.guesscelebrity.R;
import com.harnet.guesscelebrity.view.GameFragment;

import java.util.concurrent.ThreadLocalRandom;

public class AnswersController {
    private String rightAnswer;
    private String[] answers = new String[4];

    private LinearLayout answersBlockLinearLayout;
    private TextView wrongAnswersQttTextView;

    private ScoreController scoreController;

    private GameFragment.OnMessageSendListener onMessageSendListener;

    public AnswersController(GameFragment.OnMessageSendListener onMessageSendListener, LinearLayout answersBlockLinearLayout, TextView wrongAnswersQttTextView, ScoreController scoreController) {
        this.onMessageSendListener = onMessageSendListener;
        this.answersBlockLinearLayout = answersBlockLinearLayout;
        this.wrongAnswersQttTextView = wrongAnswersQttTextView;
        this.scoreController = scoreController;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    // generate random position of right question
    // TODO make it universal. As an argument pass max value
    private int setRandPosRightAnswer(){
        return ThreadLocalRandom.current().nextInt(0, 3 + 1);
    }

    // make a answers list with a right one
    private void generateAnswers(){
        int rightAnswerPos = setRandPosRightAnswer();

        for(int i = 0; i < answers.length; i++){
            if(i == rightAnswerPos){
                answers[i] = rightAnswer;
            }else{
                String wrongAnswer = CelebrityController.getInstance().getCelebrities().get(ThreadLocalRandom.current().nextInt(0, 100)).getName();
                // check if wrong answer not equal to right answer
                while(wrongAnswer.equals(rightAnswer)){
                    wrongAnswer = CelebrityController.getInstance().getCelebrities().get(ThreadLocalRandom.current().nextInt(0, 100)).getName();
                }
                answers[i] = wrongAnswer;
            }
        }
    }

    // set answers buttons text
    public void populateAnswerBtns(){
        generateAnswers();
        int answersBtnsQtt = answersBlockLinearLayout.getChildCount();

        for (int i = 0; i < answersBtnsQtt; i++) {
            final View subView = answersBlockLinearLayout.getChildAt(i);
            if (subView instanceof Button) {
                ((Button) subView).setBackgroundColor(0x00000000);// reset btn color
                ((Button) subView).setText(String.valueOf(answers[i]));

                subView.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        if(isRightAnswer((String) ((Button) subView).getText())){
                            handleRightAnswer(subView);
                        }else {
                            handleWrongAnswer(subView);
                        }
                    }
                });
            }
        }
    }

    private void handleRightAnswer(View subView){
        ((Button) subView).setBackgroundColor(Color.parseColor("#27b029"));
        clearAllListeners(subView); // block multiple pushes bag
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 1s = 1000ms
                GameController.getInstance().nextTurn();
            }
        }, 1000);
    }

    @SuppressLint("SetTextI18n")
    private void handleWrongAnswer(View subView){
        ((Button) subView).setBackgroundColor(Color.parseColor("#bd332a"));
        scoreController.addWrongAnswer();
        wrongAnswersQttTextView.setText(Integer.toString(scoreController.getWrongAnswersQtt()));
        subView.setOnClickListener(null);
        if(scoreController.getWrongAnswersQtt() > 5){
            // TODO go to a remembering game!!!
            Log.i("WRONg:", "handleWrongAnswer: " + "go to a training game");
            onMessageSendListener.onMessageSend("GoToTraining!");
        }
    }

    private void clearAllListeners(View subView){
        int answersBtnsQtt = answersBlockLinearLayout.getChildCount();

        for (int i = 0; i < answersBtnsQtt; i++) {
            subView = answersBlockLinearLayout.getChildAt(i); // reset btn color
            if (subView instanceof Button) {
                subView.setOnClickListener(null);
            }
        }
    }

    // check is answer right
    private boolean isRightAnswer(String bntText){
        return bntText.equals(rightAnswer);
    }
}