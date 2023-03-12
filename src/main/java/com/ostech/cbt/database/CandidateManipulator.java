package com.ostech.cbt.database;

import com.ostech.cbt.model.Candidate;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CandidateManipulator {
    public static boolean insertCandidate(Candidate candidate) {
        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String insertQuery = String.format("INSERT INTO candidates (id, first_name, last_name, email_address, password) " +
                    "VALUES (%d, '%s', '%s', '%s', SHA('%s'))", candidate.getId(), candidate.getFirstName(), candidate.getLastName(), candidate.getEmailAddress(), candidate.getPassword());
            Statement insertStatement = databaseConnection.createStatement();

            return insertStatement.execute(insertQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Candidate getCandidate(int candidateID) {
        Candidate candidate = new Candidate();

        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String candidateQuery = String.format("SELECT id, first_name, last_name, email_address, password FROM candidates WHERE id = %d", candidateID);
            retrieveCandidateInformation(candidate, databaseConnection, candidateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return candidate;
    }

    public static Candidate getCandidate(String emailAddress) {
        Candidate candidate = new Candidate();

        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String candidateQuery = String.format("SELECT id, first_name, last_name, email_address, password " +
                    "FROM candidates WHERE email_address = '%s'", emailAddress);
            retrieveCandidateInformation(candidate, databaseConnection, candidateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return candidate;
    }

    public static Candidate getCandidate(String emailAddress, String password) {
        Candidate candidate = new Candidate();

        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String candidateQuery = String.format("SELECT id, first_name, last_name, email_address, password " +
                    "FROM candidates WHERE email_address = '%s' AND password = SHA('%s')", emailAddress, password);
            retrieveCandidateInformation(candidate, databaseConnection, candidateQuery);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return candidate;
    }

    private static void retrieveCandidateInformation(Candidate candidate, Connection databaseConnection, String candidateQuery) throws SQLException {
        Statement selectStatement = databaseConnection.createStatement();

        ResultSet resultSet = selectStatement.executeQuery(candidateQuery);

        if (resultSet.next()) {
            candidate.setId(resultSet.getInt("id"));
            candidate.setFirstName(resultSet.getString("first_name"));
            candidate.setLastName(resultSet.getString("last_name"));
            candidate.setEmailAddress(resultSet.getString("email_address"));
        }

        selectStatement.close();
        databaseConnection.close();
    }
}