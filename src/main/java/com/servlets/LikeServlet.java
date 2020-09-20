/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlets;

import com.dao.LikesDao;
import com.dao.NotificationDao;
import com.dao.PostDao;
import com.entities.Likes;
import com.entities.Notification;
import com.entities.Post;
import com.entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author princ
 */
public class LikeServlet extends HttpServlet {

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
                int postId = Integer.parseInt(request.getParameter("postId"));

                String operation = request.getParameter("operation");
                if (operation.equals("Like")) {
                    Likes likes = new Likes(currentUser.getId(), new Date(), postId);
                    boolean liked = LikesDao.postLiked(likes);
                    if (liked) {
                        Post mainUserPost = PostDao.getPostById(postId);
                        if (currentUser.getId() != mainUserPost.getUserId()) {
                            Notification notification = new Notification(mainUserPost.getUserId(), currentUser.getId(), currentUser.getName() + " Liked your post", new Date(), postId);
                            boolean noticationSaved = NotificationDao.saveNotification(notification);
                            if (noticationSaved) {
                                long likesCount = LikesDao.likesOfPost(postId);
                                out.println("like;" + likesCount);
                            }
                        } else {
                            long likesCount = LikesDao.likesOfPost(postId);
                            out.println("like;" + likesCount);
                        }
                    }

                } else {
                    boolean unLiked = LikesDao.postUnliked(currentUser.getId(), postId);
                    if (unLiked) {
                        long likesCount = LikesDao.likesOfPost(postId);
                        out.println("unlike;" + likesCount);
                    } else {
                        out.println("in else part of unlike servlet");
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
