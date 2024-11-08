package com.demo.servlet;

import com.demo.dao.PatientDAO;
import com.demo.model.Patient;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class EditPatientServlet extends HttpServlet {

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
            // Fetch the patient data to populate the form
            Patient patient = patientDAO.getPatientById(patientId);
            if (patient != null) {
                request.setAttribute("patient", patient);
                request.getRequestDispatcher("/editPatient.jsp").forward(request, response);
            } else {
                response.sendRedirect("patientList");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error fetching patient details: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get updated patient details from the form
            int id = Integer.parseInt(request.getParameter("id"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String dob = request.getParameter("dob");
            String gender = request.getParameter("gender");
            String phone = request.getParameter("phone");

            // Create a Patient object and set updated details
            Patient patient = new Patient();
            patient.setId(id);
            patient.setFirstName(firstName);
            patient.setLastName(lastName);
            patient.setDob(dob);
            patient.setGender(gender);
            patient.setPhone(phone);

            // Update the patient details in the database
            patientDAO.updatePatient(patient);

            response.sendRedirect("patientDetails?id=" + id);  // Redirect to patient details page
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error updating patient: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}

