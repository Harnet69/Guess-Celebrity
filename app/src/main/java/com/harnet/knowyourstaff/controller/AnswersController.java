package com.harnet.knowyourstaff.controller;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.harnet.knowyourstaff.view.GameFragment;
import java.util.concurrent.ThreadLocalRandom;

public class AnswersController {
    private String rightAnswer;
    private String[] answers = new String[4];
    private boolean notGuessed = false; // for marking celebrity

    private LinearLayout answersBlockLinearLayout;
    private TextView wrongAnswersQttTextView;

    private GameController gameController;
    private ScoreController scoreController;

    private GameFragment.OnMessageSendListener onMessageSendListener;

    public AnswersController(GameController gameController, GameFragment.OnMessageSendListener onMessageSendListener, LinearLayout answersBlockLinearLayout,
                             TextView wrongAnswersQttTextView, ScoreController scoreController) {
        this.onMessageSendListener = onMessageSendListener;
        this.answersBlockLinearLayout = answersBlockLinearLayout;
        this.wrongAnswersQttTextView = wrongAnswersQttTextView;
        this.scoreController = scoreController;
        this.gameController = gameController;
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
                String wrongAnswer = PersonController.getInstance().getStaff().get(ThreadLocalRandom.current().nextInt(0, 100)).getName();
                // check if wrong answer not equal to right answer
                while(wrongAnswer.equals(rightAnswer)){
                    wrongAnswer = PersonController.getInstance().getStaff().get(ThreadLocalRandom.current().nextInt(0, 100)).getName();
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

                subView.setOnClickListener(v -> {
                    if(isRightAnswer((String) ((Button) subView).getText())){
                        handleRightAnswer(subView);
                    }else {
                        handleWrongAnswer(subView);
                    }
                });
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private void handleRightAnswer(View subView){
        ((Button) subView).setBackgroundColor(Color.parseColor("#27b029"));
        if(!notGuessed){
            PersonController.getInstance().getPersonByName((String) ((Button) subView).getText()).setGuessed(true); // mark person as guessed
            notGuessed = false;
        }else if(notGuessed){
            PersonController.getInstance().getPersonByName((String) ((Button) subView).getText()).setGuessed(false); // mark person as not guessed
            scoreController.addWrongAnswer();
            wrongAnswersQttTextView.setText(Integer.toString(scoreController.getWrongAnswersQtt()));
            notGuessed = false;
        }
        if(scoreController.getWrongAnswersQtt() >= 5){
            // go to a training mode
            onMessageSendListener.onMessageSend("Training");
        }
        clearAllListeners(subView); // block multiple pushes bag
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // switch to another person after 1s = 1000ms
                gameController.nextTurn();
            }
        }, 1000);
    }

    @SuppressLint("SetTextI18n")
    private void handleWrongAnswer(View subView){
        ((Button) subView).setBackgroundColor(Color.parseColor("#bd332a"));
        notGuessed = true;
        subView.setOnClickListener(null);
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