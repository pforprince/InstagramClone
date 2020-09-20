/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlets;

import com.dao.UserDao;
import com.entities.User;
import com.helpers.AlertMessage;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author princ
 */
public class SignUpServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            try {
                User usernameUser = UserDao.isUsernameAvailable(username);
                if (usernameUser != null) {
                    // username is not available
                    AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!", "This username is already booked!");
                    request.getSession().setAttribute("alertMessage", alertMessage);
                    response.sendRedirect("Signup.jsp");
                } else {
                    // username is available
                    User user = new User(name, email, password, username, "default.png");
                    User returnedUser = UserDao.saveUser(user);
                    if (returnedUser != null) {
                        request.getSession().setAttribute("currentUser", user);
                        response.sendRedirect("Profile.jsp");
                    } else {
                        AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!", "Something went Wrong! Kindly try again!");
                        request.getSession().setAttribute("alertMessage", alertMessage);
                        response.sendRedirect("Signup.jsp");
                    }
                }
            } catch (ConstraintViolationException e) {
                AlertMessage alertMessage = new AlertMessage("alert-warning", "Either the email or username is duplicate!!",
                        "Try again by changing the same");
                request.getSession().setAttribute("alertMessage", alertMessage);
                response.sendRedirect("Signup.jsp");
            } catch (Exception e) {
                AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!", "Something went Wrong! Kindly try again!");
                request.getSession().setAttribute("alertMessage", alertMessage);
                response.sendRedirect("Signup.jsp");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
