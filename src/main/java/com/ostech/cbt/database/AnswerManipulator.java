package com.ostech.cbt.database;

import com.ostech.cbt.model.Answer;
import com.ostech.cbt.model.Question;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AnswerManipulator {
    public static ArrayList<Answer> getAnswers(ArrayList<Question> questions) {
        ArrayList<Answer> answers = new ArrayList<>();

        try {
            Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
            String answersQuery = "SELECT id, question_id, correct_option FROM answers WHERE ";

            if (questions.size() > 0) {
                ArrayList<String> questionIDConditions = new ArrayList<>();

                for (Question currentQuestion : questions) {
                    String condition = String.format("question_id = %d", currentQuestion.getId());
                    questionIDConditions.add(condition);
                }

                String conditionClause = String.join(" OR ", questionIDConditions);
                answersQuery += conditionClause;
            } else {
                answersQuery += String.format("question_id = ", 0);
            }

            Statement selectStatement = databaseConnection.createStatement();

            ResultSet resultSet = selectStatement.executeQuery(answersQuery);

            while (resultSet.next()) {
                Answer currentAnswer = new Answer();
                currentAnswer.setId(resultSet.getInt("id"));
                Question question = new Question();
                question.setId(resultSet.getInt("question_id"));
                currentAnswer.setQuestion(question);
                currentAnswer.setCorrectOption(resultSet.getString("correct_option").charAt(0));
                answers.add(currentAnswer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return answers;
    }
}