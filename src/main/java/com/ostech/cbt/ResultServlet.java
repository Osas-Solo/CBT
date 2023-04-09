package com.ostech.cbt;

import com.ostech.cbt.database.AnswerManipulator;
import com.ostech.cbt.model.Answer;
import com.ostech.cbt.model.Candidate;
import com.ostech.cbt.model.Question;
import com.ostech.cbt.model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ResultServlet", urlPatterns = {"/result"})
public class ResultServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        Subject subject = new Subject();
        Candidate candidate = Candidate.retrieveCandidateDetailsFromSession(request);

        if (request.getSession().getAttribute("subject") != null) {
            subject = (Subject) request.getSession().getAttribute("subject");
        }

        if (subject.isFound()) {
            ArrayList<Question> questions = (ArrayList<Question>) request.getSession().getAttribute("questions");
            ArrayList<Answer> answers = AnswerManipulator.getAnswers(questions);
            answers = Answer.reorderAnswersToMatchQuestions(questions, answers);
            int numberOfQuestions = Question.numberOfQuestions(questions);
            int numberOfCorrectAnswers = Answer.numberOfCorrectAnswers(questions, answers);

            request.setAttribute("answers", answers);
            request.setAttribute("numberOfQuestions", numberOfQuestions);
            request.setAttribute("numberOfCorrectAnswers", numberOfCorrectAnswers);

            request.setAttribute("candidate", candidate);

            request.getRequestDispatcher("result.jsp").forward(request, response);
        } else {
            String testPage = getServletContext().getContextPath() + "/test";
            response.sendRedirect(testPage);
        }
    }
}
