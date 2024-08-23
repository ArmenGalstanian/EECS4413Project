package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import model.Category;
import model.Product;
import model.Brand;

public class ProductDAOImpl implements ProductDAO {

	private ServletContext context;
	
	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	static {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}

	// complete this method
	private Connection getConnection() throws SQLException {
		String path = context.getRealPath("/products.db");
		return DriverManager.getConnection("jdbc:sqlite:" + path);
	}

	private void closeConnection(Connection connection) {
		if (connection == null)
			return;
		try {
			connection.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	//complete this method
	public List<Product> findAllProducts() {
		List<Product> result = new ArrayList<Product>();
		
        // join 3 tables to get needed info
		String sql = "select * from product inner join category, brand on product.categoryID = category.id and product.brandID = brand.id";

		System.out.println(sql);

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next() ) {
				
				Product product = new Product();
				
				// populate product with info
				product.setDescription(resultSet.getString("model"));
				product.setId(resultSet.getLong("id"));
				product.setCategoryID(resultSet.getLong("categoryID"));
				product.setCategoryDescription(resultSet.getString("cName"));
				product.setBrandID(resultSet.getLong("brandID"));
				product.setBrandDescription(resultSet.getString("bName"));
				product.setPrice(resultSet.getDouble("price"));
				
				
				result.add(product);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	// complete this method
	@Override
	public List<Product> searchProductsByKeyword(String keyWord) {
		List<Product> result = new ArrayList<Product>();
		
		String sql = "select * from product "
				+ "inner join category on product.categoryID = category.id "
				+ "inner join brand on product.brandID = brand.id "
				+ "where product.model like '%"
				+ keyWord.trim()
				+ "%'"
				+ " or category.cName like '%"
				+ keyWord.trim()
				+ "%'"
				+ " or brand.bName like '%" + keyWord.trim() + "%'";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();

				// populate product with info
				product.setDescription(resultSet.getString("model"));
				product.setId(resultSet.getLong("id"));
				product.setCategoryID(resultSet.getLong("categoryID"));
				product.setCategoryDescription(resultSet.getString("cName"));
				product.setBrandID(resultSet.getLong("brandID"));
				product.setBrandDescription(resultSet.getString("bName"));
				product.setPrice(resultSet.getDouble("price"));
				
				
				result.add(product);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}

		return result;
	}
	@Override
	public Product getSingleProduct(String model) {
		Product product = new Product();
		String sql = "select * from product "
				+ "inner join category on product.categoryID = category.id "
				+ "inner join brand on product.brandID = brand.id "
				+ "where product.model='" + model + "'";
		System.out.println(sql);
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				// populate product with info
				product.setDescription(resultSet.getString("description"));
				product.setModel(resultSet.getString("model"));
				product.setId(resultSet.getLong("id"));
				product.setCategoryID(resultSet.getLong("categoryID"));
				product.setCategoryDescription(resultSet.getString("cName"));
				product.setBrandID(resultSet.getLong("brandID"));
				product.setBrandDescription(resultSet.getString("bName"));
				product.setPrice(resultSet.getDouble("price"));
				
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return product;
	}
	// complete this method
	public List<Category> findAllCategories() {
		List<Category> result = new ArrayList<>();
		String sql = "select * from category";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Category category = new Category();
				
				// populate category bean with needed info
				category.setCategoryDescription(resultSet.getString("cName"));
				category.setId(resultSet.getLong("id"));
				
				result.add(category);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}

	public List<Brand> findAllBrands() {
		List<Brand> result = new ArrayList<>();
		String sql = "select * from brand";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Brand brand = new Brand();
				
				// populate category bean with needed info
				brand.setBrandDescription(resultSet.getString("bName"));
				brand.setId(resultSet.getLong("id"));

				result.add(brand);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	
	public List<Product> findProductsByCategory(String category) {
		List<Product> result = new ArrayList<Product>();
		 

		String sql = "select * from product "
				+ "inner join category on product.categoryID = category.id "
				+ "inner join brand on product.brandID = brand.id "
				+ "where category.cName='" + category + "'";
		
		System.out.println(sql);

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();

				// populate product with info
				product.setDescription(resultSet.getString("model"));
				product.setId(resultSet.getLong("id"));
				product.setCategoryID(resultSet.getLong("categoryID"));
				product.setCategoryDescription(resultSet.getString("cName"));
				product.setBrandID(resultSet.getLong("brandID"));
				product.setBrandDescription(resultSet.getString("bName"));
				product.setPrice(resultSet.getDouble("price"));
				
				result.add(product);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
	
	public List<Product> findProductsByBrand(String brand) {
		List<Product> result = new ArrayList<Product>();
		 

		String sql = "select * from product "
				+ "inner join category on product.categoryID = category.id "
				+ "inner join brand on product.brandID = brand.id "
				+ "where brand.bName='" + brand + "'";

		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Product product = new Product();

				// populate product with info
				product.setDescription(resultSet.getString("model"));
				product.setId(resultSet.getLong("id"));
				product.setCategoryID(resultSet.getLong("categoryID"));
				product.setCategoryDescription(resultSet.getString("cName"));
				product.setBrandID(resultSet.getLong("brandID"));
				product.setBrandDescription(resultSet.getString("bName"));
				product.setPrice(resultSet.getDouble("price"));
				
				result.add(product);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
		return result;
	}
		
	public Product findProductByClick() {
		Product product = new Product();
		
		return product;
	};

	
	
	public void insert(Product product) {
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement statement = connection.prepareStatement(
					"insert into Book (book_title) values (?)",
					Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, product.getDescription());
			statement.execute();
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				product.setId(generatedKeys.getLong(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}

	
	public void delete(Long productID) {
		Connection connection = null;

		try {
			connection = getConnection();
			PreparedStatement statement = connection
					.prepareStatement("delete from product where id=?");
			statement.setLong(1, productID);
			statement.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			closeConnection(connection);
		}
	}
	

}