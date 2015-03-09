<%-- 
    Document   : SignUp
    Created on : Mar 6, 2015, 8:43:48 AM
    Author     : Iceman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="discussion.css">
        <title>Create User</title>
    </head>
    <body>
        <h1>Create User</h1>
        <form method="POST" action="SignUp">
            <div class="center">
                username: <input type="text" name="username"> <br />
                password: <input type="password" name="password"> <br />
                <button type="submit">Create user</button>
            </div>
        </form>
    </body>
</html>
