package com.harnet.knowyourstaff.controller;

public class ScoreController {
    private int wrongAnswersQtt;

    public int getWrongAnswersQtt() {
        return wrongAnswersQtt;
    }

    public void addWrongAnswer(){
        wrongAnswersQtt++;
    }

    public void resetScores(){
        wrongAnswersQtt = 0;
    }
}
