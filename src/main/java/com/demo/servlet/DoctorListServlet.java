package com.demo.servlet;

import com.demo.dao.DoctorDAO;
import com.demo.model.Doctor;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctorList")
public class DoctorListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Fetch all doctors
        DoctorDAO doctorDao = new DoctorDAO();
        List<Doctor> doctors = doctorDao.getAllDoctors();

        // Set doctors as request attribute and forward to the JSP
        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("/doctorList.jsp").forward(request, response);
    }
}
