<%-- 
    Document   : Upload
    Created on : 8 Jul, 2020, 7:21:13 PM
    Author     : princ
--%>

<%@page import="com.helpers.AlertMessage"%>
<%@page import="com.entities.User"%>
<%@page import="com.entities.Post"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Upload</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%@include  file="components/HideDetails.jsp" %>

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
        <div class="container p-5">
            <form method="post" action="Upload" enctype="multipart/form-data">
                <div class="form-group">
                    <label>Caption</label>
                    <input type="text" required name="caption" class="form-control" placeholder="Caption for your memory">
                </div>
                <div class="form-group">
                    <label>Tags</label>
                    <input type="text" required name="tags" class="form-control" placeholder="Tags">
                </div>
                <div class="form-group">
                    <label>Choose Image
                        <input type="file" name="image" class="form-control">
                        </div>
                        <button class="btn btn-outline-secondary mt-3">Upload</button>
                        </form>
                </div>

                <hr>

                </body>
                </html>
