<%-- 
    Document   : Settings
    Created on : 11 Aug, 2020, 5:12:52 PM
    Author     : princ
--%>

<%@page import="com.helpers.AlertMessage"%>
<%@page import="com.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Setting</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%
            User currentUser = (User) session.getAttribute("currentUser");
        %>
        <%@include file="components/Navbar.jsp" %>

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

        <div class="container" style="padding-left: 200px; padding-right: 200px"> 
            <div>
                <p class="h4">Edit password</p>
                <hr>
                <form action="Settings" method="post">
                    <input type="hidden" name="operation" value="password">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Old Password</label>
                        <input required type="password" name="oldpassword" class="form-control"   placeholder="Enter old password">
                    </div>
                    <div class="form-group">
                        <label for="email">New Password </label>
                        <input required type="password"  class="form-control"  name="newpassword" placeholder="Enter new password">
                    </div>  
                    <div class="form-group">
                        <label for="email">Confirm Password </label>
                        <input required type="password"  class="form-control"  name="confirmpassword" placeholder="Confirm password">
                    </div>  
                    <div class="text-center">
                        <button class="btn btn-secondary" style="width: 50%; color: white">Change Password</button>
                    </div>
                </form>
            </div>
        </div>

        <hr>
        <div class="container" style="padding-left: 200px; padding-right: 200px; margin-bottom: 100px"> 
            <div>
                <p class="h4">Edit email</p>
                <hr>
                <form action="Settings" method="post">
                    <input type="hidden" name="operation" value="email">
                    <div class="form-group">
                        <label for="exampleInputEmail1">Email</label>
                        <input type="email" required name="email" class="form-control"  placeholder="Enter new email">
                    </div>

                    <div class="text-center">
                        <button class="btn btn-secondary" style="width: 50%; color: white">Change Email</button>
                    </div>

                </form>
            </div>
        </div>
    </body>
</html>
