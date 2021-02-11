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
    <title><fmt:message key="user-edit.title"/> </title>
</head>
<header>
    <%@include file="WEB-INF/jspf/header.jspf"%>
</header>
<body class="container" style="width: 1200px">
<form method="post" action="${pageContext.request.contextPath}/profile/edit">
    <div class="alert alert-info mt-2">
        <input name="name" value="${user.name}" placeholder="<fmt:message key="user.name"/> ">
        <input name="surname" value="${user.surname}" placeholder="<fmt:message key="user.surname"/>">
        <input name="notify" type="radio" value="yes" checked> <fmt:message key="yes"/>
        <input name="notify" type="radio" value="no"> <fmt:message key="no"/>
    </div>
    <input class="btn btn-warning ml-5" type="submit" value="<fmt:message key="edition.edit"/>">
</form>
</body>
</html>
