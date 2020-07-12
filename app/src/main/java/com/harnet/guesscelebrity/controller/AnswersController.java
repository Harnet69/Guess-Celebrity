package com.harnet.guesscelebrity.controller;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

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

    private String[] generateAnswers(){
        int rightAnswerPos = setRandPosRightAnswer();
        for(int i = 0; i < answers.length; i++){
            if(i == rightAnswerPos){
                answers[i] = rightAnswer;
            }else{
                String wrongAnswer = CelebrityController.getInstance().getCelebrities().get(ThreadLocalRandom.current().nextInt(0, 100)).getName();
                // check if wrong answer not equal right
                while(wrongAnswer.equals(rightAnswer)){
                    wrongAnswer = CelebrityController.getInstance().getCelebrities().get(ThreadLocalRandom.current().nextInt(0, 100)).getName();
                }
                answers[i] = wrongAnswer;
            }
        }
        return answers;
    }

    public void populateAnswerBtns(){
        generateAnswers();
        int answersBtnsQtt = answersBlockLinearLayout.getChildCount();

        for (int i = 0; i < answersBtnsQtt; i++) {
            View subView = answersBlockLinearLayout.getChildAt(i);
            if (subView instanceof Button) {
                ((Button) subView).setText(String.valueOf(answers[i]));
            }
        }
    }

    // add texts and tags to buttons with numbers from answers array
//    public void generateAnswers(int rightResult) {
//        int[] answers = new int[answerGridLayout.getChildCount()];
//        // TODO check the quantity of childs
//        answers = fillAnswersArray(answers, rightResult);
//        for (int i = 0; i < answerGridLayout.getChildCount(); i++) {
//            View subView = answerGridLayout.getChildAt(i);
//            if (subView instanceof TextView) {
//                ((TextView) subView).setText(String.valueOf(answers[i]));
//                subView.setTag(String.valueOf(answers[i]));
//                subView.setSoundEffectsEnabled(false);
//            }
//        }
//    }
}