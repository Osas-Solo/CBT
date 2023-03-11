package com.ostech.cbt.database;

import com.ostech.cbt.model.Question;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class QuestionManipulator {
    public static ArrayList<Question> getQuestions(int subjectID, int numberOfQuestions) {
        ArrayList<Question> questions = new ArrayList<>();

        try {
            Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
            String questionsQuery = String.format("SELECT id, subject_id, question, option_a, option_b, option_c, option_d " +
                    "FROM questions WHERE subject_id = %d " +
                    "ORDER BY RAND() LIMIT %d", subjectID, numberOfQuestions);

            Statement selectStatement = databaseConnection.createStatement();

            ResultSet resultSet = selectStatement.executeQuery(questionsQuery);

            while (resultSet.next()) {
                Question currentQuestion = new Question();
                currentQuestion.setId(resultSet.getInt("id"));
                currentQuestion.setSubjectID(resultSet.getInt("subject_id"));
                currentQuestion.setQuestion(resultSet.getString("question"));
                HashMap<Character, String> options = new HashMap<>();

                final int firstOptionColumnIndex = 4;
                final int lastOptionColumnIndex = 7;

                char[] optionLabels = {'A', 'B', 'C', 'D'};

                for (int i = firstOptionColumnIndex; i <= lastOptionColumnIndex; i++) {
                    char currentOptionLabel = optionLabels[i - firstOptionColumnIndex];
                    String currentOption = resultSet.getString(i);
                    options.put(currentOptionLabel, currentOption);
                }

                currentQuestion.setOptions(options);
                currentQuestion.shuffleOptions();
                questions.add(currentQuestion);
            }

            selectStatement.close();
            databaseConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questions;
    }
}