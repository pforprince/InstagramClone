


<%@page import="com.dao.LikesDao"%>
<%@page import="com.dao.CommentDao"%>
<%@page import="com.dao.FollowerFollowingDao"%>
<%@page import="com.entities.Followers"%>
<%@page import="com.dao.UserDao"%>
<%@page import="com.dao.PostDao"%>
<%@page import="java.util.List"%>
<%@page import="com.entities.Post"%>
<%@page import="com.entities.User"%>

<%@include file="components/HideDetails.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>


        <%@include file="components/Navbar.jsp" %>
        <%            User currentUser = (User) session.getAttribute("currentUser");
        %>

        <div class="container p-5 mx-auto">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <%
                        List<Followers> followingList = FollowerFollowingDao.getFollowingsById(currentUser.getId());
                        List<Post> followingsPost = PostDao.getPostsForHomeByID(followingList, currentUser.getId());
                        if (followingsPost != null && followingsPost.size() > 0) {
                            for (Post post : followingsPost) {
                                User postUser = UserDao.fetchUserById(post.getUserId());

                    %>
                    <div class="card my-4">
                        <div class="card-header">
                            <a style="margin-right: 4px" href="User.jsp?username=<%= postUser.getUsername()%>"> <img style="height: 20px; width: 20px; border-radius: 50%"                                                                                 src="Uploads/user-images/<%= postUser.getDpName()%>" >
                            </a> 
                            <a href="User.jsp?username=<%= postUser.getUsername()%>"<strong><%= postUser.getName()%></strong>
                        </div>
                        </a>

                        <%
                            if (!post.getImageName().equals("defaultpost")) {
                        %>
                        <img class="img-fluid" style="max-height: 500px" src="Uploads/user-images/<%= post.getImageName()%>">
                        <div class="ml-3 mt-2 pb-2"> 
                            <%                                boolean isLiked = LikesDao.isPostLikedByMe(currentUser.getId(), post.getPostid());
                                String liked = "fa-heart";
                                String unliked = "fa-heart-o";

                            %>
                            <i onclick="operation(<%= post.getPostid()%>)" id="likeunlike<%= post.getPostid()%>" class="fa <%= (isLiked) ? liked : unliked%>"> </i>


                            <p id="totalLikes" style="margin-bottom: 0px">Liked by<a href="Likes.jsp?id=<%= post.getPostid()%>">

                                    <span id="likesCount<%= post.getPostid()%>"> <%= LikesDao.likesOfPost(post.getPostid())%> </span> others</a></p>

                            <p>   <a href="User.jsp?username=<%= postUser.getUsername()%>" style="font-weight: 500; margin-right: 2px"> 
                                    <%= postUser.getUsername()%> </a> <%= post.getCaption()%></p> 

                            <hr>
                            <!--                            <%= post.getCaption()%>
                                                        <p style="color: blue; 
                                                           margin-right: 10px;"> <%= post.getTags()%></p>-->

                            <%
                                int comments = CommentDao.noOfCommentsByPostId(post.getPostid());
                                if (comments == 1) {
                            %>
                            <a href="Post.jsp?id=<%= post.getPostid()%>"><%= comments%> Comment</a>
                            <%
                            } else if (comments > 1) {
                            %>
                            <a href="Post.jsp?id=<%= post.getPostid()%>"><%= comments%> Comments</a>
                            <%
                                }
                            %>

                        </div>
                        <%} else {
                        %>
                        <div class="ml-3 mt-2 pb-2"> 
                            <%                                boolean isLiked = LikesDao.isPostLikedByMe(currentUser.getId(), post.getPostid());
                                String liked = "fa-heart";
                                String unliked = "fa-heart-o";

                            %>

                            <p>   <a href="User.jsp?username=<%= postUser.getUsername()%>" style="font-weight: 500; margin-right: 2px"> 
                                    <%= postUser.getUsername()%> </a> <%= post.getCaption()%></p> 
                            <p style="color: blue; 
                               margin-right: 10px;"> <%= post.getTags()%>
                            </p>
                            <hr>
                                <i onclick="operation(<%= post.getPostid()%>)" id="likeunlike<%= post.getPostid()%>" class="fa <%= (isLiked) ? liked : unliked%>"> </i>
                            <p id="totalLikes" style="margin-bottom: 0px">Liked by<a href="Likes.jsp?id=<%= post.getPostid()%>">

                                    <span id="likesCount<%= post.getPostid()%>"> <%= LikesDao.likesOfPost(post.getPostid())%> </span> others</a></p>

                            <!--                            <%= post.getCaption()%>
                                                        <p style="color: blue; 
                                                           margin-right: 10px;"> <%= post.getTags()%></p>-->

                            <%
                                int comments = CommentDao.noOfCommentsByPostId(post.getPostid());
                                if (comments == 1) {
                            %>
                            <a href="Post.jsp?id=<%= post.getPostid()%>"><%= comments%> Comment</a>
                            <%
                            } else if (comments > 1) {
                            %>
                            <a href="Post.jsp?id=<%= post.getPostid()%>"><%= comments%> Comments</a>
                            <%
                                }
                            %>

                        </div>
                        <%
                            }

                        %>


                    </div>

                    <%                        }
                    } else {

                    %>
                    <div class="container">
                        <div class="row">
                            <div class="col-md-12 text-center">
                                <P class="h4">No posts to show</P>
                            </div>
                        </div>
                    </div>
                    <%}
                    %>
                </div>
            </div>
        </div>
    </body>
</html>
