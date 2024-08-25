<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link rel="stylesheet" href="productStore.css">
    <style>
        .profile-container {
            max-width: 800px;
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
        .profile-form input, .profile-form select, .profile-form textarea {
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
        .profile-form .error {
            color: red;
        }
        .order-history table {
            width: 100%;
            border-collapse: collapse;
        }
        .order-history th, .order-history td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .order-history th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <div class="profile-container">
        <h1>User Profile</h1>
        <form action="<%=request.getContextPath()%>/userController?action=updateProfile" method="post" class="profile-form">
            <label for="name">Name</label>
            <input type="text" id="name" name="name" value="<c:out value='${name}'/>" required>

            <label for="email">Email</label>
            <input type="email" id="email" name="email" value="<c:out value='${email}'/>" required>

            <fieldset>
                <legend>Address</legend>
                <label for="street">Street Address</label>
                <input type="text" id="street" name="street" value="<c:out value='${street}'/>" required>

                <label for="city">City</label>
                <input type="text" id="city" name="city" value="<c:out value='${city}'/>" required>

                <label for="country">Country</label>
                <input type="text" id="country" name="country" value="<c:out value='${country}'/>" required>

                <label for="postalCode">Postal Code</label>
                <input type="text" id="postalCode" name="postalCode" value="<c:out value='${postalCode}'/>" required>
            </fieldset>

            <label for="creditCard">Credit Card</label>
            <input type="text" id="creditCard" name="creditCard" value="<c:out value='${creditCard}'/>" required>

            <button type="submit">Update Profile</button>
        </form>

        <form action="<%=request.getContextPath()%>/userController?action=logout" method="post" class="profile-form">
            <button type="submit">Logout</button>
        </form>


		<form action="<%=request.getContextPath()%>/products" method="post" class="profile-form">
            <button type="submit">Go to Store</button>
        </form>
        
        <h2>Order History</h2>
        <div class="order-history">
            <table>
                <thead>
                    <tr>
                        <th>Order ID</th>
                        <th>Date</th>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Total Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orderHistory}">
                        <tr>
                            <td><c:out value="${order.orderId}"/></td>
                            <td><c:out value="${order.orderDate}"/></td>
                            <td><c:out value="${order.productId}"/></td>
                            <td><c:out value="${order.quantity}"/></td>
                            <td><c:out value="${order.totalPrice}"/></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
