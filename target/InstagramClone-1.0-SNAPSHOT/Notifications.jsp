<%-- 
    Document   : Notifications
    Created on : 8 Jul, 2020, 6:26:44 PM
    Author     : princ
--%>

<%@page import="com.dao.PostDao"%>
<%@page import="com.entities.Post"%>
<%@page import="com.dao.UserDao"%>
<%@page import="com.dao.NotificationDao"%>
<%@page import="java.util.List"%>
<%@page import="com.entities.User"%>
<%@page import="com.entities.Notification"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notifications</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%@include  file="components/HideDetails.jsp" %>

        <%@include file="components/Navbar.jsp" %>
        <%            User currentUser = (User) session.getAttribute("currentUser");
            List<Notification> notifications = NotificationDao.getAllNotifications(currentUser.getId());
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <div class="card-header">
                        Notifications
                    </div>
                    <div class="card-body">
                        <%
                            if (notifications.size() > 0) {
                                for (Notification notification : notifications) {
                                    User InteractedUser = UserDao.fetchUserById(notification.getInteractedUserId());

                        %>
                        <ul class="list-group">

                            <a href="User.jsp?username=<%= InteractedUser.getUsername()%>"> 
                                <li class="list-group-item">
                                    <img src="Uploads/user-images/<%= InteractedUser.getDpName()%>"
                                         style="width: 20px; height: 20px; border-radius: 50%"
                                         > &nbsp;
                                    <b><%= notification.getContent()%></b>
                            </a> 
                            <%
                                if (notification.getPostId() != -1) {
                                    Post post = PostDao.getPostById(notification.getPostId());
                            %>
                            <a href="Post.jsp?id=<%= post.getPostid()%>">  
                                <% if (post.getImageName().equals("defaultpost")) {
                                %>
                                <img class="float-right" src="Uploads/user-images/textComment.jpg"
                                     style="width: 20px; height: 20px;"></a>
                                <%                                    } else {
                                %>
                            <img class="float-right" src="Uploads/user-images/<%= post.getImageName()%>"
                                 style="width: 20px; height: 20px;"></a>
                            <%
                                    }
                                }
                            %>
                            </li> 
                        </ul> 
                        <%                                        }
                        } else {
                        %>
                        <p>No notifications!!</p>
                        <%                                        }%>

                    </div>
                </div>
            </div>
    </body>
</html>
