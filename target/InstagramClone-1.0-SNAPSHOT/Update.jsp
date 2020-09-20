<%-- 
    Document   : Update
    Created on : 11 Aug, 2020, 12:02:54 PM
    Author     : princ
--%>

<%@page import="com.dao.PostDao"%>
<%@page import="com.entities.Post"%>
<%@page import="com.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Post</title>
        <%@include file="components/CommonProperties.jsp" %>

    </head>
    <body>
        <%
            User currentUser = (User) session.getAttribute("currentUser");
            Post post = (Post) session.getAttribute("currentPost");
//            int postId = Integer.parseInt(request.getParameter("q"));
//            Post post = PostDao.getPostById(postId);
        %>
        <%@include file="components/Navbar.jsp" %>

        <div class="container" style="padding: 20px; margin-bottom: 40px" >
            <div class="row">
                <div class="col-md-6 offset-md-3">
                    <%
                        if (!post.getImageName().equals("defaultpost")) {
                    %>
                    <div class="card">
                        <img class="img-fluid" style="max-height: 500px" src="Uploads/user-images/<%= post.getImageName()%>">
                    </div>
                    <%
                        }
                    %>
                    <hr>
                    <form action="UpdatePost" method="post" >
                        <div class="form-group">
                            <label for="exampleInputEmail1">Caption</label>
                            <input type="text" name="caption" value="<%= post.getCaption()%>"  class="form-control"  placeholder="Enter email">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Tags</label>
                            <input type="text" name="tags" value="<%= post.getTags()%>"  class="form-control"  placeholder="Enter email">
                        </div>
                        <div class="text-center">
                            <button id="submit-btn"  type="submit" class="btn btn-primary" style="width: 60%">Update</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
