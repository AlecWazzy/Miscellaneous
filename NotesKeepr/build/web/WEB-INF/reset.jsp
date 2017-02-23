<%-- 
    Document   : forgot
    Created on : Nov 19, 2015, 5:21:10 PM
    Author     : 645111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
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
            <h1>Reset Password</h1>
            <br>
            <p>Please enter your email address to reset your password.</p>
            <br>
            <form method="post" role="form" class="col-xs-4" action="reset">
                <div class="form-group">
                    Email Address: <input type="text" class="form-control" name="email" /><br>
                    <input type="hidden" name="action" value="sendemail"/>
                    <input type="submit" class="btn btn-info" value="Send Email" />
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
        </div>
        <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
