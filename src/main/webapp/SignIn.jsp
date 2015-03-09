<%-- 
    Document   : SignIn
    Created on : Mar 5, 2015, 12:51:19 PM
    Author     : Iceman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="discussion.css">
        <title>Sign in</title>
    </head>
    <h1>Sign in</h1>
    <body>
        <form method="POST" action="VerifySignIn">
            <div class="center">
            Username: <input type="text" name="username"> <br />
            Password: <input type="password" name="password"> <br />
            <button type="submit">Sign in</button><button type="button" onclick="location.href='SignUp.jsp'">Create User</button>
            </div>
        </form>     
    </body>
</html>
