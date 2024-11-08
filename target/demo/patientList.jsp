<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patient List</title>
</head>
<body>
    <h1>Patient List</h1>
    <a href="addPatient">Add New Patient</a>
    <table border="1">
        <thead>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Date of Birth</th>
                <th>Gender</th>
                <th>Phone</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="patient" items="${patients}">
                <tr>
                    <td>${patient.firstName}</td>
                    <td>${patient.lastName}</td>
                    <td>${patient.dob}</td>
                    <td>${patient.gender}</td>
                    <td>${patient.phone}</td>
                    <td>
                        <a href="patientDetails?id=${patient.id}">View</a> |
                        <a href="editPatient?id=${patient.id}">Edit</a> |
                        <a href="deletePatient?id=${patient.id}" onclick="return confirm('Are you sure you want to delete this patient?');">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
