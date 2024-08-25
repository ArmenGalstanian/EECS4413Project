<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form id="databaseRequest" action="/ElectronicStore/AdminServlet">
	<label for="id">ID:</label>
	<input type="text" name="id" required><br>
	
<input type="hidden" name="databaseRequest" value ="remove"/>
	<input type="submit" value="remove">
</form>

<p><a href='/ElectronicStore/jsp/AdminView.jsp'>Back to Admin</a></p><br>

</body>
</html>