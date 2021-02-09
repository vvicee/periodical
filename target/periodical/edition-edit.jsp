<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit edition</title>
</head>
<header>
    <%@ include file="WEB-INF/jspf/header.jspf" %>
</header>
<body class="container" style="width: 1200px">
<form method="post" action="${pageContext.request.contextPath}/admin/edit-edition">
    <div class="alert alert-info mt-2">
        <input name="edition_id" type="hidden" value="${edition.id}">
        <input name="title" value="${edition.title}" placeholder="">
        <input name="publisher" value="${edition.publisher}" placeholder="">
        <input name="description" value="${edition.description}" placeholder="">
        <input name="price" value="${edition.price}" placeholder="${edition.title}">
        <h2>Theme</h2>
        <input name="theme" type="radio" value="education" checked> Education
        <input name="theme" type="radio" value="health"> Health
        <input name="theme" type="radio" value="sport"> Sport
        <input name="theme" type="radio" value="fashion"> Fashion
        <input name="theme" type="radio" value="garden"> Garden
        <input name="theme" type="radio" value="economy"> Economy
        <h2>Category</h2>
        <input name="category" type="radio" value="newspaper" checked> Newspaper
        <input name="category" type="radio" value="magazine"> Magazine
        <input name="category" type="radio" value="calendar"> Calendar
        <input name="category" type="radio" value="yearbook"> Yearbook
    </div>
    <input class="btn btn-warning ml-5" type="submit" value="Edit">
</form>
</body>
</html>
