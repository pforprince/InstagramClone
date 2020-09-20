<%-- 
    Document   : Profile
    Created on : 8 Jul, 2020, 6:26:22 PM
    Author     : princ
--%>
<%@page import="com.helpers.AlertMessage"%>
<%@include file="components/HideDetails.jsp" %>

<%@page import="com.dao.FollowerFollowingDao"%>
<%@page import="com.entities.User"%>
<%@page import="com.dao.PostDao"%>
<%@page import="com.entities.Post"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%@include file="components/Navbar.jsp" %>
        <%            User currentUser = (User) session.getAttribute("currentUser");
            long followers = FollowerFollowingDao.getNoOfFollowers(currentUser.getId());
            long following = FollowerFollowingDao.getNoOfFollowing(currentUser.getId());
        %>

        <%
            AlertMessage alertMessage = (AlertMessage) session.getAttribute("alertMessage");
            if (alertMessage != null) {

        %>

        <div  class="alert <%= alertMessage.getCssClass()%> alert-dismissible fade show container"  role="alert">
            <strong><%= alertMessage.getMessage()%></strong> <%= alertMessage.getDescription()%>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%
                session.removeAttribute("alertMessage");
            }
        %>

        <div class="container p-4">
            <div class="row p-4">
                <div class="col-md-3 ml-auto" >
                    <img class="img-fluid" style="border-radius: 50%; height: 200px; width: 200px" 
                         src="Uploads/user-images/<%= currentUser.getDpName()%>">
                </div>
                <div class="col-md-8 text-center">
                    <div class="row">
                        <p class="text-left display-4"><%= currentUser.getUsername()%></p>
                        <div  class="col-md-2 mt-4 text-center">
                            <a href="Settings.jsp"> 
                                <div class="text-center" 
                                     style="background-color: #e2e2e2">Setting</div>
                            </a>
                        </div>
                    </div>

                    <!---------------------------------------------------------------------------------------------------->
                    <div class="row">
                        <a> <div class="col-md-4 text-center" style="max-width: none"> 
                                <p>Posts</p>
                                <p><%= PostDao.getNoOfPosts(currentUser.getId())%></p>
                            </div></a>
                        <a href="Followers.jsp?username=<%= currentUser.getUsername()%>" >
                            <div class="col-md-4 text-center" style="max-width: none"> 
                                <p>Followers</p> 
                                <p><%= followers%></p>
                            </div></a>

                        <a href="Followings.jsp?username=<%= currentUser.getUsername()%>"> 
                            <div class="col-md-4 text-center" style="max-width: none">
                                <p>Following</p>
                                <p><%= following%></p>
                            </div>
                        </a>
                        <div class="col-md-6">
                        </div>
                        <div class="col-md-6">
                            <a href="EditProfile.jsp"> 
                                <div class="text-center" 
                                     style="background-color: #e2e2e2">Edit Profile</div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <hr>
        <div class="container p-4">
            <div class="row">

                <%Post selectedPost = null;
                    List<Post> list = PostDao.getUsersPost(currentUser.getId());
                    if (list.size() != 0) {
                        for (Post post : list) {
                            selectedPost = post;
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
                <div class="container">
                    <div class="text-center">
                        <P class="display-4">No posts yet</P>
                    </div></div>
                    <%    }
                    %>
            </div>
        </div>



    </body>
</html>
