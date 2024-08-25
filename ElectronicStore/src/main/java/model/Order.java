package model;

public class Order {
    private int orderId;
    private String userEmail;
    private int productId;
    private int quantity;
    private String orderDate;

    public Order(int orderId, String userEmail, int productId, int quantity, String orderDate) {
        this.orderId = orderId;
        this.userEmail = userEmail;
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = orderDate;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getOrderDate() {
        return orderDate;
    }
}
