<%-- 
    Document   : Chats
    Created on : 14 Jul, 2020, 11:53:12 AM
    Author     : princ
--%>

<%@page import="com.dao.MessageDao"%>
<%@page import="com.entities.Message"%>
<%@page import="java.util.List"%>
<%@page import="com.helpers.AlertMessage"%>
<%@page import="com.dao.UserDao"%>
<%@page import="com.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Chats</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%@include file="components/Navbar.jsp" %>
        <%
            User currentUser = (User) session.getAttribute("currentUser");
            int friendId = Integer.parseInt(request.getParameter("id"));
            User friendUser = UserDao.fetchUserById(friendId);
        %>
        <%
            AlertMessage alertMessage = (AlertMessage) session.getAttribute("alertMessage");
            if (alertMessage != null) {
        %>
        <div class="alert <%= alertMessage.getCssClass()%> alert-dismissible fade show" role="alert">
            <strong><%= alertMessage.getMessage()%></strong> <%= alertMessage.getDescription()%>
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <%
            }
        %>
        <hr>
        <div class="container">
            <!--<p class="h3 text-center">Messages</p>-->
            <div class="row">
                <Div class="col-md-6 offset-md-3">

                    <div style="background-color: #e2e2e2; overflow: scroll; max-height: 450px" id="messageDiv" class="m-2 p-3">
                        <%
                            List<Message> messages = MessageDao.getLatestMessages(currentUser.getId(), friendUser.getId());
                            if (messages != null) {
                                for (Message message : messages) {
                                    if (message.getSenderId() == currentUser.getId()) {
                        %>
                        <div class="list-group mt-2" style="width: max-content; max-width: 300px; margin-left: auto;">
                            <li class=" float-left list-group-item list-group-item-action list-group-item-secondary">
                                <%= message.getMessage()%></li> 
                            <img class="mt-1" style="border-radius: 50%; width: 20px; height: 20px; align-self: 
                                 flex-end" src="Uploads/user-images/<%= currentUser.getDpName() %>"> 
                        </div>
                        <%} else {
                        %>
                        <div class="list-group mt-2" style="width: max-content; max-width: 300px; margin-right: auto">
                            <li class="list-group-item list-group-item-action list-group-item-secondary">
                                <%= message.getMessage()%></li>
                            <img class="mt-1" style="border-radius: 50%; width: 20px; 
                                 height: 20px; align-self: flex-start" src="Uploads/user-images/<%= friendUser.getDpName() %>"> 

                        </div>
                        <%
                                }
                            }
                        } else {
                        %>
                        <h1>Send message to someone</h1>
                        <%
                            }
                        %>
                    </div>

                    <hr style="height:20px">

                    <div>
                        <form action="Message" method="post">
                            <div class="form-group">
                                <input type="hidden" name="friendId" value="<%=friendId%>" >
                                <input required class="form-control" name="message" placeholder="Enter Message" >
                            </div>
                            <button type="submit" class="btn btn-outline">
                                Send 
                            </button>
                            <hr>
                        </form>
                    </div>
                </Div>
            </div>
        </div>
        <script>
            var objDiv = document.getElementById("messageDiv");
            objDiv.scrollTop = objDiv.scrollHeight;

        </script>
    </body>
</html>
