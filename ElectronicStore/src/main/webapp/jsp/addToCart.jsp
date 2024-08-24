<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<script src="js/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="css/productStore.css" type="text/css" />
<script src="js/productstore.js"></script>

<style>
    /* Centered container */
    .product-details {
        display: flex;
        flex-direction: column;
        align-items: center;
        margin: 20px 0;
    }

    /* Flexbox for description */
    .product-description {
        text-align: center;
        margin-bottom: 20px;
        width: 100%;
    }

    /* Flexbox for name and price */
    .product-info {
        display: flex;
        justify-content: space-between;
        width: 100%;
        margin-bottom: 10px;
    }

    .product-name {
        text-align: left;
        padding-left: 50px;
    }

    .product-price {
        text-align: right;
		padding-right: 50px;
        
    }

    /* Flexbox for category and brand */
    .product-meta {
        display: flex;
        justify-content: space-between;
        width: 100%;
        margin-bottom: 20px;
    }

    .product-category {
        text-align: left;
        padding-left: 50px;
    }

    .product-brand {
        text-align: right;
        padding-right: 50px;
    }

    /* Centered Add to Cart button */
    .add-to-cart {
        margin-top: 30px;
        text-align: center;
        width: 100%;
    }

    .add-to-cart button {
        padding: 10px 20px;
        font-size: 16px;
    }
</style>

</head>
<body>
    <div id="centered">
        <jsp:include page="header.jsp" flush="true" />
        <br />
        <jsp:include page="leftColumn.jsp" flush="true" />

        <!-- Start of the product details section -->
        <div class="product-details">
            <!-- Description -->
            <div class="product-description">
                <p>Description: ${product.description}</p>
            </div>
			
            <!-- Name and Price -->
            <div class="product-info">
                <div class="product-name">
                    <p>Product Name: ${product.model}</p>
                </div>
                <div class="product-price">
                    <p>Price: ${product.price}</p>
                </div>
            </div>

            <!-- Category and Brand -->
            <div class="product-meta">
                <div class="product-category">
                    <p>Category: ${product.categoryDescription}</p>
                </div>
                <div class="product-brand">
                    <p>Brand: ${product.brandDescription}</p>
                </div>
            </div>

            <!-- Add to Cart Button -->
            <div class="add-to-cart">
            	<form action="${initParam.param1}" method="POST" style="display:inline;">
            	<input type="hidden" name="action" value="addToCart">
            	<input type="hidden" name="model" value="${product.model}">
                <button type="submit">Add to Cart</button>
                </form>
            </div>
          <p>You have added ${product.model} } to your cart and now have ${product.qty} in your cart!</p>
         
        </div>
        
        <!-- End of the product details section -->
    </div>
</body>
</html>
