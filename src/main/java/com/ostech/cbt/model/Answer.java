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

    public boolean isCandidateRight(Question question) {
        return question.getId() == getQuestion().getId() && getCorrectOption() == question.getSelectedOption();
    }

    public static int numberOfCorrectAnswers(ArrayList<Question> questions, ArrayList<Answer> answers) {
        int correctAnswersCount = 0;

        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).isCandidateRight(questions.get(i))) {
                correctAnswersCount++;
            }
        }

        return correctAnswersCount;
    }

    public static ArrayList<Answer> reorderAnswersToMatchQuestions(ArrayList<Question> questions, ArrayList<Answer> answers) {
        ArrayList<Answer> reorderedAnswers = new ArrayList<>();

        for (Question currentQuestion : questions) {
            for (Answer currentAnswer: answers) {
                if (currentAnswer.getQuestion().getId() == currentQuestion.getId()) {
                    reorderedAnswers.add(currentAnswer);
                }
            }
        }

        return reorderedAnswers;
    }
}