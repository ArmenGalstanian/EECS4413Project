package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import model.Order;
import model.User;

public  class UserDAOImpl implements UserDAO {
	
	private ServletContext context;
	
	public UserDAOImpl(ServletContext context) {
		this.context  = context;
	}

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        // Path to the database file
        String path = context.getRealPath("/users.db");
        return DriverManager.getConnection("jdbc:sqlite:" + path);
    }

    private void closeConnection(Connection connection) {
        if (connection == null) return;
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // create the  tables in case it is not present in the database 
    public void initializeDatabase() {
    	String createUserTablesql = "CREATE TABLE IF NOT EXISTS users ("+ 
    				 "email TEXT PRIMARY KEY," + 
    				 "password TEXT NOT NULL,"+
    				 "name TEXT NOT NULL,"+
    				 "address TEXT NOT NULL, "+
    				 "creditCard TEXT NOT NULL)";
    	
    	String createOrdersTablesql = "CREATE TABLE IF NOT EXISTS orders(" +
    		    "order_id INTEGER PRIMARY KEY AUTOINCREMENT," +
    		    "user_email TEXT NOT NULL," +
    		    "product_id INTEGER NOT NULL," +
    		    "quantity INTEGER NOT NULL," +
    		    "order_date DATETIME DEFAULT CURRENT_TIMESTAMP," +
    		    "FOREIGN KEY(user_email) REFERENCES users(email))";

    	try(Connection connection = getConnection();
    			PreparedStatement createUserTablestatement = connection.prepareStatement(createUserTablesql);
    			PreparedStatement createOrdersTablestatement = connection.prepareStatement(createOrdersTablesql)){
    				
    			
    		
    		createUserTablestatement.execute();
    		createOrdersTablestatement.execute();
    	}
    	catch(SQLException ex) {
    		ex.printStackTrace();
    	}
    }
    
    // Saving user information for tracking their order history
    @Override
    public void saveUser(User user) {
        String sql = "INSERT INTO users (email, password, name, address, creditCard) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getCreditCard());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
// checking if the user is already registered using the same email 
    
    @Override
    public User getUserByEmail(String email) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                	 String userEmail = resultSet.getString("email");
                     String password = resultSet.getString("password");
                     String name = resultSet.getString("name");
                     String address = resultSet.getString("address");
                     String creditCard = resultSet.getString("creditCard");

                     user = new User(userEmail, password, name, address, creditCard);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }

    //updating the user information
    @Override
    public void updateUser(User user) {
        String sql = "UPDATE users SET password = ?, name = ?, address = ?, creditCard = ? WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getName());
            statement.setString(3, user.getAddress());
            statement.setString(4, user.getCreditCard());
            statement.setString(5, user.getEmail());
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    //deleting user 
    @Override
    public void deleteUser(String email) {
        String sql = "DELETE FROM users WHERE email = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
    
    //used to track and get the order history of the client
    @Override
    public List<Order> getOrderHistory(String email) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE user_email = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int orderId = resultSet.getInt("order_id");
                    int productId = resultSet.getInt("product_id");
                    int quantity = resultSet.getInt("quantity");
                    String orderDate = resultSet.getString("order_date");

                    Order order = new Order(orderId, email, productId, quantity, orderDate);
                    orders.add(order);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orders;
    }

  //saving order to track the order history
	@Override
	public void saveORder(Order order) {
		String sql = "INSERT INTO orders (user_email, product_id, quantity) VALUES (?,?,?)";
    	try(Connection connection = getConnection();
    			PreparedStatement statement = connection.prepareStatement(sql)){
    				statement.setString(1,  order.getUserEmail());
    				statement.setInt(2,  order.getProductId());
    				statement.setInt(3, order.getQuantity());
    				statement.executeUpdate();
    			}
    			catch(SQLException ex) {
    				ex.printStackTrace();
    			}
		
	}

}

