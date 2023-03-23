package com.ostech.cbt.database;

import com.ostech.cbt.model.Subject;

import java.sql.*;
import java.util.ArrayList;

public class SubjectManipulator {
    public static Subject getSubject(int subjectID) {
        Subject subject = new Subject();

        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String subjectQuery = "SELECT id, name FROM subjects WHERE id = ?";
            PreparedStatement selectStatement = databaseConnection.prepareStatement(subjectQuery);
            selectStatement.setInt(1, subjectID);
            retrieveSubjectInformation(subject, databaseConnection, selectStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

    public static Subject getSubject(String subjectName) {
        Subject subject = new Subject();

        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String subjectQuery = "SELECT id, name FROM subjects WHERE name = ?";
            PreparedStatement selectStatement = databaseConnection.prepareStatement(subjectQuery);
            selectStatement.setString(1, subjectName);
            retrieveSubjectInformation(subject, databaseConnection, selectStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

    public static int getMaximumNumberOfQuestions(int subjectID) {
        int questionsCount = 0;

        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String questionsCountQuery = String.format("SELECT count(*) FROM questions " +
                    "WHERE subject_id = '%d'", subjectID);

            Statement selectStatement = databaseConnection.createStatement();
            ResultSet resultSet = selectStatement.executeQuery(questionsCountQuery);

            if (resultSet.next()) {
                questionsCount = resultSet.getInt(1);
            }

            selectStatement.close();
            databaseConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return questionsCount;
    }

    private static void retrieveSubjectInformation(Subject subject, Connection databaseConnection, PreparedStatement selectStatement) throws SQLException {
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            subject.setId(resultSet.getInt("id"));
            subject.setName(resultSet.getString("name"));
        }

        selectStatement.close();
        databaseConnection.close();
    }

    public static ArrayList<Subject> getAllSubjects() {
        ArrayList<Subject> subjects = new ArrayList<>();

        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String subjectsQuery = "SELECT id, name FROM subjects ORDER BY name";

            Statement selectStatement = databaseConnection.createStatement();

            ResultSet resultSet = selectStatement.executeQuery(subjectsQuery);

            while (resultSet.next()) {
                Subject currentSubject = new Subject();
                currentSubject.setId(resultSet.getInt("id"));
                currentSubject.setName(resultSet.getString("name"));
                subjects.add(currentSubject);
            }

            selectStatement.close();
            databaseConnection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subjects;
    }
}