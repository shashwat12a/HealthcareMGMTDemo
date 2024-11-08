<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patient Details</title>
</head>
<body>
    <h1>Patient Details</h1>
    <p><strong>First Name:</strong> ${patient.firstName}</p>
    <p><strong>Last Name:</strong> ${patient.lastName}</p>
    <p><strong>Date of Birth:</strong> ${patient.dob}</p>
    <p><strong>Gender:</strong> ${patient.gender}</p>
    <p><strong>Phone:</strong> ${patient.phone}</p>
    <br>
    <a href="patientList">Back to Patient List</a> | 
    <a href="editPatient?id=${patient.id}">Edit</a>
</body>
</html>
