<%-- 
    Document   : Likes
    Created on : 19 Jul, 2020, 11:45:11 AM
    Author     : princ
--%>

<%@page import="com.dao.UserDao"%>
<%@page import="com.entities.User"%>
<%@page import="com.dao.LikesDao"%>
<%@page import="com.entities.Likes"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post | Likes</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%@include file="components/Navbar.jsp" %>
        <%
            int postId = Integer.parseInt(request.getParameter("id"));
            List<Likes> likesList = LikesDao.postLikedBy(postId);

        %>

        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <Div class="card-header">
                        Liked by
                    </Div>
                    <div class="card-body">
                        <ul class="list-group">
                            <%                                
                                if (!likesList.isEmpty()) {
                                    for (Likes like : likesList) {
                                        User user = UserDao.fetchUserById(like.getLikedUserId());
                            %>
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <img class="img-fluid" src="Uploads/user-images/<%= user.getDpName()%>" 
                                         style="width: 20px; height: 20px; border-radius: 50%">
                                    <a href="User.jsp?username=<%= user.getUsername()%>"><%= user.getName()%></a>
                                </li>
                            </ul>
                            <%
                                }
                            } else {%>
                            <p>No one has liked this post as of now</p>
                            <%}
                            %>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
