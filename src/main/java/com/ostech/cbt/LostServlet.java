package com.ostech.cbt;

import com.ostech.cbt.model.Candidate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LostServlet", urlPatterns = {"/404"})
public class LostServlet extends HttpServlet {
    private Candidate candidate;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        candidate = Candidate.retrieveCandidateDetailsFromSession(request);

        request.setAttribute("candidate", candidate);
        request.getRequestDispatcher("404.jsp").forward(request, response);
    }

    public void destroy() {
    }
}