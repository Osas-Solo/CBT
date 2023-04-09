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
    private Candidate candidate;
    private Subject subject;
    private ArrayList<Question> questions;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        candidate = Candidate.retrieveCandidateDetailsFromSession(request);

        subject = (Subject) request.getSession().getAttribute("subject");
        questions = (ArrayList<Question>) request.getSession().getAttribute("questions");
        int testTime = 0;

        if (request.getSession().getAttribute("testTime") != null) {
            testTime = (int) request.getSession().getAttribute("testTime");
        }

        if (subject != null && questions != null && testTime != 0) {
            response.setContentType("text/html");

            request.setAttribute("candidate", candidate);
            request.getSession().setAttribute("subject", subject);
            request.getSession().setAttribute("questions", questions);
            request.getSession().setAttribute("testTime", testTime);

            request.getRequestDispatcher("test.jsp").forward(request, response);
        } else {
            String subjectPage = getServletContext().getContextPath() + "/subject";
            response.sendRedirect(subjectPage);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        candidate = Candidate.retrieveCandidateDetailsFromSession(request);
        subject = SubjectManipulator.getSubject(
                Integer.parseInt(InputUtils.cleanseInput(request.getParameter("subjectID"))));

        if (subject.isFound()) {
            questions = retrieveQuestions(request);
            int testTime = (int) (questions.size() * 60 * 1.5);

            request.getSession().setAttribute("subject", subject);
            request.getSession().setAttribute("questions", questions);
            request.getSession().setAttribute("testTime", testTime);
        }

        request.setAttribute("candidate", candidate);

        request.getRequestDispatcher("test.jsp").forward(request, response);
    }

    private ArrayList<Question> retrieveQuestions(HttpServletRequest request) {
        int subjectID = Integer.parseInt(InputUtils.cleanseInput(request.getParameter("subjectID")));
        int numberOfQuestions = Integer.parseInt(InputUtils.cleanseInput(request.getParameter("numberOfQuestions")));

        return QuestionManipulator.getQuestions(subjectID, numberOfQuestions);
    }
}