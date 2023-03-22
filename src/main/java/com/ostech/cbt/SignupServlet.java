package com.ostech.cbt;

import com.ostech.cbt.database.CandidateManipulator;
import com.ostech.cbt.model.Candidate;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.regex.Matcher;
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

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String emailAddress = request.getParameter("emailAddress");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("passwordConfirmation");

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

        request.getRequestDispatcher("signup.jsp").forward(request, response);
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
                emailAddressErrorMessage = String.format("Sorry, the email address, %s is already in use", candidate.getEmailAddress());
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

    private boolean isNameValid(String name) {
        Pattern nameRegex = Pattern.compile("[A-Z][a-z]+");

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
}