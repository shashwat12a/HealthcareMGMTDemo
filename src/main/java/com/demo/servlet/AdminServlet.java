package com.demo.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import com.demo.model.*;

public class AdminServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null || !"admin".equals(((User) session.getAttribute("user")).getRole())) {
            response.sendRedirect("login");  // If not logged in as admin, redirect to login
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/admin-dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
