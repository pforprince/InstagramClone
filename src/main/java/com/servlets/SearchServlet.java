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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author princ
 */
public class SearchServlet extends HttpServlet {

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
            
            try {
                User currentUser = (User) request.getSession().getAttribute("currentUser");
                String username = request.getParameter("username");
                String operation = request.getParameter("operation");
//                 if(username==null){
//                     AlertMessage alertMessage= new AlertMessage("alert-warning", "Invalid Action", "Enter Fi")
//                 }

                List<User> list = UserDao.searchUser(username, currentUser.getId());
                
                if (operation.equals("search")) {
                    if (list.isEmpty()) {
                        AlertMessage alertMessage = new AlertMessage("alert-warning", "No result", "No user found for this username");
                        request.getSession().setAttribute("alertMessage", alertMessage);
                        response.sendRedirect("Search.jsp");
                    } else {
                        request.getSession().setAttribute("searchedResults", list);
                        response.sendRedirect("Search.jsp");
                    }
                } else {
                    if (list.isEmpty()) {
                        response.sendRedirect("Messages.jsp");
                    } else {
                        request.getSession().setAttribute("searchedResults", list);
                        response.sendRedirect("Messages.jsp");
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace();
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
