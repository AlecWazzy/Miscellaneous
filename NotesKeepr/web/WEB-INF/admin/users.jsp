<%-- 
    Document   : users
    Created on : 08-Nov-2015, 11:18:59 AM
    Author     : awassill
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
        <div class="container">
            <h1>Manage Users</h1>
            <h2>Users</h2>
            <div id="users">
                <table border="1" class="table table-bordered">
                    <tr>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Delete</th>
                        <th>Edit</th>
                        <th>Impersonate</th>
                        <th>Modify Role</th>
                    </tr>
                    <c:forEach var="user" items="${users}">
                        <tr>
                            <td><c:out value="${user.userName}" /></td>
                            <td>${user.firstName}</td>
                            <td>${user.lastName}</td>
                            <td>${user.email}</td>
                            <td>${user.userPassword}</td>
                            <td>
                                <c:if test="${sessionScope.currentUser.userName != user.userName}">
                                    <form action="admin" method="post">
                                        <input type="hidden" name="username" value="${user.userName}">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="submit" class="btn btn-info" value="Delete">
                                    </form>
                                </c:if>
                            </td>
                            <td>
                                <form action="admin" method="post">
                                    <input type="hidden" name="username" value="${user.userName}">
                                    <input type="hidden" name="action" value="view">
                                    <input type="submit" class="btn btn-info" value="Edit">
                                </form>
                            </td>
                            <td>
                                <c:if test="${sessionScope.currentUser.userName != user.userName}">
                                    <form action="admin" method="post">
                                        <input type="hidden" name="username" value="${user.userName}">
                                        <input type="hidden" name ="action" value="impersonate">
                                        <input type="submit" class="btn btn-info" value="Impersonate">
                                    </form>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${sessionScope.currentUser.userName != user.userName}">
                                    <c:choose>
                                        <c:when test ="${fn:length(user.roleList) gt 0}">
                                            <form action="admin" method="post">
                                                <input type="hidden" name="username" value="${user.userName}">
                                                <input type="hidden" name ="action" value="demote">
                                                <input type="submit" class="btn btn-info" value="Demote">
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <form action="admin" method="post">
                                                <input type="hidden" name="username" value="${user.userName}">
                                                <input type="hidden" name ="action" value="promote">
                                                <input type="submit" class="btn btn-info" value="Promote">
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
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
            <c:if test="${selectedUser == null}">
                <div id="adduser">
                    <h2>Add User</h2>
                    <form method="post" class="col-xs-3" action="admin">
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
                            <input type="hidden" name="action" value="add">
                            <input type="submit" class="btn btn-info" value="Save"/>
                        </div>
                    </form>
                </div>
            </c:if>  
            <c:if test="${selectedUser != null}">
                <div id="edituser">
                    <h2>Edit ${selectedUser.userName}</h2>
                    <form method="post" class="col-xs-3" action="admin">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" name="username" id="username" value="${selectedUser.userName}" readonly="readonly"/>
                        </div>
                        <div class="form-group">
                            <label for="firstname">First Name:</label>
                            <input type="text" class="form-control" name="firstname" id="firstname" value="${selectedUser.firstName}" />
                        </div>
                        <div class="form-group">
                            <label for="lastname">Last Name:</label>
                            <input type="text" class="form-control" name="lastname" id="lastname" value="${selectedUser.lastName}" />
                        </div>
                        <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" name="password" id="password" value="${selectedUser.userPassword}" />
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="text" class="form-control" name="email" id="email" value="${selectedUser.email}" />
                        </div>
                        <div class="form-group">
                            <input type="hidden" name="action" value="edit">
                            <input type="submit" class="btn btn-info" value="Save"/>
                        </div>
                    </form>
                </div>
            </c:if>
        </div>
        <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    </body>
</html>
