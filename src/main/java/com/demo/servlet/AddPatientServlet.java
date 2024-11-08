package com.demo.servlet;

import com.demo.dao.PatientDAO;
import com.demo.model.Patient;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class AddPatientServlet extends HttpServlet {

    private PatientDAO patientDAO;

    @Override
    public void init() {
        patientDAO = new PatientDAO(); // Initialize the DAO
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get patient details from the request parameters
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String dob = request.getParameter("dob");  // Format: YYYY-MM-DD
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");

        // Create a new Patient object and set the details
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setDob(dob);
        patient.setGender(gender);
        patient.setPhone(phone);

        try {
            // Save patient in the database using DAO
            patientDAO.addPatient(patient);
            response.sendRedirect("patientList");  // Redirect to the patient list page
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error adding patient: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Show the form to add a new patient
        System.out.println("jjj");
        request.getRequestDispatcher("/addPatient.jsp").forward(request, response);
    }
}

