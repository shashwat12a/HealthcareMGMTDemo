package com.demo.servlet;

import com.demo.dao.AppointmentDAO;
import com.demo.model.Appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/editAppointment")
public class EditAppointmentServlet extends HttpServlet {

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

        // Set appointment as request attribute and forward to JSP for editing
        request.setAttribute("appointment", appointment);
        request.getRequestDispatcher("/editAppointment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String appointmentId = request.getParameter("appointmentId");
        String doctor = request.getParameter("doctor");
        String appointmentDate = request.getParameter("appointmentDate");

        // Create updated appointment object
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(Integer.parseInt(appointmentId));
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDate);

        // Update appointment in the database
        AppointmentDAO appointmentDao = new AppointmentDAO();
        appointmentDao.updateAppointment(appointment);

        // Redirect to appointment list page
        response.sendRedirect("appointmentList");
    }
}
