package com.ostech.cbt;

import com.ostech.cbt.database.SubjectManipulator;
import com.ostech.cbt.model.Subject;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(urlPatterns = {"/", "/home", "/index"})
public class HomeServlet extends HttpServlet {
    private ArrayList<Subject> subjects;

    public void init() {

    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        subjects = SubjectManipulator.getAllSubjects();

        for (Subject currentSubject: subjects) {
            System.out.println(currentSubject.getName());
        }

        request.setAttribute("subjects", subjects);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public void destroy() {
    }
}