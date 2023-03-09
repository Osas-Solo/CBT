package com.ostech.cbt.model;

import java.util.ArrayList;

public class Answer {
    private int id;
    private Question question;
    private char correctOption;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public char getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(char correctOption) {
        this.correctOption = correctOption;
    }

    public boolean isCandidateRight() {
        return getCorrectOption() == getQuestion().getSelectedOption();
    }

    public static int numberOfCorrectAnswers(ArrayList<Answer> answers) {
        int correctAnswersCount = 0;

        for (Answer currentAnswer: answers) {
            if (currentAnswer.isCandidateRight()) {
                correctAnswersCount++;
            }
        }

        return correctAnswersCount;
    }
}