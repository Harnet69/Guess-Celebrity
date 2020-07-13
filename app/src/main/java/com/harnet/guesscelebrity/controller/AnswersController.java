package com.harnet.guesscelebrity.controller;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.harnet.guesscelebrity.R;

import java.util.concurrent.ThreadLocalRandom;

public class AnswersController {
    private String rightAnswer;
    private String[] answers = new String[4];

    private LinearLayout answersBlockLinearLayout;
    private TextView wrongAnswersQttTextView;

    private ScoreController scoreController;

    public AnswersController(LinearLayout answersBlockLinearLayout, TextView wrongAnswersQttTextView, ScoreController scoreController) {
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
            final View subView = answersBlockLinearLayout.getChildAt(i); // reset btn color
            if (subView instanceof Button) {
                ((Button) subView).setBackgroundColor(0x00000000);
                ((Button) subView).setText(String.valueOf(answers[i]));
                subView.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View v) {
                        if(isRightAnswer((String) ((Button) subView).getText())){
                            GameController.getInstance().nextTurn();
                        }else {
                            ((Button) subView).setBackgroundColor(Color.parseColor("#bd332a"));
                            scoreController.addWrongAnswer();
                            wrongAnswersQttTextView.setText(Integer.toString(scoreController.getWrongAnswersQtt()));
                            subView.setOnClickListener(null);
                        }
                    }
                });
            }
        }
    }

    // check is answer right
    private boolean isRightAnswer(String bntText){
        System.out.println(bntText.equals(rightAnswer));
        return bntText.equals(rightAnswer);
    }
}