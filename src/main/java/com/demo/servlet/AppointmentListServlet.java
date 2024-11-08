package com.demo.servlet;

import com.demo.dao.AppointmentDAO;
import com.demo.model.Appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/appointmentList")
public class AppointmentListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get list of all appointments
        AppointmentDAO appointmentDao = new AppointmentDAO();
        List<Appointment> appointments;
        try {
            appointments = appointmentDao.getAllAppointments();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        // Set appointments as request attribute and forward to JSP
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("/appointmentList.jsp").forward(request, response);
    }
}
