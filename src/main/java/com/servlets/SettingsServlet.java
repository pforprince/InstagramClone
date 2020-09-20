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
import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author princ
 */
public class SettingsServlet extends HttpServlet {

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
            HttpSession session = request.getSession();
            User currentUser = (User) session.getAttribute("currentUser");
            String operation = request.getParameter("operation");
            if (operation.equals("password")) {

                String oldPassword = request.getParameter("oldpassword");
                String newPassword = request.getParameter("newpassword");
                String confirmPassword = request.getParameter("confirmpassword");

                if (newPassword.equals(confirmPassword)) {
                    if (oldPassword.equals(currentUser.getPassword())) {
                        if (oldPassword.equals(newPassword)) {
                            AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!!", "New password can't be same as old password");
                            session.setAttribute("alertMessage", alertMessage);
                            response.sendRedirect("Settings.jsp");
                        } else {
                            User returnedUserAfterSaving = null;
                            User updateUser = new User(currentUser.getId(), currentUser.getName(), currentUser.getEmail(), newPassword, currentUser.getUsername(), currentUser.getDpName());
                            try {
                                returnedUserAfterSaving = UserDao.updateUser(updateUser);
                            } catch (Exception e) {
                                AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!!", "Something went wrong, try again");
                                session.setAttribute("alertMessage", alertMessage);
                                response.sendRedirect("Settings.jsp");
                            }

                            if (returnedUserAfterSaving != null) {
                                session.setAttribute("currentUser", returnedUserAfterSaving);
                                response.sendRedirect("Profile.jsp");
                            } else {
                                AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!!", "Something went wrong, try again");
                                session.setAttribute("alertMessage", alertMessage);
                                response.sendRedirect("Settings.jsp");

                            }
                        }
                    } else {
                        AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!!", "You've entered wrong password");
                        session.setAttribute("alertMessage", alertMessage);
                        response.sendRedirect("Settings.jsp");

                    }
                } else {
                    AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!!", "Your new passwords are not matching");
                    session.setAttribute("alertMessage", alertMessage);
                    response.sendRedirect("Settings.jsp");

                }
            } else {
                String email = request.getParameter("email");
                User updatedUser = new User(currentUser.getId(), currentUser.getName(), email, currentUser.getPassword(), currentUser.getUsername(), currentUser.getDpName());
                User returnedSavedUser = null;
                try {
                    returnedSavedUser = UserDao.updateUser(updatedUser);
                    if (returnedSavedUser == null) {
                        AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!!", "Something went wrong, try again");
                        request.getSession().setAttribute("alertMessage", alertMessage);
                        response.sendRedirect("Settings.jsp");
                    } else {
                        session.setAttribute("currentUser", returnedSavedUser);
                        response.sendRedirect("Profile.jsp");
                    }

                } catch (PersistenceException e) {
                    AlertMessage alertMessage = new AlertMessage("alert-warning", "This email is already registerted!!",
                            "Try again by changing the same");
                    request.getSession().setAttribute("alertMessage", alertMessage);
                    response.sendRedirect("Settings.jsp");
                } catch (Exception e) {
                    AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!!", "Something went wrong, try again");
                    request.getSession().setAttribute("alertMessage", alertMessage);
                    response.sendRedirect("Settings.jsp");
                }

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
