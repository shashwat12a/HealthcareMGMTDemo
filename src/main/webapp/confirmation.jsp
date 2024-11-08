<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Patient</title>
</head>
<body>
    <h1>Are you sure you want to delete this patient?</h1>
    <p>Once deleted, the patient data cannot be recovered.</p>
    <form action="deletePatient" method="post">
        <input type="hidden" name="id" value="${patient.id}">
        <button type="submit">Yes, Delete</button>
    </form>
    <br>
    <a href="patientList">Cancel</a>
</body>
</html>
