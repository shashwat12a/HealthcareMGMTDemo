package com.demo.servlet;

import com.demo.dao.DoctorDAO;
import com.demo.model.Doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/editDoctor")
public class EditDoctorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get doctor ID from the request
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));

        // Get the doctor details from the database
        DoctorDAO doctorDao = new DoctorDAO();
        Doctor doctor = doctorDao.getDoctorById(doctorId);

        // Set the doctor as a request attribute and forward to the edit form
        request.setAttribute("doctor", doctor);
        request.getRequestDispatcher("/editDoctor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the form data
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String name = request.getParameter("name");
        String specialization = request.getParameter("specialization");
        String contactNumber = request.getParameter("contactNumber");

        // Create a doctor object and set the new values
        Doctor doctor = new Doctor();
        doctor.setDoctorId(doctorId);
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setContactNumber(contactNumber);

        // Use DoctorDao to update the doctor in the database
        DoctorDAO doctorDao = new DoctorDAO();
        doctorDao.updateDoctor(doctor);

        // Redirect to the doctor list
        response.sendRedirect("doctorList");
    }
}
