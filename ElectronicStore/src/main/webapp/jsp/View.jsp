<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<link rel="stylesheet" href="css/productStore.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script src="js/productstore.js"></script>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<table id='grid'>
<tr>
<th>ID</th>
<th>Name</th>
<th>Price</th>
<th>Category</th>
<th>Brand</th>
<th>Description</th>
</tr>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <c:forEach items="${requestScope.items}" var="item">
 	<tr>
		<td>${item.id}</td>
		<td>${item.model}</td>
		<td>${item.price}</td>
 		<td>${item.categoryDescription}</td>
 		<td>${item.brandDescription}</td>
 		<td>${item.description}</td>
	</tr>
</c:forEach>
 
</table><br/>

<p><a href='/ElectronicStore/jsp/AdminView.jsp'>Back to Admin</a></p><br>

<form method="get" action="/ElectronicStore/AdminServlet">
<input type="hidden" name="databaseRequest" value ="get"/>
<input type="submit" value="add to database" />
</form><br>

</body>
</html>