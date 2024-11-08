package com.demo.servlet;

import com.demo.dao.AppointmentDAO;
import com.demo.model.Appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet("/addAppointment")
public class AddAppointmentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/addAppointment.jsp").forward(request, response);  // Show the form to add an appointment
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String patientId = request.getParameter("patientId");
        Integer doctor = Integer.parseInt(request.getParameter("doctorId"));
        String appointmentDateStr = request.getParameter("appointmentDate");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date appointmentDate = null;
        try {
            appointmentDate = dateFormat.parse(appointmentDateStr);
        } catch (ParseException e) {
            e.printStackTrace();  // Handle the exception
        }
        // Create appointment object
        Appointment appointment = new Appointment();
        appointment.setPatientId(Integer.parseInt(patientId));
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(appointmentDate);

        // Save appointment to the database using AppointmentDao
        AppointmentDAO appointmentDao = new AppointmentDAO();
        try {
            appointmentDao.addAppointment(appointment);
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }
        // Redirect to appointment list page
        response.sendRedirect("appointmentList");
    }
}
