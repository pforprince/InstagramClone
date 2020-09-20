<%-- 
    Document   : EditProfile
    Created on : 11 Jul, 2020, 2:53:04 PM
    Author     : princ
--%>

<%@page import="com.helpers.AlertMessage"%>
<%@page import="com.entities.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Profile</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <%@include  file="components/HideDetails.jsp" %>

        <%@include file="components/Navbar.jsp" %>
        <%            User currentUser = (User) session.getAttribute("currentUser");
        %>
        <div class="container-fluid p-3 text-center">
            <div class="alert alert-info alert-dismissible fade show" role="alert">
                <strong>Important!! If you're using your own username then there is no need to check its availabilty!!
                    It might show you unavailable!!</strong> 

            </div>
        </div>


        <div class="container">
            <%
                AlertMessage alertMessage = (AlertMessage) session.getAttribute("alertMessage");
                if (alertMessage != null) {
            %>
            <div class="container p-3">
                <div class="alert <%= alertMessage.getCssClass()%> alert-dismissible fade show" role="alert">
                    <strong><%= alertMessage.getMessage()%></strong> <%= alertMessage.getDescription()%>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>

            <%
                    session.removeAttribute("alertMessage");
                }
            %>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-md-6 offset-md-3">

                    <form action="EditProfile" method="post" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Name</label>
                            <input type="text" name="name" value="<%= currentUser.getName()%>" class="form-control"  placeholder="Enter name">
                        </div>
                        <div class="form-group">
                            <label for="email">Username 
                                <span id="status" class="d-none" style="color: red"> (Available)</span></label>
                            <input required type="text" value="<%= currentUser.getUsername()%>" class="form-control" id="username" name="username">
                        </div>  
                        <div class="text-center">                        <a class="btn btn-info" onclick="checkUsername()" id="check-btn" style="width: 60%">Check Availability</a>
                        </div>
                        <br>
                        <div class="form-group">
                            <input type="file" name="image" class="form-control"  placeholder="Enter email">
                        </div>
                        <div class="text-center">
                            <input name="dpname" value="<%= currentUser.getDpName()%>" type="hidden">
                            <input name="password" value="<%= currentUser.getPassword()%>" type="hidden">
                            <button id="submit-btn"  type="submit" class="btn btn-info" style="width: 60%; color: black">Update</button>
                        </div>

                    </form>

                </div>

            </div>
            <script>
                $("input#username").on({
                    keydown: function (e) {
                        if (e.which === 32)
                            return false;
                    },
                    change: function () {
                        this.value = this.value.replace(/\s/g, "");
                    }
                });
            </script>
        </script>
        <script>
            function checkUsername() {
                let username = $("#username").val();
                console.log("printing the usernmae ---------------" + username);
                data = {
                    username: username
                };
                $.ajax({
                    url: "UniqueUsername",
                    data: data,
                    success: function (data, textStatus, jqXHR) {
                        console.log("printing data receiving from servlet");
                        console.log(data);
                        if (data.trim() === "available") {
                            console.log("in the if statement of js");
                            $("#status").removeClass("d-none");
                            $("#submit-btn").removeClass("disabled");
                            document.getElementById("status").innerHTML = "Available";

                        } else if (data.trim() === "unavailable") {
                            console.log("in the else statement of js");
                            $("#status").removeClass("d-none");
                            $("#status").html("UnAvailable");
                            $("#submit-btn").addClass("disabled");
                        } else {
                            console.log("in the third else statement");
                        }
                    }});
            }

        </script>

</body>
</html>
