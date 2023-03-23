package com.ostech.cbt.database;

import com.ostech.cbt.model.Candidate;

import java.sql.*;

public class CandidateManipulator {
    public static boolean insertCandidate(Candidate candidate) {
        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String insertQuery = "INSERT INTO candidates (id, first_name, last_name, email_address, password) VALUES " +
                    "(?, ?, ?, ?, SHA(?))";

            PreparedStatement insertStatement = databaseConnection.prepareStatement(insertQuery);
            insertStatement.setInt(1, candidate.getId());
            insertStatement.setString(2, candidate.getFirstName());
            insertStatement.setString(3, candidate.getLastName());
            insertStatement.setString(4, candidate.getEmailAddress());
            insertStatement.setString(5, candidate.getPassword());

            return insertStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Candidate getCandidate(int candidateID) {
        Candidate candidate = new Candidate();

        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String candidateQuery = "SELECT id, first_name, last_name, email_address, password FROM candidates " +
                    "WHERE id = ?";
            PreparedStatement selectStatement = databaseConnection.prepareStatement(candidateQuery);
            selectStatement.setInt(1, candidateID);

            retrieveCandidateInformation(candidate, databaseConnection, selectStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return candidate;
    }

    public static Candidate getCandidate(String emailAddress) {
        Candidate candidate = new Candidate();

        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String candidateQuery = "SELECT id, first_name, last_name, email_address, password FROM candidates " +
                    "WHERE email_address = ?";
            PreparedStatement selectStatement = databaseConnection.prepareStatement(candidateQuery);
            selectStatement.setString(1, emailAddress);

            retrieveCandidateInformation(candidate, databaseConnection, selectStatement);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return candidate;
    }

    public static Candidate getCandidate(String emailAddress, String password) {
        Candidate candidate = new Candidate();

        try {
            Connection databaseConnection = new DatabaseConfiguration().getDatabaseConnection();
            String candidateQuery = "SELECT id, first_name, last_name, email_address, password FROM candidates " +
                    "WHERE email_address = ? AND password = SHA(?)";
            PreparedStatement selectStatement = databaseConnection.prepareStatement(candidateQuery);
            selectStatement.setString(1, emailAddress);
            selectStatement.setString(2, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return candidate;
    }

    private static void retrieveCandidateInformation(Candidate candidate, Connection databaseConnection,
                                                     PreparedStatement selectStatement) throws SQLException {
        ResultSet resultSet = selectStatement.executeQuery();

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