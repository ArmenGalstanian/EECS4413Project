package dao;

import java.util.List;

import model.Order;
import model.User;

public interface UserDAO {
    void saveUser(User user);
    User getUserByEmail(String email);
    void updateUser(User user);
    void deleteUser(String email);
    
    // related for the order history of the clients
    void saveORder (Order order);
    List<Order> getOrderHistory(String email);
}
