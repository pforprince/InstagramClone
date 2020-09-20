<%-- 
    Document   : login
    Created on : 8 Jul, 2020, 4:06:27 PM
    Author     : princ
--%>

<%@page import="com.helpers.AlertMessage"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>

    <body class="container-fluid forms">

        <%
            AlertMessage alertMessage = (AlertMessage) session.getAttribute("alertMessage");
            if (alertMessage != null) {
        %>
        <div class="alert <%= alertMessage.getCssClass() %> alert-dismissible fade show" role="alert">
            <strong><%= alertMessage.getMessage() %></strong> <%= alertMessage.getDescription() %>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%
           session.removeAttribute("alertMessage");
            }
        %>
        <div class="row">
            <div class="col-md-6">
                <div class="container " style="width:400px; margin-top: 200px">
                    <form action="Login" method="post">
                        <div class="form-group" >
                            <label style="color: white" for="email">Email address:</label>
                            <input type="email" required name="email" class="form-control" id="email">
                        </div>
                        <div class="form-group" >
                            <label style="color: white" for="pwd">Password:</label>
                            <input type="password" required name="password" class="form-control" id="pwd">
                        </div>
                        <button type="submit" class="btn btn-success">Login</button>

                        <a  href="Signup.jsp" style="color: wheat">Don't have an account? Sign Up!</a>
                    </form>
                </div>   
            </div>
            <div class="col-md-6">
            </div></div>

    </body>
</html>
