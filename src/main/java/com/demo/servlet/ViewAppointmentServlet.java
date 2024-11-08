package com.demo.servlet;

import com.demo.dao.AppointmentDAO;
import com.demo.model.Appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/viewAppointment")
public class ViewAppointmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get appointmentId from request
        String appointmentId = request.getParameter("appointmentId");

        // Fetch appointment details from the database
        AppointmentDAO appointmentDao = new AppointmentDAO();
        Appointment appointment;
        try {
            appointment = appointmentDao.getAppointmentById(Integer.parseInt(appointmentId));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return;
        }

        // Set appointment as request attribute and forward to JSP
        request.setAttribute("appointment", appointment);
        request.getRequestDispatcher("/viewAppointment.jsp").forward(request, response);
    }
}
