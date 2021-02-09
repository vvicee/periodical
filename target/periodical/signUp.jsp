<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Registration</title>
</head>
<header>
    <%@include file="WEB-INF/jspf/header.jspf"%>
</header>
<body class="container">

<h2>Регистрация</h2>
<form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/signUp" >
    <div class="form-group">
        <label class="control-label col-xs-3" for="lastName">Фамилия:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" name="surname" id="lastName" placeholder="Введите фамилию">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="firstName">Имя:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" name="name" id="firstName" placeholder="Введите имя">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="inputEmail">Email:</label>
        <div class="col-xs-9">
            <input type="email" class="form-control" name="email" id="inputEmail" placeholder="Email">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="inputPassword">Пароль:</label>
        <div class="col-xs-9">
            <input type="password" class="form-control" name="password" id="inputPassword" placeholder="Введите пароль">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3">Язык интерфейса:</label>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="language" value="eng"> English
            </label>
        </div>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="language" value="ru"> Русский
            </label>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3">Подписаться на рассылку :</label>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="notify" value="Yes" checked> Да
            </label>
        </div>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="notify" value="No"> Нет <br>
            </label>
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-9">
            <input type="submit" class="btn btn-primary" value="Регистрация">
            <input type="reset" class="btn btn-default" value="Очистить форму">
            <a href="${pageContext.request.contextPath}/home" class="btn-dark">Вернуться на главную</a>
        </div>
    </div>
</form>
</body>
</html>
