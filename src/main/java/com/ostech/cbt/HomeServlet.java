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

@WebServlet(name = "HomeServlet", urlPatterns = {"/home", "/index"})
public class HomeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        Candidate candidate = Candidate.retrieveCandidateDetailsFromSession(request);
        ArrayList<Subject> subjects = SubjectManipulator.getAllSubjects();

        request.setAttribute("candidate", candidate);
        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}