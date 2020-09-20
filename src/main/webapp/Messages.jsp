<%-- 
    Document   : Messages
    Created on : 8 Jul, 2020, 6:27:00 PM
    Author     : princ
--%>

<%@page import="com.dao.UserDao"%>
<%@page import="com.dao.MessageDao"%>
<%@page import="com.entities.Message"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Messages</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%@include  file="components/HideDetails.jsp" %>

        <%@include file="components/Navbar.jsp" %>

        <%            User currentUser = (User) session.getAttribute("currentUser");

        %>

        <div class="container ">
            <h2>Messages</h2>

            <div class="container" style="max-width: 400px">
                <form action="Search" method="POST">
                    <div class="form-group">
                        <input type="hidden" name="operation" value="chat">

                        <label for="exampleInputEmail1">Enter Username</label>
                        <input type="text" required class="form-control" name="username" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Username">
                        <small id="emailHelp" class="form-text text-muted">Press Enter to Search</small>
                    </div>
                    <button class="btn btn-outline-secondary">Search</button>
                </form>
                <hr>
                <div class="container" style="max-width: 400px">
                    <%  List<User> list = (List<User>) (session.getAttribute("searchedResults"));
                        if (list == null) {
                    %>
                    <p class="text-center">Search someone to say a Hey</p>
                    <%
                    } else {
                        for (User user : list) {
                    %>
                    <ul class="list-group">
                        <li class="list-group-item">
                            <img style="border-radius: 50%; height: 20px; width: 20px " 
                                 class="img-fluid" src="Uploads/user-images/<%= user.getDpName()%>"> 
                            &nbsp;
                            <a href="Chats.jsp?id=<%= user.getId()%>"> 
                                <%= user.getName()%>
                            </a>
                        </li>
                    </ul>
                    <%
                            }
                        }
                    %>

                </div>
            </div>
            <div class="container pl-5 pr-5 my-4" >
                <hr>
                <div class="row">
                    <div class="col-md-6 offset-md-3">
                        <%
                            List<User> messagedUsers = MessageDao.getAllMessagesOfUser(currentUser.getId());
                            if (messagedUsers.isEmpty()) {
                        %>
                        <div class="text-center">
                            <h1 class="h3"> Message someone</h1 >
                        </div>
                        <%
                        } else {

                        %>

                        <Div class="text-center"> <h3 class="h3" >Your chats</h3 ></Div>

                        <%                            for (User user : messagedUsers) {
                                if (!user.getUsername().equals(currentUser.getUsername())) {
                        %>

                        <ul class="list-group">
                            <li class="list-group-item">
                                <img style="border-radius: 50%; height: 20px; width: 20px " class="img-fluid" src="Uploads/user-images/<%= user.getDpName()%>"> 
                                &nbsp;
                                <a href="Chats.jsp?id=<%= user.getId()%>"> 
                                    <%= user.getName()%>
                                </a>
                            </li>
                        </ul>
                        <%
                                    }
                                }
                            }
                        %>
                    </div>
                </div>
            </div>


    </body>
</html>
