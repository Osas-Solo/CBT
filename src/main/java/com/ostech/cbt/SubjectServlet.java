package com.ostech.cbt;

import com.ostech.cbt.database.SubjectManipulator;
import com.ostech.cbt.model.Candidate;
import com.ostech.cbt.model.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "SubjectServlet", urlPatterns = {"/subject"})
public class SubjectServlet extends HttpServlet {
    Candidate candidate;
    Subject subject;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        candidate = Candidate.retrieveCandidateDetailsFromSession(request);

        boolean isSpecificSubjectRequested = isSpecificSubjectRequested(request);

        if (isSpecificSubjectRequested) {
            subject = retrieveSubject(request);
            int maximumNumberOfQuestions = SubjectManipulator.getMaximumNumberOfQuestions(subject.getId());
            request.setAttribute("maximumNumberOfQuestions", maximumNumberOfQuestions);
        } else {
            subject = new Subject();
            ArrayList<Subject> availableSubjects = SubjectManipulator.getAllSubjects();
            request.setAttribute("availableSubjects", availableSubjects);
        }

        request.setAttribute("candidate", candidate);
        request.setAttribute("subject", subject);
        request.setAttribute("isSpecificSubjectRequested", isSpecificSubjectRequested);

        request.getRequestDispatcher("subject.jsp").forward(request, response);
    }

    private boolean isSpecificSubjectRequested(HttpServletRequest request) {
        return request.getParameter("name") != null;
    }

    private Subject retrieveSubject(HttpServletRequest request) {
        String subjectName = request.getParameter("name");

        return SubjectManipulator.getSubject(subjectName);
    }
}