
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit profile</title>
</head>
<header>
    <%@include file="WEB-INF/jspf/header.jspf"%>
</header>
<body class="container" style="width: 1200px">
<form method="post" action="/profile/edit">
    <div class="alert alert-info mt-2">
        <input name="name" value="${user.name}" placeholder="">
        <input name="surname" value="${user.surname}" placeholder="">
        <input name="notify" type="radio" value="yes" checked> Yes
        <input name="notify" type="radio" value="no"> No
    </div>
    <input class="btn btn-warning ml-5" type="submit" value="Edit">
</form>
</body>
</html>
