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

    public String getFullName() {
        return getLastName().toUpperCase() + " " + getFirstName();
    }

    public boolean isFound() {
        return getEmailAddress() != null;
    }

    public static Candidate retrieveCandidateDetailsFromSession(HttpServletRequest request) {
        Candidate candidate = new Candidate();
        
        if (request.getSession().getAttribute("candidateID") != null) {
            int candidateID = (int) request.getSession().getAttribute("candidateID");
            candidate = CandidateManipulator.getCandidate(candidateID);
        }

        return candidate;
    }
}