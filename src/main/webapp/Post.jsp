<%-- 
    Document   : Post
    Created on : 10 Jul, 2020, 1:21:54 AM
    Author     : princ
--%>

<%@page import="com.helpers.AlertMessage"%>
<%@page import="com.dao.LikesDao"%>
<%@page import="com.entities.Comment"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.CommentDao"%>
<%@page import="com.dao.UserDao"%>
<%@page import="com.entities.User"%>
<%@page import="com.dao.PostDao"%>
<%@page import="com.entities.Post"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%@include file="components/Navbar.jsp" %>

        <%  User currentUser = (User) session.getAttribute("currentUser");
            int postId = Integer.parseInt(request.getParameter("id"));
            Post post = PostDao.getPostById(postId);
            if (post == null) {
                AlertMessage alertMessage = new AlertMessage("css-danger", "Error", "Cannot find this post");
                session.setAttribute("alertMessage", alertMessage);
                response.sendRedirect("Error.jsp");
                return;
            }
            User user = UserDao.fetchUserById(post.getUserId());
            session.setAttribute("currentPost", post);
        %>
        <diV class="container p-5">
            <div class="row">
                <div class=" col-12 col-md-6 offset-md-3">
                    <div class="card">
                        <div class="card-header" style="background-color: ">
                            <a style="margin-right: 4px" href="User.jsp?username=<%= user.getUsername()%>">
                                <img style="height: 20px; width: 20px; border-radius: 50%"                                 
                                     src="Uploads/user-images/<%= user.getDpName()%>" >
                            </a> 
                            <a href="User.jsp?username=<%= user.getUsername()%>"<strong><%= user.getName()%></strong>
                                <%
                                    if (post.getUserId() == currentUser.getId()) {
                                %>
                                <div class="ml-auto float-right">
                                    <a href="DeletePost?q=<%= post.getPostid()%>">  <i class="fa fa-trash float-right" aria-hidden="true"></i> </a>
                                    &nbsp;
                                    <a href="Update.jsp"> <i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                                </div>
                                <%
                                    }%>

                        </div>

                        <!----------------------------this is end of the above part---------------------------------------->
                        <%
                            boolean isLiked = LikesDao.isPostLikedByMe(currentUser.getId(), post.getPostid());
                            String liked = "fa-heart";
                            String unliked = "fa-heart-o";

                        %>

                        <%  if (!post.getImageName().equals("defaultpost")) {
                                // this is a post with image
%>
                        <img class="img-fluid" style="max-height: 500px; width: 100%" src="Uploads/user-images/<%= post.getImageName()%>">
                        <div class="card-body">

                            <i onclick="operation(<%= post.getPostid()%>)" id="likeunlike<%= post.getPostid()%>" class="fa <%= (isLiked) ? liked : unliked%>"> </i>

                            <p id="totalLikes" style="margin-bottom: 0px">Liked by<a href="Likes.jsp?id=<%= post.getPostid()%>">

                                    <span id="likesCount<%= post.getPostid()%>"> <%= LikesDao.likesOfPost(post.getPostid())%> </span> others</a></p>

                            <p style="font-size:16px;"><a href="User.jsp?username=<%= user.getUsername()%>" style="font-weight: 600"><%= user.getName()%> &nbsp;</a><%= post.getCaption()%></p>
                            <p  style="color: blue"><%= post.getTags()%></p>
                            <hr>
                            <%} else {%>

                            <!--this is a post with only text-->
                            <div class="card-body">
                                <div >
                                    <p>   <a href="User.jsp?username=<%= user.getUsername()%>" style="font-weight: 600"><%= user.getName()%> </a>
                                        <%= post.getCaption()%></p>
                                    <hr>
                                    <p  style="color: blue"><%= post.getTags()%></p>

                                    <i onclick="operation(<%= post.getPostid()%>)" id="likeunlike<%= post.getPostid()%>" class="fa <%= (isLiked) ? liked : unliked%>"> </i>

                                    <p id="totalLikes" style="margin-bottom: 0px">Liked by<a href="Likes.jsp?id=<%= post.getPostid()%>">

                                            <span id="likesCount<%= post.getPostid()%>"> <%= LikesDao.likesOfPost(post.getPostid())%> </span> others</a></p>
                                </div>

                                <%
                                    }
                                %>
                                <!------------------------------------------------------this is the below part------------------------------------------>


                                <div>
                                    <div class="m-2 text-center">
                                        <form action="Comment" method="post">
                                            <input type="hidden" name="postId" value="<%= post.getPostid()%>">
                                            <input type="text" name="comment" class="form-control"
                                                   placeholder="Post Comment here">

                                            <button style="border: none; margin-top: 4px" type="submit">
                                                <i class="fa fa-paper-plane-o"  aria-hidden="true"></i>
                                            </button>
                                        </form>
                                    </div>

                                    <%
                                        List<Comment> list = CommentDao.getAllCommentsByPostId(post.getPostid());
                                        if (!list.isEmpty()) {
                                    %>
                                    <div>
                                        <p>Comments</p>
                                        <%
                                            if (list != null) {
                                                for (Comment comment : list) {
                                                    User commentedUser = UserDao.fetchUserById(comment.getUserId());
                                                    String pcid = String.valueOf(post.getPostid()) + "x" + String.valueOf(comment.getId());
                                        %>

                                        <ul class="list-group">
                                            <li class="list-group-item">
                                                <img class="img-fluid" style="height: 30px; width: 30px; border-radius: 50%" src="Uploads/user-images/<%= commentedUser.getDpName()%>">
                                                <a href="User.jsp?username="><b><%= commentedUser.getUsername()%></b></a>
                                                        <%= comment.getComment()%>
                                                        <%
                                                            if (currentUser.getId() == comment.getUserId()) {
                                                        %>
                                                <a href="Comment?pcid=<%= pcid%>">  <i class="fa fa-trash float-right" aria-hidden="true"></i> </a>
                                                <%}
                                                %>
                                            </li>
                                        </ul>   
                                        <%
                                                }
                                            }
                                        %>
                                    </div>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                        </div>
                    </div>  
                </div>
            </div>
    </body>
</html>
