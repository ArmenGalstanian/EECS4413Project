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

	<form method='get' action='cart'>
		<table id='grid'>
		<thead>
			<tr>
<!-- 				<th>ID</th> -->
				<th style="width: 150px;">Name</th>
				<th id="th-title">Description</th>
				<th style="width: 60px;">Price</th>
				<th style="width: 100px;">Category</th>
				<th style="width: 100px;">Brand</th>
				<th style="width: 50px;">Quantity</th>
				<th id="th-View">Update Quantity</th>
				<th id="th-View">Remove</th>
			</tr>
		</thead>

			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

			<c:forEach items="${requestScope.cart.products}" var="item">
				<tr>
<%-- 					<td>${item.id}</td> --%>
					<td>${item.model}</td>
					<td>${item.description}</td>
					<td>${item.price}</td>
					<td>${item.categoryDescription}</td>
					<td>${item.brandDescription}</td>
					<td>${item.qty}</td>
					
					<td> <form method="get">
					<input type='hidden' size='3' name='cartRequest' value='update' >
					<input type='hidden' size='3' name='id' value='${item.id}'>
					<input type='text' size='3' name='qty' value='${item.qty}' />
					<input type='submit' name="cartRequest" value="Update" /></form></td>
					
					<td> <form method="get">
					<input type='hidden' size='3' name='cartRequest' value='remove' >
					<input type='hidden' size='3' name='id' value='${item.id}'>
					<input type='submit' name="cartRequest" value='Remove' /></form></td>
					
				</tr>
			</c:forEach>
			
			<tr>
				<td style="font-size: 20px; width: 150px"><b>Total Price: $${requestScope.cart.bill}</b></td>
			</tr>

		</table>
		<br />
		<form method="get">
		<input type='hidden' name='cartRequest'  value='clear'>
		<input type='submit' class="transparent-orange-button" value='CLEAR' />
		</form>
	</form>

	<p>
		<a href='/ElectronicStore/products'>Back to Select Menu</a>
	</p>
	
	<form method="get">
		<input type='hidden' name='cartRequest'  value='checkout'>
		<input type='submit' class="transparent-orange-button" value='Proceed to Checkout' />
		</form>

</body>
</html>