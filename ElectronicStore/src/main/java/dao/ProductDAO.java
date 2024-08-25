package dao;

import java.util.List;

import model.Brand;
import model.Category;
import model.Product;

public interface ProductDAO {
	//getters
	public List<Product> findAllProducts();
	public List<Product> searchProductsByKeyword(String keyWord);
	public List<Product> findProductsByCategory(String category);
	public List<Product> findProductsByBrand(String brand);
	public List<Category> findAllCategories();
	public List<Brand> findAllBrands();
	public Product getSingleProduct(String model);

	//setters
	public void insert(Product product);
	public void delete(Long bookId);
	

		
}
