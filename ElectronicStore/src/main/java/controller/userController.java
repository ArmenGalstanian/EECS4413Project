package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/userController")
public class UserControllerServlet extends HttpServlet {
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
                response.sendRedirect("error.jsp"); 
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
                response.sendRedirect("error.jsp"); 
        }
    }

  
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

        

        response.sendRedirect("registration.jsp"); 
    }

   
    private void handleLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("loginEmail");
        String password = request.getParameter("loginPassword");

        

        boolean isValidUser = true; 

        if (isValidUser) {
            HttpSession session = request.getSession();
            session.setAttribute("user", email);
            response.sendRedirect("profile.jsp"); 
        } else {
            request.setAttribute("error", "Invalid email or password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    
    private void handleUpdateProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("user");

        String newName = request.getParameter("name");
        String newAddress = request.getParameter("address");
        String newCreditCard = request.getParameter("creditCard");

        
        session.setAttribute("userName", newName);

        response.sendRedirect("profile.jsp"); 
    }

   
    private void handleProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("user");

       
        String userName = "User Name"; 
        String userAddress = "User Address"; 
        String userCreditCard = "User Credit Card"; 

        request.setAttribute("name", userName);
        request.setAttribute("address", userAddress);
        request.setAttribute("creditCard", userCreditCard);

        request.getRequestDispatcher("profile.jsp").forward(request, response);
    }

   
    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.jsp"); 
    }
}
