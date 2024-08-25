<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Account Management</title>
    <link rel="stylesheet" href="bookstore.css">
    <style>
        .payment-icons img {
            width: 50px;
            margin: 5px;
        }
        .hidden {
            display: none;
        }
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <div class="account-container">
        <div class="account-form">
            <h1>Account Management</h1>
            <div id="account-section">
                <h2>Account Information</h2>
                <div id="login-section">
                    <form id="loginForm" action="<%=request.getContextPath()%>/userController" method="post">
                    	<input type = "hidden" name="action" value="login">
                        <label for="loginEmail">Email</label>
                        <input type="email" id="loginEmail" name="loginEmail" required>

                        <label for="loginPassword">Password</label>
                        <input type="password" id="loginPassword" name="loginPassword" required>

                        <button type="submit">Log In</button>
                    </form>
                    <p>or</p>
                    <button type="button" onclick="showCreateAccount()">Create New Account</button>
                </div>

                <div id="create-account-section" class="hidden">
                    <form id="createAccountForm" action="<%=request.getContextPath()%>/userController" method="post" onsubmit="return validatePasswords()">
                    	<input type="hidden" name="action" value="register">
                    	<label for="name">Name</label>
                        <input type="name" id="name" name="name" required>
                    	
                        <label for="newEmail">Email</label>
                        <input type="email" id="newEmail" name="newEmail" required>

                        <label for="newPassword">Password</label>
                        <input type="password" id="newPassword" name="newPassword" required minlength="8">

                        <label for="confirmPassword">Retype Password</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" required minlength="8">

                        <h3>Default Billing Address</h3>
                        <label for="billingAddress">Address</label>
                        <input type="text" id="billingAddress" name="billingAddress" required>

                        <label for="billingCountry">Country</label>
                        <select id="billingCountry" name="billingCountry" onchange="updateStates('billingCountry', 'billingState')" required>
                            <option value="">Select Country</option>
                            <option value="USA">United States</option>
                            <option value="CAN">Canada</option>
                            <!-- Add more countries here -->
                        </select>

                        <label for="billingCity">City</label>
                        <input type="text" id="billingCity" name="billingCity" required>

                        <label for="billingState">State/Province</label>
                        <select id="billingState" name="billingState" required>
                            <option value="">Select State/Province</option>
                            <!-- Add states/provinces here based on selected country -->
                        </select>

                        <label for="billingZip">Zip/Postal Code</label>
                        <input type="text" id="billingZip" name="billingZip" required>

                        <h3>Default Shipping Address</h3>
                        <label>
                            <input type="checkbox" id="sameAsBilling" onclick="copyBillingAddress()"> Same as Billing Address
                        </label>

                        <div id="shippingAddressSection">
                            <label for="shippingAddress">Address</label>
                            <input type="text" id="shippingAddress" name="shippingAddress">

                            <label for="shippingCity">City</label>
                            <input type="text" id="shippingCity" name="shippingCity">

                            <label for="shippingState">State/Province</label>
                            <select id="shippingState" name="shippingState">
                                <option value="">Select State/Province</option>
                               
                            </select>

                            <label for="shippingZip">Zip/Postal Code</label>
                            <input type="text" id="shippingZip" name="shippingZip">

                            <label for="shippingCountry">Country</label>
                            <select id="shippingCountry" name="shippingCountry" onchange="updateStates('shippingCountry', 'shippingState')">
                                <option value="">Select Country</option>
                                <option value="USA">United States</option>
                                <option value="CAN">Canada</option>
                                
                            </select>
                        </div>

                        <button type="submit">Create Account</button>
                        <p id="passwordError" class="error"></p>
                    </form>
                </div>
            </div>

            <div class="checkout-form hidden" id="checkoutForm">
                <h2>Checkout</h2>
                <form action="<%=request.getContextPath()%>/submitOrder" method="post">
                    <!-- Checkout form fields as previously defined -->
                </form>
            </div>

            <div class="order-summary hidden" id="orderSummary">
                <h2>Thank You for Your Order!</h2>
                <p>Your estimated shipping time is 2-3 days after the order was placed.</p>
                <div id="orderDetails">
                    <!-- Order details will be dynamically added here -->
                </div>
            </div>

            <div class="payment-error hidden" id="paymentError">
                <h2>Payment Denied</h2>
                <p>Your payment has been denied after three attempts. Please try with a different payment method.</p>
                <button type="button" onclick="retryPayment()">Try Again</button>
                <button type="button" onclick="quit()">Quit</button>
            </div>
        </div>
    </div>
    <script>
    function validatePasswords() {
        const password = document.getElementById('newPassword').value;
        const confirmPassword = document.getElementById('confirmPassword').value;
        const passwordError = document.getElementById('passwordError');

        if (password !== confirmPassword) {
            passwordError.textContent = 'Passwords do not match!';
            return false; 
        } else {
            passwordError.textContent = ''; 
            return true; 
        }
    }
</script>
    

    <script>
        function showCreateAccount() {
            document.getElementById('login-section').style.display = 'none';
            document.getElementById('create-account-section').style.display = 'block';
        }

        function copyBillingAddress() {
            const sameAsBilling = document.getElementById('sameAsBilling').checked;
            if (sameAsBilling) {
                document.getElementById('shippingAddress').value = document.getElementById('billingAddress').value;
                document.getElementById('shippingCity').value = document.getElementById('billingCity').value;
                document.getElementById('shippingState').value = document.getElementById('billingState').value;
                document.getElementById('shippingZip').value = document.getElementById('billingZip').value;
                document.getElementById('shippingCountry').value = document.getElementById('billingCountry').value;
                document.getElementById('shippingAddressSection').querySelectorAll('input').forEach(input => input.disabled = true);
                document.getElementById('shippingState').disabled = true;
                document.getElementById('shippingCountry').disabled = true;
            } else {
                document.getElementById('shippingAddressSection').querySelectorAll('input').forEach(input => input.disabled = false);
                document.getElementById('shippingState').disabled = false;
                document.getElementById('shippingCountry').disabled = false;
                document.getElementById('shippingAddress').value = '';
                document.getElementById('shippingCity').value = '';
                document.getElementById('shippingState').value = '';
                document.getElementById('shippingZip').value = '';
                document.getElementById('shippingCountry').value = '';
            }
        }

        function updateStates(countryId, stateId) {
            const country = document.getElementById(countryId).value;
            const stateSelect = document.getElementById(stateId);
            stateSelect.innerHTML = '<option value="">Select State/Province</option>'; // Reset states

            let states = [];

            if (country === 'USA') {
                states = [
                    'Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut',
                    'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa',
                    'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan',
                    'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire',
                    'New Jersey', 'New Mexico', 'New York', 'North Carolina', 'North Dakota', 'Ohio',
                    'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota',
                    'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia',
                    'Wisconsin', 'Wyoming'
                ];
            } else if (country === 'CAN') {
                states = [
                    'Alberta', 'British Columbia', 'Manitoba', 'New Brunswick', 'Newfoundland and Labrador',
                    'Northwest Territories', 'Nova Scotia', 'Nunavut', 'Ontario', 'Prince Edward Island',
                    'Quebec', 'Saskatchewan', 'Yukon'
                ];
            }

            states.forEach(state => {
                const option = document.createElement('option');
                option.value = state;
                option.textContent = state;
                stateSelect.appendChild(option);
            });
        }

        function retryPayment() {
            document.getElementById('paymentError').style.display = 'none';
            document.getElementById('checkoutForm').style.display = 'block';
        }

        function quit() {
            window.location.href = '<%=request.getContextPath()%>/home';
        }
    </script>
</body>
</html>
