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
    <title><fmt:message key="edition.title"/> </title>

</head>
<header>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
</header>
<body class="container mt-5">
<div>
    <div class="alert alert-info mt-2">
        <h1>${edition.title}</h1>
        <h1>${edition.publisher}</h1>
        <span>${edition.description}</span>
        <h2>${edition.price}</h2>
        <h2>${edition.theme}</h2>
        <h2>${edition.category}</h2>
        <input hidden name="edition_id" value="${edition.id}">

        <c:if test="${sessionScope.user.role == 'USER'}">
            <button type="button" class="btn btn-primary" style="width: 200px; text-align: center" data-toggle="modal"
                    data-target="#exampleModalCenter">
                <fmt:message key="edition.subscribe"/>
            </button>
        </c:if>


        <form method="post" action="${pageContext.request.contextPath}/edition/subscribe">
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <input hidden name="edit_id" value="${edition.id}">
                            <h5 class="modal-title" id="exampleModalLongTitle"><fmt:message key="edition.subscription"/> </h5>
                        </div>
                        <div class="modal-body">
                            <h4><fmt:message key="edition.choose_months"/> </h4>
                            <c:forEach items="${months}" var="m">
                                <li>
                                    <input name="months" type="checkbox" value="${m}"> ${m}
                                </li>
                            </c:forEach>
                        </div>
                        <div class="modal-footer">
                            <button type="button" style="width: 100px" class="btn btn-secondary" data-dismiss="modal">
                                Close
                            </button>
                            <input type="submit" class="btn btn-primary" value="<fmt:message key="edition.subscribe"/>">
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <c:if test="${sessionScope.user.role == 'ADMIN'}">
            <a href="${pageContext.request.contextPath}/admin/edit-edition?edition_id=${edition.id}"
               class="btn btn-warning ml-5">
                <fmt:message key="edition.edit"/> </a>
            <a href="${pageContext.request.contextPath}/admin/delete-edition?edition_id=${edition.id}"
               class="btn btn-warning ml-5">
                <fmt:message key="edition.delete"/> </a>
        </c:if>
    </div>
</div>

</body>
</html>

