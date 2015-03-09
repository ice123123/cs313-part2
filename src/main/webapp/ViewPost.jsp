<%-- 
    Document   : ViewPost
    Created on : Mar 5, 2015, 1:12:02 PM
    Author     : Iceman
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="discussion.css">
        <title>Posts</title>
    </head>
    <body class="center">
        <h1>Posts</h1>
        <div class="center"> <button type="button" onclick="location.href='NewPostPage.jsp'">Add new Post</button></div>
        <br />
        <br />
        <a href="NewPostPage.jsp"></a>
        <c:forEach items="${posts}" var="post">
            <table class="center">
                <tr>
                    <td><b>User: </b>${post.username}</td>
                </tr>
                <tr>
                    <td><textarea readonly>${post.text}</textarea></td>
                </tr>                
                <tr>
                    <td><b>Date: </b>${post.date}</p></td>
                </tr>
            </table>                


            <br />
        </c:forEach>
        </div>
</html>
