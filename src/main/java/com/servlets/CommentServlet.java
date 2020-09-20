package com.servlets;

import com.dao.CommentDao;
import com.dao.NotificationDao;
import com.dao.PostDao;
import com.entities.Comment;
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

public class CommentServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                User currentUser = (User) request.getSession().getAttribute("currentUser");
                String stringPostId = request.getParameter("postId");

                if (stringPostId != null) {
                    int postId = Integer.parseInt(stringPostId);
                    Post postedImage = PostDao.getPostById(postId);
                    String commentText = request.getParameter("comment");
                    Comment comment = new Comment(postId, currentUser.getId(), commentText, new Date());
                    Comment postedComment = CommentDao.saveComment(comment);
                    if (currentUser.getId() != postedImage.getUserId()) {
                        Notification notification = new Notification(postedImage.getUserId(), currentUser.getId(),
                                currentUser.getName() + " commented on your post", new Date(), postId);
                        boolean noticationSaved = NotificationDao.saveNotification(notification);
                        response.sendRedirect("Post.jsp?id=" + postId);
                    } else {
                        response.sendRedirect("Post.jsp?id=" + postId);
                    }
                } else {
                    String pcid = request.getParameter("pcid");
                    int postId = Integer.parseInt(pcid.split("x")[0]);
                    int commentId = Integer.parseInt(pcid.split("x")[1]);
                    boolean isDeleted = CommentDao.deleteCommentById(commentId);
                    if (isDeleted) {
                        response.sendRedirect("Post.jsp?id=" + postId);
                        out.println("Error");
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
