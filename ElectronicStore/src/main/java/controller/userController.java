package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

@WebServlet("/userController")
public class userController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "register":
                handleRegistration(request, response);
                break;
            case "login":
                handleLogin(request, response);
                break;
            case "updateProfile":
                handleUpdateProfile(request, response);
                break;
            default:
                response.sendRedirect("registration.jsp"); 
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "profile":
                handleProfile(request, response);
                break;
            case "logout":
                handleLogout(request, response);
                break;
            default:
                response.sendRedirect("registration.jsp"); 
        }
    }

  // handling new user registration
    private void handleRegistration(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("newEmail");
        String password = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String creditCard = request.getParameter("creditCard");

        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Passwords do not match");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        // Update user to the database 
        UserDAO userDAO = new UserDAOImpl(getServletContext());
        User user = new User(email, password, name, address, creditCard);
        userDAO.saveUser(user);

        response.sendRedirect("registration.jsp");
    }

   
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("loginEmail");
        String password = request.getParameter("loginPassword");

        // Validate if user exist in the database 
        UserDAO userDAO = new UserDAOImpl(getServletContext());
        User user = userDAO.getUserByEmail(email);

        

        if ( user != null && user.getPassword().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            response.sendRedirect("profile.jsp");
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
        }
    }

    
    private void handleUpdateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("registration.jsp");
            return;
        }

        String newName = request.getParameter("name");
        String newAddress = request.getParameter("address");
        String newCreditCard = request.getParameter("creditCard");

        // Update user details in the database if new user
        UserDAO userDAO = new UserDAOImpl(getServletContext());
        user.setName(newName);
        user.setAddress(newAddress);
        user.setCreditCard(newCreditCard);
        userDAO.updateUser(user);

        
        session.setAttribute("user", user);

        response.sendRedirect("profile.jsp");
    }


   // displaying the users profile 
    private void handleProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("registration.jsp");
            return;
        }

        request.setAttribute("name", user.getName());
        request.setAttribute("address", user.getAddress());
        request.setAttribute("creditCard", user.getCreditCard());

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }


   // logic for handling the logout service for the user 
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.jsp"); 
    }
}
