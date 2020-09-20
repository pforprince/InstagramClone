<%-- 
    Document   : index.js
    Created on : 8 Jul, 2020, 3:43:35 PM
    Author     : princ
--%>

<%@page import="com.helpers.FactoryProvider"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instagram</title>
        <style>
            body{
                margin: 0px;
                background-image: url('https://fischerinstitute.com/wp-content/uploads/2013/05/instagram-background.jpg');
                background-position: center;
                background-repeat: no-repeat;
                background-size: cover;
            }
        </style>
        <%@include file="components/CommonProperties.jsp" %>
    </head>
    <body>
        <div class="container-fluid" style="margin: 0px;height: 100vh;">
            <div class="row">
                <div class="col-md-12 text-center my-5" style="color: white">
                    <p class="display-4">Glad you choosed our platform</p>

                    <div class="mt-5">
                        <h5 class="card-title">Let's Dive in</h5>
                        <p class="display-4" style="color: black">Start sharing your rememberable moments</p>
                        <div class="container">
                            <div class="row px-5" style="margin-left: 100px; margin-right: 100px">
                                <div class="col-md-6">
                                    <a href="Signup.jsp" class="btn btn-danger" style="width: 60%">SignUp</a>
                                </div>
                                <div class="col-md-6">
                                    <a href="login.jsp" class="btn btn-danger" style="width: 60%">Login</a>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </div>


        </div>

    </div>

</body>
</html>
