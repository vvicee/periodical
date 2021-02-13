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

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6" style=" border: 4px double black; padding: 15px">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login">
                <span class="heading" style="text-align: center"><fmt:message key="login.auth"/></span> <br><br>
                <div class="form-group">
                    <input type="email" class="form-control" name="email" id="inputEmail"
                           data-pattern="[a-zA-Z0-9]+@[a-z]+\.[a-z]+" placeholder="<fmt:message key="user.mail"/>">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group help">
                    <input type="password" class="form-control" name="password" id="inputPassword"
                           data-pattern=".{1,}"
                           placeholder="<fmt:message key="user.pass"/>">
                    <i class="fa fa-lock"></i>
                    <a href="#" class="fa fa-question-circle"></a>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-9">
                        <input type="submit" class="btn btn-primary" value="<fmt:message key="login.enter"/>">
                        <input type="reset" class="btn btn-default" value="<fmt:message key="login.reset"/>">
                        <a href="${pageContext.request.contextPath}/home" class="btn-dark"><fmt:message key="login.back"/></a>
                    </div>
                </div>
            </form>
        </div>
    </div><!-- /.row -->
</div><!-- /.container -->
</body>
</html>
