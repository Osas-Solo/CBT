package com.ostech.cbt;

import com.ostech.cbt.database.CandidateManipulator;
import com.ostech.cbt.model.Candidate;
import org.apache.commons.text.StringEscapeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

@WebServlet(name = "SignupServlet", urlPatterns = {"/signup", "/register"})
public class SignupServlet extends HttpServlet {
    private Candidate candidate;
    private String firstNameErrorMessage = "";
    private String lastNameErrorMessage = "";
    private String emailAddressErrorMessage = "";
    private String passwordErrorMessage = "";
    private String passwordConfirmationErrorMessage = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        processSignupForm(request);

        if (areSignupDetailsValid()) {
            completeCandidateSignup(request);
        }

        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    private void processSignupForm(HttpServletRequest request) {
        String firstName = cleanseInput(request.getParameter("firstName"));
        String lastName = cleanseInput(request.getParameter("lastName"));
        String emailAddress = cleanseInput(request.getParameter("emailAddress"));
        String password = cleanseInput(request.getParameter("password"));
        String passwordConfirmation = cleanseInput(request.getParameter("passwordConfirmation"));

        candidate = new Candidate();
        candidate.setFirstName(firstName);
        candidate.setLastName(lastName);
        candidate.setEmailAddress(emailAddress);
        candidate.setPassword(password);

        validateCandidateSignup(passwordConfirmation);

        request.setAttribute("firstName", firstName);
        request.setAttribute("lastName", lastName);
        request.setAttribute("emailAddress", emailAddress);
        request.setAttribute("firstNameErrorMessage", firstNameErrorMessage);
        request.setAttribute("lastNameErrorMessage", lastNameErrorMessage);
        request.setAttribute("emailAddressErrorMessage", emailAddressErrorMessage);
        request.setAttribute("passwordErrorMessage", passwordErrorMessage);
        request.setAttribute("passwordConfirmationErrorMessage", passwordConfirmationErrorMessage);
    }

    private String cleanseInput(String data) {
        String cleansedData = data.trim();
        cleansedData = cleansedData.replaceAll("\\\\", "");
        cleansedData = StringEscapeUtils.escapeHtml4(cleansedData);

        return cleansedData;
    }

    private void validateCandidateSignup(String passwordConfirmation) {
        if (!isNameValid(candidate.getFirstName())) {
            firstNameErrorMessage = "Please, first name should be an uppercase letter followed by lowercase letters and " +
                    "can only have a maximum of 100 letters";
        }

        if (!isNameValid(candidate.getLastName())) {
            lastNameErrorMessage = "Please, last name should be an uppercase letter followed by lowercase letters and " +
                    "can only have a maximum of 100 letters";
        }

        if (!isEmailAddressValid(candidate.getEmailAddress())) {
            emailAddressErrorMessage = "Please, enter a valid email address";
        } else {
            Candidate retrievedCandidate = CandidateManipulator.getCandidate(candidate.getEmailAddress());

            if (retrievedCandidate.getEmailAddress() != null) {
                emailAddressErrorMessage = String.format("Sorry, the email address %s is already in use", candidate.getEmailAddress());
            }
        }

        if (!isPasswordValid(candidate.getPassword())) {
            passwordErrorMessage = "Please, enter a password with at least one uppercase, lowercase and a digit. " +
                    "Passwords must contain at least 8 characters and cannot exceed 20 characters";
        }

        if (!isPasswordConfirmed(candidate.getPassword(), passwordConfirmation)) {
            passwordConfirmationErrorMessage = "Sorry, passwords do not match";
        }
    }

    private boolean areSignupDetailsValid() {
        return firstNameErrorMessage.length() == 0 && lastNameErrorMessage.length() == 0 &&
                emailAddressErrorMessage.length() == 0 && passwordErrorMessage.length() == 0 &&
                passwordConfirmationErrorMessage.length() == 0;
    }

    private boolean isNameValid(String name) {
        Pattern nameRegex = Pattern.compile("^[A-Z][a-z]+$");

        return nameRegex.matcher(name).matches() && name.length() <= 100;
    }

    private boolean isEmailAddressValid(String emailAddress) {
        Pattern emailAddressRegex = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+\\..+)$");

        return emailAddressRegex.matcher(emailAddress).matches() && emailAddress.length() <= 100;
    }

    private boolean isPasswordValid(String password) {
        Pattern lowercaseRegex = Pattern.compile("[a-z]");
        Pattern uppercaseRegex = Pattern.compile("[A-Z]");
        Pattern digitRegex = Pattern.compile("[0-9]");

        return lowercaseRegex.matcher(password).find() && uppercaseRegex.matcher(password).find() &&
                digitRegex.matcher(password).find() && password.length() >= 8 && password.length() <= 20;
    }

    private boolean isPasswordConfirmed(String password, String passwordConfirmation) {
        return password.equals(passwordConfirmation);
    }

    private void completeCandidateSignup(HttpServletRequest request) {
        if (CandidateManipulator.insertCandidate(candidate)) {
            String loginPage = getServletContext().getContextPath() + "/login";
            String signupConfirmationDialog = String.format("<script>" +
                    "if (confirm('You have successfully signed up. You may now proceed to login')) {" +
                    "window.location.replace('%s')" +
                    "} else {" +
                    "window.location.replace('%s')" +
                    "}</script>", loginPage, loginPage);
            request.setAttribute("signupConfirmationDialog", signupConfirmationDialog);
        }
    }
}