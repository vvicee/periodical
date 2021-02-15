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
    <title><fmt:message key="login.title"/> </title>
</head>
<header>
    <%@include file="WEB-INF/jspf/header.jspf" %>
</header>
<body class="container">
<h2><fmt:message key="login.auth"/> </h2>
<hr>
<h4>${message}</h4>
<p class="text-danger"><c:out value='${requestScope.active}'/></p>
<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login" >
    <div class="form-group">
        <label class="control-label col-xs-3" for="inputEmail"><fmt:message key="user.mail"/>:</label>
        <div class="col-xs-9">
            <input type="email" class="form-control" data-pattern="[a-zA-Z0-9]+@[a-z]+\.[a-z]+" name="email"
                   id="inputEmail" minlength="1" placeholder="<fmt:message key="user.mail"/>">
        </div>
    </div>
    <p class="text-danger"><c:out value='${requestScope.email}'/></p>

    <div class="form-group">
        <label class="control-label col-xs-3" for="inputPassword"><fmt:message key="user.pass"/>:</label>
        <div class="col-xs-9">
            <input type="password" class="form-control" name="password" data-pattern=".{1,}"
                   id="inputPassword" minlength="1" placeholder="<fmt:message key="user.pass"/>">
        </div>
    </div>
    <p class="text-danger"><c:out value='${requestScope.password}'/></p>

    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-9">
            <input type="submit" class="btn btn-primary" value="<fmt:message key="login.enter"/>">
            <input type="reset" class="btn btn-default" value="<fmt:message key="login.reset"/>">
            <a href="${pageContext.request.contextPath}/home" class="btn-dark"><fmt:message key="login.back"/></a>
        </div>
    </div>
</form>
</body>
</html>
