<%-- 
    Document   : login
    Created on : Nov 08, 2015, 1:08:53 PM
    Author     : awassill
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NotesKeeper - Login</title>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"/>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="login">NotesKeepr</a>
                </div>
            </div>
        </nav>
        <div class="container">
            <div id="login">
                <h1>NotesKeeper Login</h1>
                <form role="form" method="post" action="login">
                    <div class="form-group">
                        Username: <input type="text" class="form-control" name="username" id="username" value="${username}"/><br>
                    </div>
                    <div class="form-group">
                        Password: <input type="password" class="form-control" name="password" id="password" value="${password}"/><br>
                    </div>
                    <input type="hidden" name="action" value="login">
                    <input type="submit" class="btn btn-info" value="Login"/>
                </form>
                <br>
                <c:choose>
                    <c:when test="${message != null}">
                        <div><b>${message}</b></div>
                        <br>
                    </c:when>
                    <c:otherwise>
                        <br>
                        <br>
                    </c:otherwise>
                </c:choose>
                <p>Forgot your password? <a href="reset" class="btn btn-info" role="button">Click here</a></p>
                <p>Don't have an account? <a href="register?action=register" class="btn btn-info" role="button">Register here!</a></p>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
