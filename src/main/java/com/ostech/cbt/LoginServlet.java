package com.ostech.cbt;

import com.ostech.cbt.database.CandidateManipulator;
import com.ostech.cbt.model.Candidate;
import com.ostech.cbt.model.InputUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login", "/signin"})
public class LoginServlet extends HttpServlet {
    private Candidate candidate;
    private String emailAddressErrorMessage = "";
    private String passwordErrorMessage = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        request.setAttribute("candidate", new Candidate());
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        request.setAttribute("candidate", new Candidate());

        processLoginForm(request);

        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    private void processLoginForm(HttpServletRequest request) {
        String emailAddress = InputUtils.cleanseInput(request.getParameter("emailAddress"));
        String password = InputUtils.cleanseInput(request.getParameter("password"));

        candidate = new Candidate();
        candidate.setEmailAddress(emailAddress);
        candidate.setPassword(password);

        validateCandidateLogin(request);

        request.setAttribute("emailAddress", emailAddress);
        request.setAttribute("emailAddressErrorMessage", emailAddressErrorMessage);
        request.setAttribute("passwordErrorMessage", passwordErrorMessage);
    }

    private void validateCandidateLogin(HttpServletRequest request) {
        Candidate retrievedCandidate = CandidateManipulator.getCandidate(candidate.getEmailAddress());

        if (!retrievedCandidate.isFound()) {
            emailAddressErrorMessage = String.format("Sorry, no candidate with the email address %s could be found", candidate.getEmailAddress());
        } else {
            emailAddressErrorMessage = "";
            retrievedCandidate = CandidateManipulator.getCandidate(candidate.getEmailAddress(), candidate.getPassword());

            if (!retrievedCandidate.isFound()) {
                passwordErrorMessage = "Sorry, the password you have entered is incorrect";
            } else {
                passwordErrorMessage = "";
                candidate = retrievedCandidate;
                startCandidateSession(request);
            }
        }

        if (areLoginDetailsValid()) {
            String testPage = getServletContext().getContextPath() + "/test";
            String loginConfirmationDialog = String.format("<script>" +
                    "if (confirm('You have successfully logged in. You may now proceed to take a test')) {" +
                        "window.location.replace('%s')" +
                    "} else {" +
                        "window.location.replace('%s')" +
                    "}</script>", testPage, testPage);
            request.setAttribute("loginConfirmationDialog", loginConfirmationDialog);
        }
    }

    private boolean areLoginDetailsValid() {
        return emailAddressErrorMessage.length() == 0 && passwordErrorMessage.length() == 0;
    }

    private void startCandidateSession(HttpServletRequest request) {
        request.getSession().setAttribute("candidateID", candidate.getId());
    }
}