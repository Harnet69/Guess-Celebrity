package com.harnet.guesscelebrity.controller;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.concurrent.ThreadLocalRandom;

public class AnswersController {
    private String rightAnswer;
    private String[] answers = new String[4];

    private LinearLayout answersBlockLinearLayout;

    public AnswersController(LinearLayout answersBlockLinearLayout) {
        this.answersBlockLinearLayout = answersBlockLinearLayout;
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
                // check if wrong answer not equal to right
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
                ((Button) subView).setText(String.valueOf(answers[i]));
                subView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isRightAnswer((String) ((Button) subView).getText())){
                            GameController.getInstance().nextTurn();
                        }
                    }
                });
            }
        }
    }

    private boolean isRightAnswer(String bntText){
        System.out.println(bntText.equals(rightAnswer));
        return bntText.equals(rightAnswer);
    }
}