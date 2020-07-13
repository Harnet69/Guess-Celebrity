package com.harnet.guesscelebrity.controller;

public class ScoreController {
    private int rightAnswersQtt;
    private int wrongAnswersQtt;

    public int getRightAnswersQtt() {
        return rightAnswersQtt;
    }

    public int getWrongAnswersQtt() {
        return wrongAnswersQtt;
    }

    public void addRightAnswer(){
        rightAnswersQtt++;
    }

    public void addWrongAnswer(){
        wrongAnswersQtt++;
    }

    public void resetScores(){
        rightAnswersQtt = 0;
        wrongAnswersQtt = 0;
    }
}
