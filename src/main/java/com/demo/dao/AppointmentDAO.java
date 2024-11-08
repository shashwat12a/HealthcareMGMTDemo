package com.demo.dao;

import com.demo.model.Appointment;
import com.demo.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

    // Add a new appointment to the database
    public void addAppointment(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointments (patient_id, appointment_date, reason) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set parameters for the PreparedStatement
            stmt.setInt(1, appointment.getPatientId());
            stmt.setTimestamp(2, new Timestamp(appointment.getAppointmentDate().getTime())); // Convert Date to Timestamp
            stmt.setString(3, appointment.getReason());

            // Execute the update
            stmt.executeUpdate();
        }
    }

    // Get an appointment by its ID
    public Appointment getAppointmentById(int id) throws SQLException {
        Appointment appointment = null;
        String sql = "SELECT * FROM appointments WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    appointment = new Appointment();
                    appointment.setId(rs.getInt("id"));
                    appointment.setPatientId(rs.getInt("patient_id"));
                    appointment.setAppointmentDate(rs.getTimestamp("appointment_date"));
                    appointment.setReason(rs.getString("reason"));
                }
            }
        }
        return appointment;
    }

    // Get all appointments from the database
    public List<Appointment> getAllAppointments() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointments";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(rs.getInt("id"));
                appointment.setPatientId(rs.getInt("patient_id"));
                appointment.setAppointmentDate(rs.getTimestamp("appointment_date"));
                appointment.setReason(rs.getString("reason"));
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    // Update an existing appointment
    public void updateAppointment(Appointment appointment) throws SQLException {
        String sql = "UPDATE appointments SET patient_id = ?, appointment_date = ?, reason = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, appointment.getPatientId());
            stmt.setTimestamp(2, new Timestamp(appointment.getAppointmentDate().getTime())); // Convert Date to Timestamp
            stmt.setString(3, appointment.getReason());
            stmt.setInt(4, appointment.getId());

            stmt.executeUpdate();
        }
    }

    // Delete an appointment by its ID
    public void deleteAppointment(int id) throws SQLException {
        String sql = "DELETE FROM appointments WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
