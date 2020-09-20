<%-- 
    Document   : Followings
    Created on : 12 Jul, 2020, 12:31:36 PM
    Author     : princ
--%>

<%@page import="com.dao.UserDao"%>
<%@page import="com.entities.Followers"%>
<%@page import="com.dao.FollowerFollowingDao"%>
<%@page import="java.util.List"%>
<%@page import="com.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User | Followings</title>
        <%@include  file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%@include file="components/Navbar.jsp" %>
        <%
            String username = request.getParameter("username");
            User user = UserDao.fetchUserByUsername(username);
            List<Followers> followings = FollowerFollowingDao.getFollowingsById(user.getId());
        %>

        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <Div class="card-header">
                        Followings
                    </Div>
                    <div class="card-body">
                        <ul class="list-group">
                            <%
                                if (followings.size() > 0) {
                                    for (Followers followingID : followings) {
                                        User following = UserDao.fetchUserById(followingID.getFollowingId());
                            %>
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <img class="img-fluid" src="Uploads/user-images/<%= following.getDpName()%>" 
                                         style="width: 20px; height: 20px; border-radius: 50%">
                                    
                                    
                                    <a href="User.jsp?username=<%= following.getUsername()%>"><%= following.getName()%></a>
                                </li>
                            </ul>
                            <%
                                }
                            } else {%>
                            <p>You're not following anyone</p>
                            <%}
                            %>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
