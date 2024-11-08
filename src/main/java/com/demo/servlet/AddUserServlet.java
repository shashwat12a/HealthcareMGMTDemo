package com.demo.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import com.demo.model.User;
import com.demo.dao.*;

public class AddUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null || !"admin".equals(((User) session.getAttribute("user")).getRole())) {
            response.sendRedirect("login");  // Redirect to login if not logged in as admin
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/add-user.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User newUser = new User(0, username, password, role);
        UserDao userDao = new UserDao();

        if (userDao.addUser(newUser)) {
            response.sendRedirect("admin-dashboard.jsp");  // Redirect back to admin dashboard
        } else {
            request.setAttribute("error", "Failed to add new user.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/add-user.jsp");
            dispatcher.forward(request, response);
        }
    }
}
