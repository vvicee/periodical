<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<header>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
</header>
<body class="container" style="width: 1200px">
<div class="row">

    <form action="${pageContext.request.contextPath}/filter" class="col-md-8">
        <div class="row">
            <div class="col-md-4">
                <h4>Categories</h4>
                <div id="categoryId">
                    <div class="checkbox"><label><input class="check" type="checkbox" name="category"
                                                        value="newspaper">
                        newspaper</label></div>
                    <div class="checkbox"><label><input class="check" type="checkbox" name="category"
                                                        value="magazine">
                        magazine</label></div>
                    <div class="checkbox"><label><input class="check" type="checkbox" name="category"
                                                        value="calendar">
                        calendar</label></div>
                    <div class="checkbox"><label><input class="check" type="checkbox" name="category"
                                                        value="yearbook">
                        yearbook</label></div>
                    <div class="checkbox"><label><input type="checkbox" name="category" value="" hidden checked>
                    </label></div>
                </div>
            </div>

            <div class="col-md-4">
                <h4>Themes</h4>
                <div id="themeId">
                    <div class="checkbox"><label><input class="check" type="checkbox" name="theme" value="garden">
                        garden</label></div>
                    <div class="checkbox"><label><input class="check" type="checkbox" name="theme" value="economy">
                        economy</label></div>
                    <div class="checkbox"><label><input class="check" type="checkbox" name="theme"
                                                        value="education">
                        education</label></div>
                    <div class="checkbox"><label><input class="check" type="checkbox" name="theme" value="sport">
                        sport</label>
                    </div>
                    <div class="checkbox"><label><input hidden checked type="checkbox" name="theme" value="">
                    </label>
                    </div>
                </div>
            </div>
        </div>
        <div class="row" >
            <input type="submit" value="Filter">
        </div>
    </form>

    <div class="col-md-4">
        <h4>Сортировка</h4>
        <br>
        <form action="/sort" method="get">
            <select name="comparedParameter" class="form-control" id="sortID"
                    onchange='submitFormSort(), this.form.submit()'>
                <option disabled selected>Choose sorting...</option>
                <option value="price_asc">По цене, сначала дешевые</option>
                <option value="price_desc">По цене, сначала дорогие</option>
                <option value="by title from A-Z">По названию, A-Z</option>
                <option value="by title from Z-A">По названию, Z-A</option>
                <br>
            </select>
        </form>
    </div>
</div>

<div style="display: flex; clear: both">
    <c:forEach items="${editions}" var="ed">
        <div class="card" style="padding: 10px; display: flow">
            <img class="card-img-top" src="${pageContext.request.contextPath}/images/books.png"
                 style="height: 150px; width: 150px" alt="Card image cap">
            <div class="card-block">
                <h4 class="card-title">${ed.title}</h4>
                <p class="card-text"> ${ed.description} </p>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">${ed.price}</li>
                    <li class="list-group-item">${ed.theme}</li>
                </ul>
            </div>
            <div class="card-footer">
                <a href="/edition?edition_id=${ed.id}" class="card-link">Details</a>
            </div>
        </div>
    </c:forEach>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var itemSort = localStorage.getItem('sortId');
        var selectSort = document.getElementById("sortID");
        selectSort.value = itemSort;
    });

    function submitFormSort() {
        var select = document.getElementById("sortID");
        var value = select.options[select.selectedIndex].value;
        localStorage.setItem('sortId', value);
    }

    document.querySelectorAll(".check").forEach(el => {
        el.onchange = () => localStorage.setItem(el.value, el.checked);
        el.checked = localStorage.getItem(el.value) === "true";
    })

</script>
</body>
</html>
