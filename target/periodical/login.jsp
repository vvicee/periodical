<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<header>
    <%@include file="WEB-INF/jspf/header.jspf" %>
</header>
<body class="container">

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6" style=" border: 4px double black; padding: 15px">
            <form class="form-horizontal" method="post" action="${pageContext.request.contextPath}/login">
                <span class="heading" style="text-align: center">АВТОРИЗАЦИЯ</span> <br><br>
                <div class="form-group">
                    <input type="email" class="form-control" name="email" id="inputEmail" placeholder="E-mail">
                    <i class="fa fa-user"></i>
                </div>
                <div class="form-group help">
                    <input type="password" class="form-control" name="password" id="inputPassword"
                           placeholder="Password">
                    <i class="fa fa-lock"></i>
                    <a href="#" class="fa fa-question-circle"></a>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-3 col-xs-9">
                        <input type="submit" class="btn btn-primary" value="Вход">
                        <input type="reset" class="btn btn-default" value="Очистить форму">
                        <a href="${pageContext.request.contextPath}/home" class="btn-dark">Вернуться на главную</a>
                    </div>
                </div>
            </form>
        </div>
    </div><!-- /.row -->
</div><!-- /.container -->
</body>
</html>
