<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form id="databaseRequest" action="/ElectronicStore/AdminServlet">
	<label for="id">ID:</label>
	<input type="text" name="id" required><br>
	
	<label for="name">Name:</label>
	<input type="text" name="name" required><br>
	
	<label for="price">Price:</label>
	<input type="text" name="price" required><br>
	
	<label for=description>Description:</label>
	<input type="text" name="description" required><br>
	
<!-- 	<label for="cat">Category Name:</label>
	<input type="text" name="catname" required><br> -->
	<select name="catname" required>
 		<c:forEach items="${requestScope.categories}" var="item">
 			<option>${item.categoryDescription}</option>
		</c:forEach>
	</select>
	
<!-- 	<label for="brand">Brand Name:</label>
	<input type="text" name="brandname" required><br> -->
	<select name="brandname" required>
 		<c:forEach items="${requestScope.brands}" var="item">
 			<option>${item.brandDescription}</option>
		</c:forEach>
	</select>
	
<input type="hidden" name="databaseRequest" value ="add"/>
	<input type="submit" value="add">
</form>

<p><a href='/ElectronicStore/jsp/AdminView.jsp'>Back to Admin</a></p><br>

</body>
</html>