<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:choose>
    <c:when test="${sessionScope.locale != null}">
        <fmt:setLocale value="${param.locale}" scope="session"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="en"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="resources"/>
<html>
<head>
    <title><fmt:message key="edition-edit.title"/> </title>
</head>
<header>
    <%@ include file="WEB-INF/jspf/header.jspf" %>
</header>
<body class="container" style="width: 1200px">
<form method="post" action="${pageContext.request.contextPath}/admin/edit-edition">
    <div class="alert alert-info mt-2">
        <input name="edition_id" type="hidden" value="${edition.id}">
        <input name="title" value="${edition.title}" placeholder="<fmt:message key="admin.edition_title"/> ">
        <input name="publisher" value="${edition.publisher}" placeholder="<fmt:message key="admin.publisher"/>">
        <input name="description" value="${edition.description}" placeholder="<fmt:message key="admin.description"/>">
        <input name="price" value="${edition.price}" placeholder="<fmt:message key="admin.price"/>">
        <h2><fmt:message key="admin.theme"/></h2>
        <input name="theme" type="radio" value="education" checked> <fmt:message key="edition.theme.education"/>
        <input name="theme" type="radio" value="health"> <fmt:message key="edition.theme.health"/>
        <input name="theme" type="radio" value="sport"> <fmt:message key="edition.theme.sport"/>
        <input name="theme" type="radio" value="fashion"> <fmt:message key="edition.theme.fashion"/>
        <input name="theme" type="radio" value="garden"> <fmt:message key="edition.theme.garden"/>
        <input name="theme" type="radio" value="economy"> <fmt:message key="edition.theme.economy"/>
        <input name="theme" type="radio" value="science"> <fmt:message key="edition.theme.science"/>

        <h2> <fmt:message key="admin.category"/></h2>
        <input name="category" type="radio" value="newspaper" checked> <fmt:message key="edition.category.newspaper"/> ">
        <input name="category" type="radio" value="magazine">  <fmt:message key="edition.category.magazine"/>
        <input name="category" type="radio" value="calendar">  <fmt:message key="edition.category.calendar"/>
        <input name="category" type="radio" value="yearbook">  <fmt:message key="edition.category.yearbook"/>
    </div>
    <input class="btn btn-warning ml-5" type="submit" value=" <fmt:message key="edition.edit"/> ">
</form>
</body>
</html>
