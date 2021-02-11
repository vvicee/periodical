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
    <title><fmt:message key="registration.title"/> </title>
</head>
<header>
    <%@include file="WEB-INF/jspf/header.jspf"%>
</header>
<body class="container">

<h2><fmt:message key="registration.title"/></h2>
<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/signUp" >
    <div class="form-group">
        <label class="control-label col-xs-3" for="lastName"><fmt:message key="user.surname"/>:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" name="surname" id="lastName" placeholder="<fmt:message key="user.surname"/>">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="firstName"><fmt:message key="user.name"/>:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" name="name" id="firstName" placeholder="<fmt:message key="user.name"/>">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="inputEmail"><fmt:message key="user.mail"/>:</label>
        <div class="col-xs-9">
            <input type="email" class="form-control" name="email" id="inputEmail" placeholder="<fmt:message key="user.mail"/>">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="inputPassword"><fmt:message key="user.pass"/>:</label>
        <div class="col-xs-9">
            <input type="password" class="form-control" name="password" id="inputPassword" placeholder="<fmt:message key="user.pass"/>">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3"><fmt:message key="registration.notify"/> :</label>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="notify" value="Yes" checked> <fmt:message key="yes"/>
            </label>
        </div>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="notify" value="No"> <fmt:message key="no"/> <br>
            </label>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-9">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="registration.title"/>">
            <input type="reset" class="btn btn-default" value="<fmt:message key="login.reset"/>">
            <a href="${pageContext.request.contextPath}/home" class="btn-dark"><fmt:message key="login.back"/></a>
        </div>
    </div>
</form>
</body>
</html>
