package com.demo.servlet;

import com.demo.dao.DoctorDAO;
import com.demo.model.Doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/addDoctor")
public class AddDoctorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Show the form to add a doctor
        request.getRequestDispatcher("/addDoctor.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String name = request.getParameter("name");
        String specialization = request.getParameter("specialization");
        String contactNumber = request.getParameter("contactNumber");

        // Create a new doctor object
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setContactNumber(contactNumber);

        // Use DoctorDao to save the doctor to the database
        DoctorDAO doctorDao = new DoctorDAO();
        doctorDao.addDoctor(doctor);

        // Redirect to doctor list page
        response.sendRedirect("doctorList");
    }
}
