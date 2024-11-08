package com.demo.servlet;

import com.demo.dao.PatientDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class DeletePatientServlet extends HttpServlet {

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
            // Delete the patient from the database
            patientDAO.deletePatient(patientId);
            response.sendRedirect("patientList");  // Redirect to the patient list after deletion
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error deleting patient: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
