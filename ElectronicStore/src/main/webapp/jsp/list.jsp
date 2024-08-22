<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>





<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>

    <div>
        <c:if test="${param.category != null}">
            <span class="label" > List of ${param.category} Products</span>
        </c:if>
    </div>



    <table id="grid" >
        <thead>
            <tr>
                <th id="th-title">Product Name</th>
                <th id="th-author">Category</th>
                <th id="th-author1">Brand</th>
                <th id="th-View">See Details</th> <!-- New column for the button -->
            </tr>
        </thead>

        <tbody>
            <c:forEach var="product" items="${productList}">
                <tr>
                    <td>${product.description}</td>
                    <td>${product.categoryDescription}</td>
                    <td>${product.brandDescription}</td>
                    <td>
                        <form action="${initParam.param1}" method="POST" style="display:inline;">
    					<input type="hidden" name="action" value="viewSingle">
    					<input type="hidden" name="model" value="${product.description}">
    					<button type="submit" class="label" >View</button>
						</form>
        
                    </td>
                    
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
