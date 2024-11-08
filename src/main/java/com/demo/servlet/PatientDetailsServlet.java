package com.demo.servlet;

import com.demo.dao.PatientDAO;
import com.demo.model.Patient;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class PatientDetailsServlet extends HttpServlet {

    private PatientDAO patientDAO;

    @Override
    public void init() {
        patientDAO = new PatientDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get the patient ID from the request parameter
            int patientId = Integer.parseInt(request.getParameter("id"));
            // Fetch the patient from the database
            Patient patient = patientDAO.getPatientById(patientId);
            if (patient != null) {
                request.setAttribute("patient", patient);
                request.getRequestDispatcher("/patientDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect("patientList");  // If patient not found, redirect to the list
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error retrieving patient details: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
