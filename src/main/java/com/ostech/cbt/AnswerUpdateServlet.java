package com.ostech.cbt;

import com.ostech.cbt.model.Question;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AnswerUpdateServlet", urlPatterns = {"/updateAnswer"})
public class AnswerUpdateServlet extends HttpServlet {
    ArrayList<Question> questions;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        questions = (ArrayList<Question>) request.getSession().getAttribute("questions");
        int questionNumber = Integer.parseInt(request.getParameter("questionNumber"));
        char selectedOption = request.getParameter("selectedOption").charAt(0);

        updateAnswer(questionNumber, selectedOption);
        request.getSession().setAttribute("questions", questions);
    }

    private void updateAnswer(int questionNumber, char selectedOption) {
        questions.get(questionNumber - 1).setSelectedOption(selectedOption);
    }
}