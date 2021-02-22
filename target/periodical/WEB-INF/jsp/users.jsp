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
    <title><fmt:message key="index.title"/></title>
</head>
<header>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
</header>
<body class="container">
<table class="table table-striped">
    <thead>
    <tr>
        <th scope="col">Id</th>
        <th scope="col">First</th>
        <th scope="col">Last</th>
        <th scope="col">E-mail</th>
        <th scope="col">Role</th>
        <th scope="col">Status</th>
        <th scope="col">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user">
        <c:if test="${user.role != 'ADMIN'}">
            <tr>
                <form>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>

                    <c:choose>
                        <c:when test="${user.isActive() == 'true'}">
                            <td style="color: green"><b>Active</b></td>
                            <td>
                                <a class="btn btn-danger"
                                   href="${pageContext.request.contextPath}/admin/block?user_id=${user.id}&active=false">Block</a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td style="color: #800000"><b>Blocked</b></td>
                            <td>
                                <a class="btn btn-success"
                                   href="${pageContext.request.contextPath}/admin/block?user_id=${user.id}&active=true">Unblock</a>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </form>
            </tr>
        </c:if>

    </c:forEach>
    </tbody>
</table>
<ul class="pager">
    <li class="previous"><a href="${pageContext.request.contextPath}/admin/users?page=${page-1}">Previous</a>
    </li>
    <li class="next"><a href="${pageContext.request.contextPath}/admin/users?page=${page+1}">Next</a></li>
</ul>
</body>
</html>
