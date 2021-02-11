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
<div class="container mt-5">
    <div class="alert alert-info mt-2">
        <h1>${user.name}</h1>
        <h1>${user.surname}</h1>
        <span>${user.email}</span>
        <h2>${user.balance}</h2>
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
            <fmt:message key="profile.balance_"/>
        </button>

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
                            <fmt:message key="profile.credit"/>: <input name="numberCard" type="number" size="10"><br><br>
                            CVV: <input name="cvv" type="number" size="3"><br><br>
                            <fmt:message key="profile.money"/>: <input name="money" type="number"><br><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="profile.close"/></button>
                            <input type="submit" class="btn btn-primary" value="<fmt:message key="profile.balance_"/>">
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <a href="${pageContext.request.contextPath}/profile/edit" class="btn btn-warning ml-5"> <fmt:message key="edition.edit"/></a>
    <form action="${pageContext.request.contextPath}/profile" method="post">
        <button class="btn btn-warning ml-5" type="submit"> <fmt:message key="edition.delete"/></button>
    </form>

<h3><fmt:message key="edition.subscription"/></h3>
    <div class="card-deck">
        <c:forEach items="${edition}" var="ed">
            <div class="card">
                <img class="card-img-top" src="${pageContext.request.contextPath}/images/books.png" style="height: 150px; width: 100px" alt="Card image cap">
                <div class="card-block">
                    <h4 class="card-title">${ed.title}</h4>
                    <p class="card-text"> ${ed.description} </p>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">${ed.price}</li>
                        <li class="list-group-item">${ed.theme}</li>
                    </ul>
                </div>
                <div class="card-footer">
                    <a href="${pageContext.request.contextPath}/edition?edition_id=${ed.id}" class="card-link"><fmt:message key="edition.details"/></a>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
