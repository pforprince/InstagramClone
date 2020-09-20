<%-- 
    Document   : Extra
    Created on : 12 Jul, 2020, 3:35:33 PM
    Author     : princ
--%>

<%@page import="java.util.Date"%>
<%@page import="com.helpers.FactoryProvider"%>
<%@page import="org.hibernate.Transaction"%>
<%@page import="org.hibernate.Session"%>
<%@page import="com.entities.Followers"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%

                try {
                    Followers followers = new Followers(1, 2, new Date());
                    Session session1 = FactoryProvider.getFactory().openSession();
                    Transaction transaction = session1.beginTransaction();
                    session1.save(followers);
                    transaction.commit();
                    session1.close();
                    
                    out.println("saved");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        %>

    </body>
</html>
