<%-- 
    Document   : index
    Created on : 22 Sep, 2018, 10:38:57 PM
    Author     : Mayank420
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form name="loginform" action="LoginResponse.jsp" method="GET">
            UserName: &nbsp;<input type="text" name="username" value="" size="50px" />
            <br/>
            <br/>
            Password:&nbsp;&nbsp; <input type="password" name="password" value="" size="50px"/>
            <br/>
            <br/>
            <input type="submit" value="Login" name="login" />
        </form>
    </body>
</html>
