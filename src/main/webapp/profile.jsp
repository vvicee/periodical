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
    <title><fmt:message key="profile.title"/></title>
</head>
<header>
    <%@include file="WEB-INF/jspf/header.jspf" %>
</header>
<body>
<div class="container">
    <h3>Personal info</h3>

    <div class="row">
<%--        <div class="col col-md-2">--%>
<%--            <form action="${pageContext.request.contextPath}/profile" method="post">--%>
<%--                <button class="btn btn-danger" type="submit"><fmt:message key="edition.delete"/></button>--%>
<%--            </form>--%>
<%--        </div>--%>
        <div class="col col-md-6">
            <a href="${pageContext.request.contextPath}/profile/edit" style="width: 200px" class="btn btn-success"> <fmt:message
                    key="edition.edit"/></a>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <b><h4><fmt:message key="user.name"/></h4></b>
        </div>
        <div class="col-md-6">
            <h4>${user.name}</h4>
        </div>
    </div>

    <div class="row">
        <div class="col-md-4">
            <b><h4><fmt:message key="user.surname"/></h4></b>
        </div>
        <div class="col-md-6">
            <h4>${user.surname}</h4>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4">
            <b><h4><fmt:message key="admin.Email"/></h4></b>
        </div>
        <div class="col-md-6">
            <h4>${user.email}</h4>
        </div>
    </div>
    <hr>
    <h3>Account info</h3>
    <div class="row">
        <div class="col-md-3">
            <b><h4><fmt:message key="profile.balance"/></h4></b>
        </div>
        <div class="col-md-3">
            <h4 style="color: red">${user.balance}</h4>
        </div>
        <div class="col-md-4">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
                <fmt:message key="profile.balance_"/>
            </button>
        </div>
    </div>
    <hr>
    <c:if test="${requestScope.subscriptions!=null}">
        <h3><fmt:message key="edition.subscription"/></h3>
        <table class="table table-striped">
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Edition</th>
                <th scope="col">Year</th>
                <th scope="col">Months</th>
<%--                <th scope="col">Status</th>--%>
                <th scope="col">Price</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="sub" items="${subscriptions}">
                <tr>
                    <td>${sub.id}</td>
                    <td><a href="/edition?edition_id=${sub.edition.id}">${sub.edition.title}</a></td>
                    <td>${sub.year}</td>
                    <td>${sub.getMonthsAsString()}</td>
<%--                    <td>${sub}</td>--%>
                    <td style="color: red">${sub.getPrice()}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

    </c:if>

</div>
<form method="post" action="${pageContext.request.contextPath}/profile/balance">
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="profile.balance"/>
                    </h5>
                </div>
                <div class="modal-body">
                    <fmt:message key="profile.credit"/>: <input name="numberCard" type="number"
                                                                size="10"><br><br>
                    CVV: <input name="cvv" type="number" size="3"><br><br>
                    <fmt:message key="profile.money"/>: <input name="money" type="number" min="0"><br><br>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message
                            key="profile.close"/></button>
                    <input type="submit" class="btn btn-primary"
                           value="<fmt:message key="profile.balance_"/>">
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
