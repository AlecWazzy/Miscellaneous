<%-- 
    Document   : navigation_menu
    Created on : Nov 13, 2015, 10:06:26 AM
    Author     : 645111
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- any content can be specified here e.g.: --%>
<ul class="nav navbar-nav">
        <li><a href="notes">Manage Notes</a></li>
        <c:if test="${not isAdmin}">
            <li><a href="account">Manage Account</a></li>
        </c:if>
        <c:if test="${isAdmin}">
            <li><a href="admin">Manage Users</a></li> 
        </c:if>
            <li><c:out value="${logoutlink}" escapeXml="false"/></li>
</ul>
<!--<div id="navigation_menu">
    <a href="notes">Manage Notes</a> &nbsp;
    <c:if test="${not isAdmin}">
        <a href="account">Manage My Account</a> &nbsp;
    </c:if>
    <c:if test="${isAdmin}">
        <a href="admin">Manage Users</a> &nbsp;
    </c:if>
    <c:out value="${logoutlink}" escapeXml="false"/>
</div>-->