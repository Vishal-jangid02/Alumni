<%-- 
    Document   : LoginResponse
    Created on : 22 Sep, 2018, 10:48:12 PM
    Author     : Mayank420
--%>

<%@page import="org.json.JSONObject"%>
<%@page import="java.sql.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            JSONObject js= new JSONObject();;
            
            try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Mayank", "root", "kvalwar");
                    
                    PreparedStatement ps = con.prepareStatement("select * from demo where username=? and password=?");
                    ps.setString(1, username);
                    ps.setString(2, password);
                    
                    ResultSet rs = ps.executeQuery();
                    
                    if (rs.next()) {
                             js.put("response", "Yes");
                             
                             pageContext.setAttribute("answer", "Yes");
                        }
                    out.write(js.toString());
                } catch (Exception e) {
                    System.out.println(e.getLocalizedMessage());
                    System.out.println("Exception");
                }
        %>
        
        <h1><c:out value="${answer}"/></h1>
    </body>
</html>
