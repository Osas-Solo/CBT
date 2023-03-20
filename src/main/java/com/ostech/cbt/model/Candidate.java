package com.ostech.cbt.model;

import com.ostech.cbt.database.CandidateManipulator;

import javax.servlet.http.HttpServletRequest;

public class Candidate {
    private int id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFound() {
        return new Integer(id) != null && firstName != null && lastName != null && emailAddress != null;
    }

    public String getFullName() {
        return getLastName().toUpperCase() + " " + getFirstName();
    }

    public static Candidate retrieveCandidateDetailsFromSession(HttpServletRequest request) {
        if (request.getSession().getAttribute("candidateID") != null) {
            int candidateID = Integer.parseInt((String) request.getSession().getAttribute("candidateID"));
            return CandidateManipulator.getCandidate(candidateID);
        }

        return null;
    }
}
