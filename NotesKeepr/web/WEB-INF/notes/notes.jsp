<%-- 
    Document   : notes
    Created on : 08-Nov-2015, 1:01:59 PM
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
        <link rel="stylesheet" type="text/css" href="css/noteskeepr.css"/>
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
            <h1>Manage Notes</h1>
            <h3>Hello ${currentUser.firstName}</h3>
                <table border="1" class="table table-bordered">
                    <tr>
                        <th>Date/Time</th>
                        <th>Note Text</th>
                        <th>Delete</th>
                        <th>Edit</th>
                        <th>Collaborators</th>
                        <th>Add Collaborator</th>
                    </tr>
                    <c:forEach var="note" items="${noteList}">
                        <tr>
                            <td>${note.dateCreated}</td>
                            <td>${note.contents}</td>
                            <td>
                              <form action="notes" method="post">
                                  <input type="hidden" name="noteID" value="${note.noteID}">
                                  <input type="hidden" name="action" value="delete">
                                  <input type="submit" class="btn btn-info" value="Delete">
                              </form>
                            </td>
                            <td>
                              <form action="notes" method="post">
                                  <input type="hidden" name="noteID" value="${note.noteID}">
                                  <input type="hidden" name="action" value="view">
                                  <input type="submit" class="btn btn-info" value="Edit">
                              </form>
                            </td>
                            <td>
                                <form action="notes" method="post">
                                    <select name="removecollaborator-option">
                                        <c:set var="userList" value="${note.userList}"></c:set>
                                        <c:forEach var="user" items="${userList}">
                                            <option value="${user.userName}">${user.userName}</option>
                                        </c:forEach>
                                    </select>
                                    <input type="hidden" name="noteID" value="${note.noteID}">
                                    <input type="hidden" name="action" value="removecollaborator">
                                    <input type="submit" class="btn btn-info" value="Remove">
                                </form>
                            </td>
                            <td>
                                <form action="notes" method="post">
                                    <input list="addcollaborator-datalist" name="addcollaborator-datalist">
                                    <datalist id="addcollaborator-datalist">
                                        <c:forEach var="userName" items="${availableCollaborators1}">
                                            <c:if test="${sessionScope.currentUser.userName != userName}">
                                                <option value="${userName}">
                                            </c:if>
                                        </c:forEach>
                                    </datalist>
                                    <input type="hidden" name="noteID" value="${note.noteID}">
                                    <input type="hidden" name="action" value="addcollaborator">
                                    <input type="submit" class="btn btn-info" value="Add">
                                </form>
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
                <c:if test="${selectedNote == null}">
                    <div id="addnote">
                        <h2>Add Note</h2>
                        <form method="post" action="notes">
                            Contents: <br>
                            <textarea name="contents" rows="8" cols="50"></textarea><br>
                            <input type="hidden" name="action" value="add">
                            <input type="submit" class="btn btn-info" value="Add"/>
                        </form>
                    </div>
                </c:if>
                <c:if test="${selectedNote != null}">
                    <div id="editnote">
                        <h2>Edit Note</h2>
                        <form method="post" action="notes">
                            Contents: <br>
                            <textarea name="contents" rows="8" cols="50">${selectedNote.contents}</textarea><br>
                            <input type="hidden" name="noteID" value="${selectedNote.noteID}">
                            <input type="hidden" name="action" value="edit">
                            <input type="submit" class="btn btn-info" value="Save"/>
                        </form>
                    </div>
                </c:if>
                <h2>Collaborated With Me</h2>
                <c:choose>
                    <c:when test="${fn:length(collaboratedNotes) gt 0}">
                        <table border="1"  class="table table-bordered">
                            <tr>
                                <th>Date/Time</th>
                                <th>Note Text</th>
                                <th>Owner</th>
                                <th>Edit</th>
                            </tr>
                            <c:forEach var="note" items="${collaboratedNotes}">
                                <tr>
                                    <td>${note.dateCreated}</td>
                                    <td>${note.contents}</td>
                                    <td>${note.owner.userName}</td>
                                    <td>
                                      <form action="notes" method="post">
                                          <input type="hidden" name="noteID" value="${note.noteID}">
                                          <input type="hidden" name="action" value="viewcollaboratednote">
                                          <input type="submit" class="btn btn-info" value="Edit">
                                      </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <c:choose>
                            <c:when test="${collaboratedmessage != null}">
                                <div><b>${collaboratedmessage}</b></div>
                                <br>
                            </c:when>
                            <c:otherwise>
                                <br>
                                <br>
                            </c:otherwise>
                        </c:choose>
                        <c:if test="${selectedCollaboratedNote != null}">
                            <div id="editcollaboratednote">
                                <h2>Edit Collaborated Note</h2>
                                <form method="post" action="notes">
                                    Contents: <br>
                                    <textarea name="contents" rows="8" cols="50">${selectedCollaboratedNote.contents}</textarea><br>
                                    <input type="hidden" name="noteID" value="${selectedCollaboratedNote.noteID}">
                                    <input type="hidden" name="action" value="editcollaboratednote">
                                    <input type="submit" class="btn btn-info" value="Save"/>
                                </form>
                            </div>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                        <p>No notes are currently being collaborated with you.</p>
                    </c:otherwise>
                </c:choose>
        </div>
        <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
        <script>tinymce.init({ selector:'textarea' });</script>
    </body>
</html>
