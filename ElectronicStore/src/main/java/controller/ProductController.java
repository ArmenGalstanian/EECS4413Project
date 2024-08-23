package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import model.Product;
import model.Brand;
import model.Category;
import model.Cart;

@WebServlet({ "/products"})
public class ProductController extends HttpServlet {
//
	private ProductDAO productDao;
	
	Cart cart = new Cart();
	public ProductController() {
		super();
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ServletContext context = getServletContext();
		productDao = new ProductDAOImpl();
		((ProductDAOImpl) productDao).setContext(context);
		// calling DAO method to retrieve category List from Database, for left column display
		List<Category> categoryList = productDao.findAllCategories();
		context.setAttribute("categoryList", categoryList);
		List<Brand> brandList = productDao.findAllBrands();
		context.setAttribute("brandList", brandList);
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String base = "/jsp/";
		String url = base + "home.jsp";
		String action = request.getParameter("action");
		String category = request.getParameter("category");
		String brand = request.getParameter("brand");
		String keyWord = request.getParameter("keyWord");
		String model = request.getParameter("model");
		//int id = Integer.parseInt(request.getParameter("id"));
		if (action != null) {
			switch (action) {
			case "allProducts":
				findAllProducts(request, response);
				url = base + "listOfProducts.jsp";
				break;
			case "category":
				findBooksByCategory(request, response, category);
				url = base + "category.jsp?category=" + category;
				break;
			case "brand":
				findProductsByBrand(request, response, brand);
				url = base + "brand.jsp?brand=" + brand;
				break;
			case "search":
				searchProducts(request, response, keyWord);
				url = base + "searchResult.jsp?model=" + model;
				break;

			case "viewSingle":
				findSingleProduct(request,response, model);
				url = base + "viewSingle.jsp?model=";
				break;
				
			case "addToCart":
				addToCart(request,response, model);
				url = base + "addToCart.jsp?model=";
				break;
			}
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	private void findAllProducts(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Product> productList = productDao.findAllProducts();
			request.setAttribute("productList", productList);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	//search books by keyword
	private void searchProducts(HttpServletRequest request,
			HttpServletResponse response, String keyWord)
			throws ServletException, IOException {
		try {
			// calling DAO method to search book by keyword 
			List<Product> productList = productDao.searchProductsByKeyword(keyWord);
			request.setAttribute("productList", productList);
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void findBooksByCategory(HttpServletRequest request,
			HttpServletResponse response, String cate)
			throws ServletException, IOException {
		try {
			// calling DAO method to search book by catetory 
			List<Product> productList = productDao.findProductsByCategory(cate);
			request.setAttribute("productList", productList);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void findProductsByBrand(HttpServletRequest request,
			HttpServletResponse response, String cate)
			throws ServletException, IOException {
		try {
			// calling DAO method to search book by catetory 
			List<Product> productList = productDao.findProductsByBrand(cate);
			request.setAttribute("productList", productList);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void findSingleProduct(HttpServletRequest request,
			HttpServletResponse response, String model)
			throws ServletException, IOException {
		try {
			// calling DAO method to search book by catetory 
			Product product = productDao.getSingleProduct(model);
			request.setAttribute("product", product);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	private void addToCart(HttpServletRequest request,
			HttpServletResponse response, String model)
			throws ServletException, IOException {
		try {
			// calling DAO method to search book by catetory 
			Product product = productDao.getSingleProduct(model);
			if (cart.contains(product.getId()) != null) {
				System.out.println("hey");
				product = cart.contains(product.getId());
				product.setQty(product.getQty()+1);
			}
			else {
				System.out.println("hi");
				product.setQty(1);
				cart.add(product);
			}
			request.setAttribute("product", product);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
