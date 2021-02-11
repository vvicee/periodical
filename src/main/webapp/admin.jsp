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
    <title><fmt:message key="admin.title"/> </title>
</head>
<header>
    <%@include file="WEB-INF/jspf/header.jspf" %>
</header>
<body class="container">
<div class="admin-container">
    <div style="overflow: auto;">
        <div class="tabs">
            <input type="radio" name="inset" value="" id="tab_1" checked>
            <label for="tab_1"><fmt:message key="admin.active_users"/></label>

            <input type="radio" name="inset" value="" id="tab_2">
            <label for="tab_2"><fmt:message key="admin.add_edition"/></label>

            <input type="radio" name="inset" value="" id="tab_3">
            <label for="tab_3"><fmt:message key="admin.blocked_users"/></label>

            <div id="txt_1">
                <c:forEach var="user" items="${activeUsers}">
                    <button class="accordion">${user.name} ${user.surname}</button>
                    <div class="panel">
                        <img src="images/cont.png" srcset="images/cont.png 1.5x"
                             style="width: 150px; height: 100px;float: left;">
                        <input name="id" hidden value="${user.id}">
                        <i><fmt:message key="admin.Email"/>: </i>${user.email}<br>
                        <p> <fmt:message key="admin.Role"/>: ${user.role}</p>
                        <form method="post" action="${pageContext.request.contextPath}/admin/block?user_id=${user.id}&active=false">
                            <input type="submit" value="<fmt:message key="admin.block"/> ">
                        </form>
                    </div>
                </c:forEach>
            </div>

            <div id="txt_2">
                <form style="text-align: left;" method="post" action="${pageContext.request.contextPath}/admin/add-edition">
                    <img src="images/news.jpg" style="width: 350px; height: 300px;float: right;">

                    <fmt:message key="admin.edition_title"/>:
                    <input class="input-field" name="title" type="text" placeholder=""><br><br>
                    <fmt:message key="admin.publisher"/>:
                    <input class="input-field" name="publisher" type="text" placeholder=""><br><br>
                    <fmt:message key="admin.description"/>:
                    <input class="input-field" name="description" type="text" placeholder=""><br><br>
                    <fmt:message key="admin.price"/>:
                    <input class="input-field" name="price" type="text" placeholder=""><br><br>
                    <fmt:message key="admin.theme"/>:<br>
                    <input name="theme" type="radio" value="education" checked> <fmt:message key="edition.theme.education"/>
                    <input name="theme" type="radio" value="health">  <fmt:message key="edition.theme.health"/>
                    <input name="theme" type="radio" value="sport">  <fmt:message key="edition.theme.sport"/>
                    <input name="theme" type="radio" value="fashion"> <fmt:message key="edition.theme.fashion"/>
                    <input name="theme" type="radio" value="garden"> <fmt:message key="edition.theme.garden"/>
                    <input name="theme" type="radio" value="economy"> <fmt:message key="edition.theme.economy"/><br><br>
                    <fmt:message key="admin.category"/>: <br>
                    <input name="category" type="radio" value="newspaper" checked> <fmt:message key="edition.category.newspaper"/>
                    <input name="category" type="radio" value="magazine"> <fmt:message key="edition.category.magazine"/>
                    <input name="category" type="radio" value="calendar"> <fmt:message key="edition.category.calendar"/>
                    <input name="category" type="radio" value="yearbook"> <fmt:message key="edition.category.yearbook"/>
                    <br><br>
                    <button class="button" style="width: 250px; background-color: #9999e8;"><fmt:message key="admin.add_edition"/> </button>
                </form>
            </div>

            <div id="txt_3">
                <c:forEach var="user" items="${blockedUsers}">
                    <button class="accordion">${user.name} ${user.surname}</button>
                    <div class="panel">
                        <img src="images/cont.png" srcset="images/cont.png 1.5x"
                             style="width: 150px; height: 100px;float: left;">
                        <input name="user_id" hidden value="${user.id}">
                        <i><fmt:message key="admin.Email"/>: </i>${user.email}<br>
                        <p> <fmt:message key="admin.Role"/>: ${user.role}</p>
                        <form method="post" action="${pageContext.request.contextPath}/admin/block?user_id=${user.id}&active=true">
                            <input type="submit" value="<fmt:message key="admin.unblock"/> ">
                        </form>
                    </div>
                </c:forEach>
            </div>

        </div>

    </div>
</div>
<script type="text/javascript" src="/js/accordion.js" async></script>
</body>
</html>
