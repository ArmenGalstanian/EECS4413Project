<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="bookstore.css">
    <style>
        .profile-container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 8px;
        }
        .profile-container h1 {
            text-align: center;
        }
        .profile-form label {
            display: block;
            margin: 10px 0 5px;
        }
        .profile-form input, .profile-form select {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .profile-form button {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }
        .profile-form button:hover {
            background-color: #45a049;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <h1>User Profile</h1>
        <form action="<%=request.getContextPath()%>/userController?action=updateProfile" method="post" class="profile-form">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" value="<c:out value='${name}'/>" required>

            <label for="address">Address</label>
            <input type="text" id="address" name="address" value="<c:out value='${address}'/>" required>

            <label for="creditCard">Credit Card</label>
            <input type="text" id="creditCard" name="creditCard" value="<c:out value='${creditCard}'/>" required>

            <button type="submit">Update Profile</button>
        </form>

        <form action="<%=request.getContextPath()%>/userController?action=logout" method="post" class="profile-form">
            <button type="submit">Logout</button>
        </form>
    </div>
</body>
</html>
