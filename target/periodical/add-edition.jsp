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
    <title><fmt:message key="admin.add_edition"/></title>
</head>
<header>
    <%@include file="WEB-INF/jspf/header.jspf" %>
</header>
<body>
<div class="container">
    <form accept-charset="UTF-8" style="text-align: left;" method="post"
          action="${pageContext.request.contextPath}/admin/add-edition">
<%--        <img src="${pageContext.request.contextPath}/images/news.jpg" style="width: 350px; height: 300px;float: right;">--%>
        <div class="row">
            <div class="col-md-4">
                <b><fmt:message key="admin.edition_title"/>:</b>
            </div>
            <div class="col-md-4">
                <input class="input-field" name="title" type="text" placeholder=""><br><br>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <b><fmt:message key="admin.publisher"/>:</b>
            </div>
            <div class="col-md-6">
                <input class="input-field" name="publisher" type="text" placeholder=""><br><br>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <b><fmt:message key="admin.description"/></b>
            </div>
            <div class="col-md-6">
                <input class="input-field" name="description" type="text" placeholder=""><br><br>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <b><fmt:message key="admin.price"/>:</b>
            </div>
            <div class="col-md-6">
                <input class="input-field" name="price" type="number" min="1"/><br><br>
            </div>
        </div>

        <b><fmt:message key="admin.theme"/>:</b><br>
        <input name="theme" type="radio" value="education" checked> <fmt:message key="edition.theme.education"/>
        <input name="theme" type="radio" value="health"> <fmt:message key="edition.theme.health"/>
        <input name="theme" type="radio" value="sport"> <fmt:message key="edition.theme.sport"/>
        <input name="theme" type="radio" value="fashion"> <fmt:message key="edition.theme.fashion"/>
        <input name="theme" type="radio" value="garden"> <fmt:message key="edition.theme.garden"/>
        <input name="theme" type="radio" value="economy"> <fmt:message key="edition.theme.economy"/><br><br>
        <b><fmt:message key="admin.category"/>:</b> <br>
        <input name="category" type="radio" value="newspaper" checked> <fmt:message key="edition.category.newspaper"/>
        <input name="category" type="radio" value="magazine"> <fmt:message key="edition.category.magazine"/>
        <input name="category" type="radio" value="calendar"> <fmt:message key="edition.category.calendar"/>
        <input name="category" type="radio" value="yearbook"> <fmt:message key="edition.category.yearbook"/>
        <br><br>
        <button class="button" style="width: 250px; background-color: #9999e8;"><fmt:message
                key="admin.add_edition"/></button>
    </form>
</div>
</body>
</html>
