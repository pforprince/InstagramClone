/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlets;

import com.dao.UserDao;
import com.entities.User;
import com.helpers.AlertMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author princ
 */
@MultipartConfig
public class EditProfileServlet extends HttpServlet {

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
                String name = request.getParameter("name");
                String username = request.getParameter("username");

                User returnedUser = UserDao.isUsernameAvailable(username);
                // if the returned user is different from current user 
                if (returnedUser != null && returnedUser.getId() != currentUser.getId()) {
                    AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!", "This username is already booked!");
                    request.getSession().setAttribute("alertMessage", alertMessage);
                    response.sendRedirect("EditProfile.jsp");
                } else if (returnedUser == null || returnedUser.getId() == currentUser.getId()) {
                    // returned user is null and we can take the username 

                    Part fileVal = request.getPart("image");
                    String password = request.getParameter("password");
                    String fileName = request.getPart("image").getSubmittedFileName();
                    if (fileName.length() > 0) {
                        // means the user has updated his profile photo
                        String path = request.getRealPath("Uploads") + File.separator + "user-images" + File.separator + fileName;
                        Part part = request.getPart("image");
                        try {
                            OutputStream outputStream = new FileOutputStream(path);
                            InputStream inputStream = part.getInputStream();
                            byte data[] = new byte[inputStream.available()];
                            inputStream.read(data);
                            outputStream.write(data);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        User user = new User(currentUser.getId(), name, currentUser.getEmail(), password, username, fileName);
                        User updatedUser = UserDao.updateUser(user);
                        if (updatedUser != null) {
                            request.getSession().setAttribute("currentUser", updatedUser);
                            response.sendRedirect("Profile.jsp");
                        } else {
                            out.println("error in last stage of update");
                        }
                    } else {

                        // the user has not updated his profile photo
                        User user = new User(currentUser.getId(), name, currentUser.getEmail(), currentUser.getPassword(), username, currentUser.getDpName());
                        User updatedUser = UserDao.updateUser(user);
                        if (updatedUser != null) {
                            request.getSession().setAttribute("currentUser", updatedUser);
                            response.sendRedirect("Profile.jsp");
                        } else {
                            out.println("error in last stage of update");
                        }
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
