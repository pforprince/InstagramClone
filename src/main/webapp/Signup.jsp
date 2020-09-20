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
        <title>Sign Up</title>
        <%@include file="components/CommonProperties.jsp" %>
    </head>

    <body class="container-fluid forms">

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
        <div class="row">
            <div class="col-md-6">
                <div class="container " style="width:400px; margin-top: 160px">
                    <form action="SignUp" method="post">
                        <div class="form-group">
                            <label style="color: white" for="email">Full Name</label>
                            <input required type="text" class="form-control" name="name">
                        </div>
                        <div class="form-group">
                            <label style="color: white" for="email">Username 
                                <span id="status" class="d-none" style="color: greenyellow"> (Available)</span></label>
                            <input required type="text" class="form-control" id="username" name="username">
                        </div>  
                        <a class="btn btn-outline-warning" onclick="checkUsername()" id="check-btn" >Check Availability</a>
                        <div class="form-group" >
                            <label style="color: white" for="email">Email address:</label>
                            <input required type="email" class="form-control" name="email">
                        </div>
                        <div class="form-group">
                            <label style="color: white" for="pwd">Password:</label>
                            <input required type="password" class="form-control" name="password">
                        </div>

                        <button id="submit-btn" type="submit" class="btn btn-success disabled">Sign Up</button>
                        <a class="float-right" href="login.jsp" style="color: wheat">Already have an account? LOGIN!!</a>
                    </form>
                </div>   
            </div>
            <div class="col-md-6">
            </div></div>

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
                        console.log(data);
                        if (data.trim() === "available") {
                            $("#status").removeClass("d-none");
                            $("#submit-btn").removeClass("disabled");
                            document.getElementById("status").innerHTML = "Available";
                        } else if (data.trim() === "unavailable")
                        {
                            $("#status").removeClass("d-none");
                            $("#status").html("UnAvailable");
                            $("#submit-btn").addClass("disabled");
                        }
                    }
                }
                );
            }

        </script>

    </body>
</html>
