package com.demo.servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import com.demo.dao.UserDao;
import com.demo.model.User;

public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();
        User user = userDao.authenticate(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if ("admin".equals(user.getRole())) {
                response.sendRedirect("admin-dashboard.jsp");  // Redirect to admin dashboard if user is admin
            } else {
                response.sendRedirect("user-dashboard.jsp");  // Redirect to user dashboard if user is regular
            }
        } else {
            request.setAttribute("error", "Invalid username or password.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
