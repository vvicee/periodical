<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin</title>
</head>
<header>
    <%@include file="WEB-INF/jspf/header.jspf" %>
</header>
<body class="container">
<div class="admin-container">
    <div style="overflow: auto;">
        <div class="tabs">
            <input type="radio" name="inset" value="" id="tab_1" checked>
            <label for="tab_1">Active users</label>

            <input type="radio" name="inset" value="" id="tab_2">
            <label for="tab_2">Add edition</label>

            <input type="radio" name="inset" value="" id="tab_3">
            <label for="tab_3">Blocked users</label>

            <div id="txt_1">
                <c:forEach var="user" items="${activeUsers}">
                    <button class="accordion">${user.name} ${user.surname}</button>
                    <div class="panel">
                        <img src="images/cont.png" srcset="images/cont.png 1.5x"
                             style="width: 150px; height: 100px;float: left;">
                        <input name="id" hidden value="${user.id}">
                        <i> Email: </i>${user.email}<br>
                        <p> Role: ${user.role}</p>
                        <form method="post" action="/admin/block?user_id=${user.id}&active=false">
                            <input type="submit" value="Block">
                        </form>
                    </div>
                </c:forEach>
            </div>

            <div id="txt_2">
                <form style="text-align: left;" method="post" action="/admin/add-edition">
                    <img src="images/news.jpg" style="width: 350px; height: 300px;float: right;">

                    Title:
                    <input class="input-field" name="title" type="text" placeholder=""><br><br>
                    Publisher:
                    <input class="input-field" name="publisher" type="text" placeholder=""><br><br>
                    Description:
                    <input class="input-field" name="description" type="text" placeholder=""><br><br>
                    Price:
                    <input class="input-field" name="price" type="text" placeholder=""><br><br>
                    Theme:<br>
                    <input name="theme" type="radio" value="education" checked> Education
                    <input name="theme" type="radio" value="health"> Health
                    <input name="theme" type="radio" value="sport"> Sport
                    <input name="theme" type="radio" value="fashion"> Fashion
                    <input name="theme" type="radio" value="garden"> Garden
                    <input name="theme" type="radio" value="economy"> Economy<br><br>
                    Category<br>
                    <input name="category" type="radio" value="newspaper" checked> Newspaper
                    <input name="category" type="radio" value="magazine"> Magazine
                    <input name="category" type="radio" value="calendar"> Calendar
                    <input name="category" type="radio" value="yearbook"> Yearbook
                    <br><br>
                    <button class="button" style="width: 250px; background-color: #9999e8;">Add edition</button>
                </form>
            </div>

            <div id="txt_3">
                <c:forEach var="user" items="${blockedUsers}">
                    <button class="accordion">${user.name} ${user.surname}</button>
                    <div class="panel">
                        <img src="images/cont.png" srcset="images/cont.png 1.5x"
                             style="width: 150px; height: 100px;float: left;">
                        <input name="user_id" hidden value="${user.id}">
                        <i> Email: </i>${user.email}<br>
                        <p> Role: ${user.role}</p>
                        <form method="post" action="/admin/block?user_id=${user.id}&active=true">
                            <input type="submit" value="Unblock">
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
