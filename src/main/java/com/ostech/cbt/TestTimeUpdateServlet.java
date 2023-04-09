package com.ostech.cbt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestTimeUpdateServlet", urlPatterns = {"/updateTestTime"})
public class TestTimeUpdateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        updateTestTime(request);
    }

    private static void updateTestTime(HttpServletRequest request) {
        int newTestTime = Integer.parseInt(request.getParameter("newTestTime"));

        if (newTestTime != 0) {
            request.getSession().setAttribute("testTime", newTestTime);
        }
    }
}