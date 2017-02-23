<%-- 
    Document   : resetNewPassword
    Created on : Nov 20, 2015, 1:35:25 PM
    Author     : 645111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Password</title>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"/>
    </head>
    <body>
        <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="login">NotesKeepr</a>
            </div>
            <div class="">
                <a href="login" class="btn btn-info">Back to login</a>
            </div>
        </div>
        </nav>
        <div class="container">
            <h1>Enter a new password</h1>
            <form method="post" action="reset">
                <input type="password" name="password" value="${password}" />
                <input type="hidden" name="action" value="resetpassword" /><br>
                <input type="hidden" name="uuid" value="${uuid}" />
                <input type="submit" value="Submit" />
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
