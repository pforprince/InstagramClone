/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlets;

import com.dao.PostDao;
import com.entities.Post;
import com.entities.User;
import com.helpers.AlertMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
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
public class UploadServlet extends HttpServlet {

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

            User user = (User) request.getSession().getAttribute("currentUser");

            String caption = request.getParameter("caption");
            String tags = request.getParameter("tags");
            Part part = request.getPart("image");
            String imageName = request.getPart("image").getSubmittedFileName();
            if (imageName.length() == 0) {
                try {
                    Post post = new Post(caption, tags, "defaultpost", user.getId(), new Date());
                    Post returnedPost = PostDao.savePost(post);
                    if (returnedPost != null) {
                        AlertMessage alertMessage = new AlertMessage("alert-success", "Successfull!! ", "Your post has been uploaded successfully");
                        request.getSession().setAttribute("alertMessage", alertMessage);
                        response.sendRedirect("Upload.jsp");
                    } else {
                        AlertMessage alertMessage = new AlertMessage("alert-warning", "Error!! ", "Something went wrong");
                        request.getSession().setAttribute("alertMessage", alertMessage);
                        response.sendRedirect("Upload.jsp");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Post post = new Post(caption, tags, imageName, user.getId(), new Date());

                    Post returnedPost = PostDao.savePost(post);
                    if (returnedPost != null) {
                        try {
                            String path = request.getRealPath("Uploads") + File.separator + "user-images" + File.separator + part.getSubmittedFileName();
                            FileOutputStream fileOutputStream = new FileOutputStream(path);
                            InputStream inputStream = part.getInputStream();
                            byte data[] = new byte[inputStream.available()];
                            inputStream.read(data);
                            fileOutputStream.write(data);
                            fileOutputStream.close();
                            AlertMessage alertMessage = new AlertMessage("alert-success", "Successfull!! ", "Your post has been uploaded successfully");
                            request.getSession().setAttribute("alertMessage", alertMessage);

                            response.sendRedirect("Upload.jsp");
                        } catch (Exception e) {
                            e.printStackTrace();
                            out.print("error caught in saving image");
                        }

                    } else {
                        out.println("error");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
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
