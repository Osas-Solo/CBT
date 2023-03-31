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
    private Candidate candidate;
    private Subject subject;
    private ArrayList<Question> questions;
    private ArrayList<Answer> answers;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        subject = new Subject();
        candidate = Candidate.retrieveCandidateDetailsFromSession(request);

        if (request.getSession().getAttribute("subject") != null) {
            subject = (Subject) request.getSession().getAttribute("subject");
        }

        if (subject.isFound()) {
            questions = (ArrayList<Question>) request.getSession().getAttribute("questions");
            answers = AnswerManipulator.getAnswers(questions);
            answers = Answer.reorderAnswersToMatchQuestions(questions, answers);
            int numberOfQuestions = Question.numberOfQuestions(questions);
            int numberOfCorrectAnswers = Answer.numberOfCorrectAnswers(questions, answers);

            request.setAttribute("answers", answers);
            request.setAttribute("numberOfQuestions", numberOfQuestions);
            request.setAttribute("numberOfCorrectAnswers", numberOfCorrectAnswers);
        }

        request.setAttribute("candidate", candidate);

        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}
