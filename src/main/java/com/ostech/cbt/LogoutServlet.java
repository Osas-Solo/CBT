package com.ostech.cbt;

import com.ostech.cbt.model.Candidate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout", "/signout"})
public class LogoutServlet extends HttpServlet {
    private Candidate candidate;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        candidate = Candidate.retrieveCandidateDetailsFromSession(request);

        request.setAttribute("candidate", candidate);
        request.getRequestDispatcher("logout.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        
        candidate = new Candidate();

        request.setAttribute("candidate", candidate);
        request.setAttribute("logoutMessage", "You have been logged out successfully");
        request.getRequestDispatcher("logout.jsp").forward(request, response);
    }
}