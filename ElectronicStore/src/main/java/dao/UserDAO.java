package dao;

import model.User;

public interface UserDAO {
    void saveUser(User user);
    User getUserByEmail(String email);
    void updateUser(User user);
    void deleteUser(String email);
}
