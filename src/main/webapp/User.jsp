<%-- 
    Document   : Profile
    Created on : 8 Jul, 2020, 6:26:22 PM
    Author     : princ
--%>

<%@page import="com.helpers.AlertMessage"%>
<%@page import="com.dao.FollowerFollowingDao"%>
<%@page import="com.dao.UserDao"%>
<%@page import="com.entities.User"%>
<%@page import="com.dao.PostDao"%>
<%@page import="com.entities.Post"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User | Profile</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%@include file="components/Navbar.jsp" %>
        <%
            User currentUser = (User) session.getAttribute("currentUser");
            String username = request.getParameter("username");
            User user = UserDao.fetchUserByUsername(username);
            try {
                if (user == null) {
                    int id = Integer.parseInt(request.getParameter("id"));
                    user = UserDao.fetchUserById(id);
                }
            } catch (Exception e) {
                AlertMessage alertMessage = new AlertMessage("css-danger", "Error", "Cannot find this user");
                session.setAttribute("alertMessage", alertMessage);
                response.sendRedirect("Error.jsp");
                return;
            }

            if (user.getUsername().equals(currentUser.getUsername())) {
                response.sendRedirect("Profile.jsp");
            }
            long followers = FollowerFollowingDao.getNoOfFollowers(user.getId());
            long followings = FollowerFollowingDao.getNoOfFollowing(user.getId());
        %>

        <div class="container p-4">
            <div class="row">
                <div class="col-md-4">
                    <img class="img-fluid" style="border-radius: 50%; height: 200px; width: 200px" src="Uploads/user-images/<%= user.getDpName()%>">
                </div>
                <div class="col-md-8">
                    <div class="row text-center">
                        <div class="col-md-4"> 
                            <p>Posts</p>
                            <p><%=  PostDao.getNoOfPosts(user.getId())%></p>
                        </div>
                        <a href="Followers.jsp?username=<%= user.getUsername()%>">
                            <div class="col-md-4"> 
                                <p>Followers</p>
                                <p><%= followers%></p>
                            </div></a>  
                        <a href="Followings.jsp?username=<%= user.getUsername()%>">
                            <div class="col-md-4">
                                <p>Following</p>
                                <p><%= followings%></p>
                            </div></a>  
                            <%
                                boolean isFollowed = FollowerFollowingDao.isFollowed(currentUser.getId(), user.getId());
                                if (isFollowed) {
                            %>
                        <a href="Unfollow?id=<%= user.getId()%>" class="col-md-12 text-center btn btn-secondary">Unfollow</a>
                        <%
                        } else {
                        %>
                        <a href="Follower?id=<%= user.getId()%>" class="col-md-12 text-center btn btn-primary">Follow</a>
                        <%}%>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="container-fluid text-center">
            <div class="row">

                <%
                    List<Post> list = PostDao.getUsersPost(user.getId());

                    if (list.size()
                            != 0) {
                        for (Post post : list) {
                %>

                <div class="col-md-4 mb-2 pb-2" >
                    <div class="card" style="max-height: 240px; overflow: hidden">
                        <a href="Post.jsp?id=<%= post.getPostid()%>">
                            <%
                                if (post.getImageName().equals("defaultpost")) {
                            %>
                            <div class="card-body p-3">
                                <p><%= post.getCaption()%></p>
                            </div>

                            <%} else {
                            %>
                            <img class="card-img-top" style="min-height: 240px; max-height: 240px" src="Uploads/user-images/<%= post.getImageName()%>">
                            <%}%>
                        </a>
                    </div>
                </div>
                <%                        }
                } else {
                %>
                <h3 class="display-4" >No posts yet</h3>
                <%    }
                %>
            </div>
        </div>
    </body>
</html>
