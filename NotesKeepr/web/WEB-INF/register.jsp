<%-- 
    Document   : register
    Created on : Nov 12, 2015, 12:25:40 PM
    Author     : 645111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NotesKeeper - Register</title>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"/>
    </head>
    <body>
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="login">NotesKeepr</a>
                </div>
                <div>
                    <ul class="nav navbar-nav">
                        <li><a href="login">Back to Login</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container">
            
            <h1>Register</h1>
            <br>
            <form method="post" role="form" class="col-xs-4" action="register">
                <div class="form-group">
                    <label for="username">Username:</label>
                    <input type="text" class="form-control" name="username" id="username" />
                </div>
                <div class="form-group">
                    <label for="firstname">First Name:</label>
                    <input type="text" class="form-control" name="firstname" id="firstname" />
                </div>
                <div class="form-group">
                    <label for="lastname">Last Name:</label>
                    <input type="text" class="form-control" name="lastname" id="lastname" />
                </div>
                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" name="password" id="password" />
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="text" class="form-control" name="email" id="email" />
                </div>
                <div class="form-group">
                    <input type="hidden" name="action" value="register">
                    <input type="submit" class="btn btn-info" value="Register"/>
                </div>
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
            <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        </div>
    </body>
</html>
