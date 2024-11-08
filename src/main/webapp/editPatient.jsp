<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Edit Patient</title>
</head>
<body>
    <h1>Edit Patient</h1>
    <form action="editPatient" method="post">
        <input type="hidden" name="id" value="${patient.id}">

        <label for="firstName">First Name:</label><br>
        <input type="text" id="firstName" name="firstName" value="${patient.firstName}" required><br><br>

        <label for="lastName">Last Name:</label><br>
        <input type="text" id="lastName" name="lastName" value="${patient.lastName}" required><br><br>

        <label for="dob">Date of Birth:</label><br>
        <input type="date" id="dob" name="dob" value="${patient.dob}" required><br><br>

        <label for="gender">Gender:</label><br>
        <input type="text" id="gender" name="gender" value="${patient.gender}" required><br><br>

        <label for="phone">Phone:</label><br>
        <input type="text" id="phone" name="phone" value="${patient.phone}" required><br><br>

        <button type="submit">Update Patient</button>
    </form>
    <br>
    <a href="patientList">Back to Patient List</a>
</body>
</html>
