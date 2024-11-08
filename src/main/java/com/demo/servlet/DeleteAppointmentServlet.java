package com.demo.servlet;

import com.demo.dao.AppointmentDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteAppointment")
public class DeleteAppointmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get appointmentId from request
        String appointmentId = request.getParameter("appointmentId");

        // Delete appointment from the database
        AppointmentDAO appointmentDao = new AppointmentDAO();
        try {
            appointmentDao.deleteAppointment(Integer.parseInt(appointmentId));
        } catch (NumberFormatException | SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        // Redirect to appointment list page
        response.sendRedirect("appointmentList");
    }
}

