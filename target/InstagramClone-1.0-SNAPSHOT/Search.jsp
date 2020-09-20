<%-- 
    Document   : Search
    Created on : 8 Jul, 2020, 6:26:32 PM
    Author     : princ
--%>

<%@page import="com.helpers.AlertMessage"%>
<%@page import="com.sun.media.sound.AlawCodec"%>
<%@page import="com.entities.User"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%@include  file="components/HideDetails.jsp" %>

        <%@include file="components/Navbar.jsp" %>

        <%            AlertMessage alertMessage = (AlertMessage) session.getAttribute("alertMessage");
            if (alertMessage != null) {
        %>
        <div class="container">
            <div class="alert <%= alertMessage.getCssClass()%> alert-dismissible fade show" role="alert">
                <strong>
                    <%= alertMessage.getMessage()%></strong> <%= alertMessage.getDescription()%>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div></div>
            <%
                    session.removeAttribute("alertMessage");
                }
            %>

        <div class="container" style="max-width: 400px">
            <form action="Search" method="POST">
                <div class="form-group">
                    <input type="hidden" name="operation" value="search">

                    <label for="exampleInputEmail1">Enter Username</label>

                    <input type="text" required class="form-control" name="username" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Username">
                    <small id="emailHelp" class="form-text text-muted">Press Enter to Search</small>
                </div>
                <button class="btn btn-outline-secondary">Search</button>
            </form>
        </div>
        <hr>
        <div class="container" style="max-width: 400px">
            <%                List<User> list = (List<User>) (session.getAttribute("searchedResults"));
                if (list == null) {
            %>
            <%
            } else {
                for (User user : list) {
            %>
            <ul class="list-group">
                <li class="list-group-item">
                    <img style="border-radius: 50%; height: 20px; width: 20px " class="img-fluid" src="Uploads/user-images/<%= user.getDpName()%>"> 
                    &nbsp;
                    <a href="User.jsp?username=<%= user.getUsername()%>"> 
                        <%= user.getName()%>
                    </a>
                </li>
            </ul>
            <%
                    }
                }
            %>

        </div>

    </body>
</html>
