package com.ostech.cbt.database;

import com.ostech.cbt.model.Subject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SubjectManipulator {
    public static Subject getSubject(int subjectID) {
        Subject subject = new Subject();

        try {
            Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
            String subjectQuery = String.format("SELECT id, name FROM subjects WHERE id = %d", subjectID);
            retrieveSubjectInformation(subject, databaseConnection, subjectQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

    public static Subject getSubject(String subjectName) {
        Subject subject = new Subject();

        try {
            Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
            String subjectQuery = String.format("SELECT id, name " +
                    "FROM subjects WHERE name = '%s'", subjectName);
            retrieveSubjectInformation(subject, databaseConnection, subjectQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return subject;
    }

    public static int getMaximumNumberOfQuestions(int subjectID) {
        int questionsCount = 0;

        try {
            Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
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

    private static void retrieveSubjectInformation(Subject subject, Connection databaseConnection, String subjectQuery) throws SQLException {
        Statement selectStatement = databaseConnection.createStatement();

        ResultSet resultSet = selectStatement.executeQuery(subjectQuery);

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
            Connection databaseConnection = DatabaseConfiguration.getDatabaseConnection();
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