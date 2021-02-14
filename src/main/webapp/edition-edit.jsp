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
    <title><fmt:message key="edition-edit.title"/></title>
</head>
<header>
    <%@ include file="WEB-INF/jspf/header.jspf" %>
</header>
<body>
<form method="post" action="${pageContext.request.contextPath}/admin/edit-edition">
    <div class="container">
        <input name="edition_id" type="hidden" value="${edition.id}">

        <div class="row">
            <div class="col-md-4">
                <b><h4><fmt:message key="admin.edition_title"/></h4></b>
            </div>
            <div class="col-md-6">
                <input class="input-field" name="title" value="${edition.title}"
                       placeholder="<fmt:message key="admin.edition_title"/>">
            </div>
        </div>

        <div class="row">
            <div class="col-md-4">
                <b><h4><fmt:message key="admin.publisher"/></h4></b>
            </div>
            <div class="col-md-6">
                <input class="input-field" name="publisher" value="${edition.publisher}"
                       placeholder="<fmt:message key="admin.publisher"/>">
            </div>
        </div>

        <div class="row">
            <div class="col-md-4">
                <b><h4><fmt:message key="admin.description"/></h4></b>
            </div>
            <div class="col-md-6 ">
                <input class="input-field" name="description" value="${edition.description}"
                       placeholder="<fmt:message key="admin.description"/>">
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <b><h4><fmt:message key="admin.price"/></h4></b>
            </div>
            <div class="col-md-">
                <input class="input-field" name="price" value="${edition.price}"
                       placeholder="<fmt:message key="admin.price"/>">
            </div>
        </div>

        <h4><fmt:message key="admin.theme"/></h4>
        <input name="theme" type="radio" value="education" checked> <fmt:message key="edition.theme.education"/>
        <input name="theme" type="radio" value="health"> <fmt:message key="edition.theme.health"/>
        <input name="theme" type="radio" value="sport"> <fmt:message key="edition.theme.sport"/>
        <input name="theme" type="radio" value="fashion"> <fmt:message key="edition.theme.fashion"/>
        <input name="theme" type="radio" value="garden"> <fmt:message key="edition.theme.garden"/>
        <input name="theme" type="radio" value="economy"> <fmt:message key="edition.theme.economy"/>
        <input name="theme" type="radio" value="science"> <fmt:message key="edition.theme.science"/>

        <h4><fmt:message key="admin.category"/></h4>
        <input name="category" type="radio" value="newspaper" checked> <fmt:message key="edition.category.newspaper"/>
        ">
        <input name="category" type="radio" value="magazine"> <fmt:message key="edition.category.magazine"/>
        <input name="category" type="radio" value="calendar"> <fmt:message key="edition.category.calendar"/>
        <input name="category" type="radio" value="yearbook"> <fmt:message key="edition.category.yearbook"/>
        <br>
        <input class="btn btn-primary ml-5" type="submit" value=" <fmt:message key="edition.edit"/> ">
    </div>
</form>
</body>
</html>
