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
    <title><fmt:message key="user-edit.title"/></title>
</head>
<header>
    <%@include file="../jspf/header.jspf" %>
</header>
<body>
<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/profile/edit">
        <div class="row">
            <div class="col-md-4">
                <b><h4><fmt:message key="user.name"/></h4></b>
            </div>
            <div class="col-md-6">
                <input class="input-field" name="name" value="${user.name}" minlength="1" placeholder="<fmt:message key="user.name"/> ">
            </div>
        </div>

        <div class="row">
            <div class="col-md-4">
                <b><h4><fmt:message key="user.surname"/></h4></b>
            </div>
            <div class="col-md-6">
                <input class="input-field" name="surname" minlength="1" value="${user.surname}" placeholder="<fmt:message key="user.surname"/>">
            </div>
        </div>

        <h4><fmt:message key="registration.notify"/></h4>
        <input name="notify" type="radio" value="yes" checked> <fmt:message key="yes"/>
        <input name="notify" type="radio" value="no"> <fmt:message key="no"/>

        <br>

        <input class="btn btn-warning" style="width: 200px" type="submit" value="<fmt:message key="edition.edit"/>">

    </form>
</div>
</body>
</html>
