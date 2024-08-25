<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout</title>
    <link rel="stylesheet" href="productStore.css">
    <style>
        .payment-icons img {
            width: 50px;
            margin: 5px;
        }
    </style>
</head>
<body>
    <div class="checkout-container">
        <div class="checkout-form">
            <h1>Checkout</h1>
            
            <!-- Checkout Form -->
            <form action="<%=request.getContextPath()%>/submitOrder" method="post" id="checkoutForm">
                <div class="section">
                    <h2>Shipping Information</h2>
                    <label for="fullName">Full Name</label>
                    <input type="text" id="fullName" name="fullName" required>

                    <label for="address">Address</label>
                    <input type="text" id="address" name="address" required>

                    <label for="city">City</label>
                    <input type="text" id="city" name="city" required>

                    <label for="state">State/Province</label>
                    <input type="text" id="state" name="state" required>

                    <label for="zip">Zip/Postal Code</label>
                    <input type="text" id="zip" name="zip" required>

                    <label for="country">Country</label>
                    <input type="text" id="country" name="country" required>
                </div>

                <div class="section">
                    <h2>Payment Information</h2>
                    <label for="cardName">Name on Card</label>
                    <input type="text" id="cardName" name="cardName" required>

                    <label for="cardNumber">Card Number</label>
                    <input type="text" id="cardNumber" name="cardNumber" required>

                    <label for="expiryDate">Expiry Date (MM/YY)</label>
                    <input type="text" id="expiryDate" name="expiryDate" required>

                    <label for="cvv">CVV</label>
                    <input type="text" id="cvv" name="cvv" required>

                    <div class="payment-icons">
                        <img src="${initParam.imageURL}/visa.png" alt="Visa">
                        <img src="${initParam.imageURL}/mastercard.png" alt="MasterCard">
                        <img src="${initParam.imageURL}/paypal.png" alt="PayPal">
                    </div>
                </div>

                <div class="section">
                    <button type="submit" class="btn" onClick="processOrder(event)">Confirm Order</button>
                </div>
            </form>
        </div>
        
		<div class = "cart-summary">
			<h2> Your Cart Summary </h2>
			<c:choose>
				
                <c:when test="${sessionScope.cart != null && !sessionScope.cart.isEmpty()}">
        <ul>
            <c:forEach var="product" items="${sessionScope.cart.products}">
                <li>${product.model} - ${product.qty} x ${product.price} = ${product.price * product.qty}</li>
            </c:forEach>
        </ul>
        <p><strong>Subtotal:</strong> ${sessionScope.cart.bill}</p>
        <p><strong>Tax:</strong> ${sessionScope.cart.calculateTax()}</p>
        <p><strong>Shipping: free</strong></p>
        <p><strong>Total:</strong> ${sessionScope.cart.calculateTotal()}</p>
    </c:when>
                <c:otherwise>
                    <p>Your cart is empty.</p>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="order-summary" style="display: none;" id="orderSummary">
            <h2>Thank You for Your Order!</h2>
            <div id="orderDetails">
            </div>
        </div>
		<div class="section">
			<h2>Shipping Information</h2>
			<p id="shippingMessage"></p>
		</div>
        <div class="payment-error" style="display: none;" id="paymentError">
            <h2>Payment Denied</h2>
            <p>Your payment has been denied after three attempts. Please try with a different payment method.</p>
            <button type="button" onclick="retryPayment()">Try Again</button>
            <button type="button" onclick="quit()">Quit</button>
        </div>
    </div>
    <script>
        let orderCount = 0;
        let paymentAttempts = 0;

        function validateEmail(email) {
            const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return re.test(email);
        }
     // Simple algo for payment authorization

        function validateExpiryDate(expiryDate){
            const re = /^(0[1-9]|1[0-2])\/([0-9]{2})$/;
            return re.test(expiryDate);
        }

        function validateCardNumber(cardNumber) {
            return /^[0-9]{16}$/.test(cardNumber);
        }

        function validateCVV(cvv) {
            return /^[0-9]{3}$/.test(cvv);
        }

        function processOrder(event) {
            event.preventDefault(); 

            const cardNumber = document.getElementById('cardNumber').value;
            const cvv = document.getElementById('cvv').value;
            const expiryDate = document.getElementById('expiryDate').value;
            const orderSummary = document.getElementById('orderSummary');
            const orderDetails = document.getElementById('orderDetails');
            const paymentError = document.getElementById('paymentError');

            if (!validateCardNumber(cardNumber)) {
                alert('Invalid Card Number. Please enter a 16-digit card number.');
                return;
            }

            if (!validateCVV(cvv)) {
                alert('Invalid CVV. Please enter a 3-digit CVV.');
                return;
            }

            if(!validateExpiryDate(expiryDate)){
                alert('Invalid Expiry Date. Please enter in MM/YY format.');
                return; 
            }

            
            paymentAttempts++;
            if (paymentAttempts >= 3) {
                document.getElementById('checkoutForm').style.display = 'none';
                paymentError.style.display = 'block';
                return;
            }

            orderCount++;
            if (orderCount % 3 === 0) {
                alert('Credit Card Authorization Failed.');
                return;
            }

           
            calculateShippingDate();
            
            document.getElementById('checkoutForm').style.display = 'none';
            orderSummary.style.display = 'block';
        }

        function retryPayment() {
            document.getElementById('paymentError').style.display = 'none';
            document.getElementById('checkoutForm').style.display = 'block';
        }
        
        function calculateShippingDate(){
        	const shippingMessage = document.getElementById('shippingMessage');
        	const today = new Date();
        	const shippingDays = Math.floor(Math.random() * 2) + 2;
        	const shippingDate = new Date(today);
        	shippingDate.setDate(today.getDate() + shippingDays);
        	
        	const options = {year :'numeric' , month:'long', day: 'numeric'};
        	const formattedDate = shippingDate.toLocaleDateString(undefined, options);
        	//system.out.println(formattedDate);
        	shippingMessage.innerHTML = "Estimate shipping date : " + formattedDate;
        }

        function quit() {
            window.location.href = '<%=request.getContextPath()%>/home';
        }
    </script>
</body>
</html>
