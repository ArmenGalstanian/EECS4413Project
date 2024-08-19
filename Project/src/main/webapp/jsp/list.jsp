<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <script type="text/javascript">
        function handleButtonClick(productId) {
            // Your JavaScript code here
            alert("product ID: " + productId + " added to cart");
            // Add your function logic here
        }
    </script>
</head>
<body>

    <div>
        <c:if test="${param.category != null}">
            <span class="label" style="margin-left: 15px;"> List of ${param.category} Products</span>
        </c:if>
    </div>

    <table id="grid">
        <thead>
            <tr>
                <th id="th-title">Product Name</th>
                <th id="th-author">Category</th>
                <th id="th-author1">Brand</th>
                <th id="th-price">Price</th>
                <th id="th-Add">Add</th> <!-- New column for the button -->
            </tr>
        </thead>

        <tbody>
            <c:forEach var="product" items="${bookList}">
                <tr>
                    <td>${product.description}</td>
                    <td>${product.categoryDescription}</td>
                    <td>${product.brandDescription}</td>
                    <td>${product.price}</td>
                    <td>
                        <button type="button" onclick="handleButtonClick('${product.id}')">Add</button>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
