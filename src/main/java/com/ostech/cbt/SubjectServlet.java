package com.ostech.cbt;

import com.ostech.cbt.database.SubjectManipulator;
import com.ostech.cbt.model.Candidate;
import com.ostech.cbt.model.Subject;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;

@WebServlet(name = "SubjectServlet", urlPatterns = {"/subject/*"})
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
        } else {
            subject = new Subject();
        }

        request.setAttribute("candidate", candidate);
        request.setAttribute("subject", subject);
        request.setAttribute("isSpecificSubjectRequested", isSpecificSubjectRequested);

        request.getRequestDispatcher("subject.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private boolean isSpecificSubjectRequested(HttpServletRequest request) {
        return request.getParameter("name") != null;
    }

    private Subject retrieveSubject(HttpServletRequest request) {
        String subjectName = request.getParameter("name");

        return SubjectManipulator.getSubject(subjectName);
    }
}