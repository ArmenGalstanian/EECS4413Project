package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDAO;
import dao.ProductDAOImpl;
import model.Cart;

/**
 * Servlet implementation class CartController
 */
@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Cart cart;
	private ProductDAO dao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartController() {
		super();
		dao = new ProductDAOImpl();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String forwardUrl = "/jsp/CartView.jsp";
		HttpSession session = request.getSession(true);

		synchronized (session) {  // synchronized to prevent concurrent updates
			// Retrieve the shopping cart for this session, if any. Otherwise, create one.
			cart = (Cart) session.getAttribute("cart");
			if (cart == null) {  // No cart, create one.
				cart = new Cart();
				session.setAttribute("cart", cart);  // Save it into session
			}
		}

		String req = request.getParameter("cartRequest");
		long id = 0L;
		if (request.getParameter("id") != null)
			id = Long.parseLong(request.getParameter("id"));

		if (req != null) {
			if (req.equals("update")) {
				int qty = Integer.parseInt(request.getParameter("qty"));
				cart.update(id, qty);
			}
			else if (req.equals("remove")) {
				cart.remove(id);
			}
			else if (req.equals("clear")) {
				cart.clear();
			}
			else if (req.equals("checkout")) {
				checkoutCart(request, response);
			}
		}

		request.setAttribute("cart", cart);
		request.getRequestDispatcher(forwardUrl).forward(request, response);
	}
	
	private void checkoutCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();			
			if(cart == null || cart.isEmpty()) {
				cart = new Cart();
				session.setAttribute("cart", cart);
			}
			
			request.setAttribute("cart", cart);
			//cart.clear();
			
			request.setAttribute("message",  "Thank you for your order!");
			
		}
		catch(Exception e) {
			System.out.println(e);
			request.setAttribute("message", "An error occured during checkout.Please try again.");
		}
			request.getRequestDispatcher("/jsp/checkout.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
