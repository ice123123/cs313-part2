<%-- 
    Document   : NewPostPage
    Created on : Mar 5, 2015, 1:06:06 PM
    Author     : Iceman
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="discussion.css">
        <title>New Post</title>
    </head>
    <body>
        <h1>New Post</h1>
        <form action="CreatePost" method="POST" id="myForm">
            <div class="center">
                <textarea name="text" form="myForm"></textarea> <br />
                <button type="submit" value="Post">Post</button>
                <button type="button" onclick="location.href='LoadPost'">View Posts</button>
            </div>            
        </form>

    </body>
</html>
