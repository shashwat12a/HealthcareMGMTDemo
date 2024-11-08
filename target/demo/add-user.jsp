<!DOCTYPE html>
<html>
<head>
    <title>Add User</title>
</head>
<body>
    <h2>Add User</h2>
    <form action="add-user" method="post">
        Username: <input type="text" name="username" required/><br>
        Password: <input type="password" name="password" required/><br>
        Role: 
        <select name="role">
            <option value="admin">Admin</option>
            <option value="user">User</option>
        </select><br>
        <input type="submit" value="Add User"/>
    </form>
    <p>${error}</p>
</body>
</html>
