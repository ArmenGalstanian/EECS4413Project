package dao;

import java.util.List;

import model.Brand;
import model.Category;
import model.Product;

public interface ProductDAO {
	public List<Product> findAllProducts();
	
	public List<Product> searchProductsByKeyword(String keyWord);
	
	public List<Category> findAllCategories();
	public List<Brand> findAllBrands();


	public void insert(Product product);
	
	public void delete(Long bookId);
	
	public List<Product> findProductsByCategory(String category);
	public List<Product> findProductsByBrand(String brand);
	
	public Product findProductByClick();
	
	public Product getSingleProduct(int id);
}
