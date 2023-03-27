package com.ostech.cbt.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

public class Question {
    private int id;
    private int subjectID;
    private String question;
    private LinkedHashMap<Character, String> options;
    private char selectedOption;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(int subjectID) {
        this.subjectID = subjectID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LinkedHashMap<Character, String> getOptions() {
        return options;
    }

    public void setOptions(LinkedHashMap<Character, String> options) {
        this.options = options;
    }

    public char getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(char selectedOption) {
        this.selectedOption = selectedOption;
    }

    public void shuffleOptions() {
        ArrayList<Character> optionList = new ArrayList<>(getOptions().keySet());

        Collections.shuffle(optionList);
        LinkedHashMap<Character, String> rearrangedOptions = new LinkedHashMap<>();

        for (Character currentOption : optionList) {
            rearrangedOptions.put(currentOption, options.get(currentOption));
        }

        setOptions(rearrangedOptions);
    }

    public static int numberOfQuestions(ArrayList<Question> questions) {
        return questions.size();
    }
}