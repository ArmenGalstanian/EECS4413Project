package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import model.Brand;
import model.Category;
import model.Product;

/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO dao;
	private List<Product> items;
	private List<Category> categories;
	private List<Brand> brands;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = new ProductDAOImpl();
		((ProductDAOImpl) dao).setContext(getServletContext());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String req = request.getParameter("databaseRequest");

		if (req.equals("view")) {
			items = dao.findAllProducts();
			System.out.println(items.get(1).getModel());
			
			request.setAttribute("items", items);
			request.getRequestDispatcher("/jsp/View.jsp").forward(request, response);
		}
		else if (req.equals("add")) {
			long id = Long.parseLong(request.getParameter("id"));
			String name = request.getParameter("name");
			double price = Float.parseFloat(request.getParameter("price"));
			String category = request.getParameter("catname");
			String brand = request.getParameter("brandname");
			String description = request.getParameter("description");
			
			Product prod = new Product();
			prod.setId(id);
			prod.setModel(name);
			price = Math.round(price * 100.0) / 100.0;
			prod.setPrice(price);
			prod.setDescription(description);
			prod.setBrandDescription(brand);
			prod.setCategoryDescription(category);
			prod.setBrandID( ((ProductDAOImpl) dao).getBrandID(brand) );
			prod.setCategoryID( ((ProductDAOImpl) dao).getCategoryID(category) );
			
			dao.insert(prod);
			items = dao.findAllProducts();
			
			request.setAttribute("items", items);
			request.getRequestDispatcher("/jsp/View.jsp").forward(request, response);
			
		}
		else if (req.equals("remove")) {
			dao.delete(Long.parseLong(request.getParameter("id")));
			
			request.setAttribute("categories", getCategories());
			request.setAttribute("brands", getBrands());
			request.setAttribute("items", dao.findAllProducts());
			request.getRequestDispatcher("/jsp/View.jsp").forward(request, response);
		}
		else if (req.equals("get")) {
			
			request.setAttribute("categories", getCategories());
			request.setAttribute("brands", getBrands());
			request.setAttribute("items", dao.findAllProducts());
			request.getRequestDispatcher("/jsp/Add.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private List<Category> getCategories() {
		if (categories == null)
			return dao.findAllCategories();
		return categories;
	}
	
	private List<Brand> getBrands() {
		if (brands == null)
			return dao.findAllBrands();
		return brands;
	}

}
