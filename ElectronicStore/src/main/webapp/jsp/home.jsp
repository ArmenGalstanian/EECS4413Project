<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

 

<!DOCTYPE html >

 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="stylesheet" href="css/bookstore.css" type="text/css" />
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script src="js/productstore.js"></script>
</head>
<body>
	<div id="centered">

		<jsp:include page="header.jsp" flush="true" />
		<br />
		<jsp:include page="leftColumn.jsp" flush="true" />
		<span class="label" style="margin-left: 15px;">Featured Products</span>
		<table>
			<tr>
				<td><span class="tooltip_img1"><img
						src="${initParam.imageURL}/Apple Watch Ultra 2.jpg" /></span></td>
				<td><img src="${initParam.imageURL}/Apple Watch SE(2nd Gen).jpg" /></td>
				<td><img src="${initParam.imageURL}/Apple Watch Series 5.jpg" /></td>
				<td><img src="${initParam.imageURL}/Apple Watch Series 6.jpg" /></td>
				<td><img src="${initParam.imageURL}/Apple Watch Series 7.jpg" /></td>
			</tr>
			<tr>
				<td><img src="${initParam.imageURL}/Apple Watch Series 8.jpg" /></td>
				<td><img src="${initParam.imageURL}/AppleWatchSeries9.jpg" /></td>
				<td><img src="${initParam.imageURL}/Fire 7.jpg" /></td>
				<td><img src="${initParam.imageURL}/Fire 7 Kids.jpg" /></td>
				<td><img src="${initParam.imageURL}/Fire HD 10.jpg" /></td>
			</tr>
			<tr>
				<td><img src="${initParam.imageURL}/Fire HD 10 Kids.jpg" /></td>
				<td><img src="${initParam.imageURL}/iPad(9th Generation).jpg" /></td>
				<td><img src="${initParam.imageURL}/iPad(10th Generation).jpg" /></td>
				<td><img src="${initParam.imageURL}/iPad Mini.jpg" /></td>
				<td><img src="${initParam.imageURL}/iPad Pro.jpg" /></td>
			</tr>
		</table>
	</div>
</body>
</html>