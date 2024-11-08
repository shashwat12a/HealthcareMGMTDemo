package com.demo.servlet;

import com.demo.dao.DoctorDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteDoctor")
public class DeleteDoctorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get doctor ID from the request
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));

        // Use DoctorDao to delete the doctor from the database
        DoctorDAO doctorDao = new DoctorDAO();
        doctorDao.deleteDoctor(doctorId);

        // Redirect to the doctor list
        response.sendRedirect("doctorList");
    }
}
