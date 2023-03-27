package com.ostech.cbt;

import com.ostech.cbt.database.QuestionManipulator;
import com.ostech.cbt.database.SubjectManipulator;
import com.ostech.cbt.model.Candidate;
import com.ostech.cbt.model.InputUtils;
import com.ostech.cbt.model.Question;
import com.ostech.cbt.model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "TestServlet", urlPatterns = {"/test", "/exam"})
public class TestServlet extends HttpServlet {
    Candidate candidate;
    Subject subject;
    ArrayList<Question> questions;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subjectPage = getServletContext().getContextPath() + "/subject";
        response.sendRedirect(subjectPage);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        candidate = Candidate.retrieveCandidateDetailsFromSession(request);
        subject = SubjectManipulator.getSubject(
                Integer.parseInt(InputUtils.cleanseInput(request.getParameter("subjectID"))));

        if (subject.isFound()) {
            questions = retrieveQuestions(request);
            request.getSession().setAttribute("questions", questions);
            int testTime = (int) (questions.size() * 60 * 1.5);
            request.setAttribute("testTime", testTime);
        }

        request.setAttribute("candidate", candidate);
        request.setAttribute("subject", subject);
        request.setAttribute("questions", questions);

        request.getRequestDispatcher("test.jsp").forward(request, response);
    }

    private ArrayList<Question> retrieveQuestions(HttpServletRequest request) {
        int subjectID = Integer.parseInt(InputUtils.cleanseInput(request.getParameter("subjectID")));
        int numberOfQuestions = Integer.parseInt(InputUtils.cleanseInput(request.getParameter("numberOfQuestions")));

        return QuestionManipulator.getQuestions(subjectID, numberOfQuestions);
    }
}