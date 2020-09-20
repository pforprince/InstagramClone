<%@page import="com.entities.User"%>
<%
    User loggedInUser = (User) session.getAttribute("currentUser");
    if (loggedInUser == null) {
        response.sendRedirect("login.jsp");
    }
%>
