package com.harnet.guesscelebrity.controller;

import java.util.concurrent.ThreadLocalRandom;

public class AnswersController {
    private String rightAnswer;
    private String[] answers = new String[4];

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    private int setRandPosRightAnswer(){
        return ThreadLocalRandom.current().nextInt(0, 3 + 1);
    }

    public String[] generateAnswers(){
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
}