package com.demo.dao;

import com.demo.model.Patient;
import com.demo.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // Add a new patient to the database
    public void addPatient(Patient patient) throws SQLException {
        String sql = "INSERT INTO patients (first_name, last_name, dob, gender, phone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for the PreparedStatement
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setDate(3, Date.valueOf(patient.getDob()));  // Convert String to SQL Date
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getPhone());

            // Execute the update
            stmt.executeUpdate();
        }
    }

    // Get a patient by their ID
    public Patient getPatientById(int id) throws SQLException {
        Patient patient = null;
        String sql = "SELECT * FROM patients WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    patient = new Patient();
                    patient.setId(rs.getInt("id"));
                    patient.setFirstName(rs.getString("first_name"));
                    patient.setLastName(rs.getString("last_name"));
                    patient.setDob(rs.getString("dob"));  // Assuming the dob is in 'YYYY-MM-DD' format
                    patient.setGender(rs.getString("gender"));
                    patient.setPhone(rs.getString("phone"));
                }
            }
        }
        return patient;
    }

    // Get all patients from the database
    public List<Patient> getPatients() throws SQLException {
        List<Patient> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId(rs.getInt("id"));
                patient.setFirstName(rs.getString("first_name"));
                patient.setLastName(rs.getString("last_name"));
                patient.setDob(rs.getString("dob"));
                patient.setGender(rs.getString("gender"));
                patient.setPhone(rs.getString("phone"));
                patients.add(patient);
            }
        }
        return patients;
    }

    // Update an existing patient's details
    public void updatePatient(Patient patient) throws SQLException {
        String sql = "UPDATE patients SET first_name = ?, last_name = ?, dob = ?, gender = ?, phone = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setDate(3, Date.valueOf(patient.getDob()));  // Convert String to SQL Date
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getPhone());
            stmt.setInt(6, patient.getId());

            stmt.executeUpdate();
        }
    }

    // Delete a patient by their ID
    public void deletePatient(int id) throws SQLException {
        String sql = "DELETE FROM patients WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
