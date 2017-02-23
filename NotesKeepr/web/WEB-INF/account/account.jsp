<%-- 
    Document   : account
    Created on : Nov 11, 2015, 6:52:02 PM
    Author     : 645111
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NotesKeeper</title>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css"/>
    </head>
    <body>
        <%@taglib tagdir="/WEB-INF/tags/" prefix="sait" %>
        <nav class="navbar navbar-default">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="notes">NotesKeepr</a>
            </div>
            <div>
                <sait:navigation_menu/>
            </div>
        </div>
        </nav>
        <div class='container'>
            <h1>Manage Account</h1>
            <div id="editaccount">
                <h2>Edit Account Information</h2>
                <form method="post" role="form" action="account">
                    <div class="form-group">
                        <label for="username">Username:</label>
                        <input type="text" name="username" id="username" value="${currentUser.userName}" readonly="readonly"/>
                    </div>
                    <div class="form-group">
                        <label for="firstname">First Name:</label>
                        <input type="text" name="firstname" id="firstname" value="${currentUser.firstName}" />
                    </div>
                    <div class="form-group">
                        <label for="lastname">Last Name:</label>
                        <input type="text" name="lastname" id="lastname" value="${currentUser.lastName}" />
                    </div>
                    <div class="form-group">
                        <label for="password">Password:</label>
                        <input type="password" name="password" id="password" value="${currentUser.userPassword}" />
                    </div>
                    <div class="form-group">
                        <label for="email">Email:</label>
                        <input type="text" name="email" id="email" value="${currentUser.email}" />
                    </div>
                    <div class="form-group">
                        <input type="hidden" name="action" value="edit">
                        <input type="submit" class="btn btn-info" value="Save Changes"/>
                    </div>
                </form>
                <br>
                <c:choose>
                    <c:when test="${editfeedback != null}">
                        <div><b>${editfeedback}</b></div>
                        <br>
                    </c:when>
                    <c:otherwise>
                        <br>
                        <br>
                    </c:otherwise>
                </c:choose>
            </div>
            <div id="deleteaccount">
                <h2>Delete My Account</h2><br>
                <form method="post" action="account">
                    <div class="form-group">
                        <input type="hidden" name="username" value="${currentUser.userName}" />
                        <input type="hidden" name="action" value="delete">
                        <input type="submit" class="btn btn-info" value="Delete Account"/>
                    </div>
                </form>
                <br>
                <c:choose>
                    <c:when test="${deletefeedback != null}">
                        <div><b>${deletefeedback}</b></div>
                        <br>
                    </c:when>
                    <c:otherwise>
                        <br>
                        <br>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
