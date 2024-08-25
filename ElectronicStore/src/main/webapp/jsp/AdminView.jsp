<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/productStore.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script src="js/productstore.js"></script>
<style>
        .transparent-orange-button {
            background-color: rgba(255, 165, 0, 0.5);
            border: 2px solid rgba(255, 165, 0, 0.8);
            border-radius: 5px;
            padding: 10px 40px;
            cursor: pointer;
            color: white;
            font-weight: bold;
        }
        .transparent-orange-button:hover {
            background-color: rgba(255, 165, 0, 0.7);
        }
</style>
</head>
<body>

<form method="get" action="/ElectronicStore/AdminServlet">
<input type="hidden" name="databaseRequest" value ="get"/>
<input type="submit" class="transparent-orange-button" value="add to database" />
</form><br>

<form method="get" action="/ElectronicStore/jsp/Remove.jsp">
<input type="hidden" name="databaseRequest" value ="remove"/>
<input type="submit" class="transparent-orange-button" value="remove from database" />
</form><br>

<form method="get" action="/ElectronicStore/AdminServlet">
<input type="hidden" name="databaseRequest" value ="view"/>
<input type="submit" class="transparent-orange-button" value="View Products List" />
</form><br>

<p><a href='/ElectronicStore/'>Back to Home</a></p>

</body>
</html>