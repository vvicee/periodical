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
    <title><fmt:message key="index.title"/></title>
</head>
<header>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
</header>
<body >
<div class="container text-center">
    <div class="row">
        <div class="col-sm-3 well" style="background: white; position: sticky; top: 0">
            <div class="well">
                <p>
                <h4 style="text-align: center"><fmt:message key="index.sorting"/></h4>
                <form action="${pageContext.request.contextPath}/sort" method="get">

                    <select name="comparedParameter" class="form-control" id="sortID"
                            onchange='submitFormSort(), this.form.submit()'>
                        <option value="price_asc"><fmt:message key="index.sort.price_asc"/></option>
                        <option value="price_desc"><fmt:message key="index.sort.price_desc"/></option>
                        <option value="by title from A-Z"><fmt:message key="index.sort.title_A-Z"/></option>
                        <option value="by title from Z-A"><fmt:message key="index.sort.title_Z-A"/></option>
                        <br>
                    </select>
                </form>
                </p>
            </div>
            <form action="/filter" method="get">
                <div class="well">
                    <p>
                    <h4><fmt:message key="index.categories"/></h4>
                    <div id="categoryId">
                        <div class="checkbox"><label><input class="check" type="checkbox" name="category"
                                                            value="newspaper">
                            <fmt:message key="edition.category.newspaper"/> </label></div>
                        <div class="checkbox"><label><input class="check" type="checkbox" name="category"
                                                            value="magazine">
                            <fmt:message key="edition.category.magazine"/></label></div>
                        <div class="checkbox"><label><input class="check" type="checkbox" name="category"
                                                            value="calendar">
                            <fmt:message key="edition.category.calendar"/></label></div>
                        <div class="checkbox"><label><input class="check" type="checkbox" name="category"
                                                            value=" yearbook">
                            <fmt:message key="edition.category.yearbook"/></label></div>
                        <div class="checkbox"><label><input type="checkbox" name="category" value="" hidden checked>
                        </label></div>
                    </div>

                    <h4 style="text-align: center"><fmt:message key="index.themes"/></h4>
                    <div id="themeId">
                        <div class="col-md-8">
                            <div class="checkbox"><label><input class="check" type="checkbox" name="theme"
                                                                value="garden">
                                <fmt:message key="edition.theme.garden"/></label>
                            </div>

                            <div class="checkbox"><label><input class="check" type="checkbox" name="theme"
                                                                value="economy">
                                <fmt:message key="edition.theme.economy"/></label>
                            </div>
                            <div class="checkbox"><label><input class="check" type="checkbox" name="theme"
                                                                value="education">
                                <fmt:message key="edition.theme.education"/></label>
                            </div>
                            <div class="checkbox"><label><input class="check" type="checkbox" name="theme"
                                                                value="fashion">
                                <fmt:message key="edition.theme.fashion"/></label>
                            </div>
                        </div>

                        <div class="col-md-2">
                            <div class="checkbox"><label><input class="check" type="checkbox" name="theme"
                                                                value="health">
                                <fmt:message key="edition.theme.health"/></label>
                            </div>
                            <div class="checkbox"><label><input class="check" type="checkbox" name="theme"
                                                                value="science">
                                <fmt:message key="edition.theme.science"/></label>
                            </div>
                            <div class="checkbox"><label><input class="check" type="checkbox" name="theme"
                                                                value="sport">
                                <fmt:message key="edition.theme.sport"/></label>
                            </div>
                            <div class="checkbox">
                                <label><input hidden checked type="checkbox" name="theme" value=""> </label>
                            </div>
                        </div>
                    </div>

                    <input type="submit" value="<fmt:message key="index.filter"/>">
                </div>
            </form>
        </div>

        <div class="col-sm-9">
            <c:forEach items="${editions}" var="edition">
                <div class="row">
                    <div class="col-sm-3">
                        <div class="well">
                            <p><a href="${pageContext.request.contextPath}/edition?edition_id=${edition.id}">
                                    ${edition.title}</a></p>
                            <img src="${pageContext.request.contextPath}/images/books.png" class="img-circle"
                                 height="55"
                                 width="55" alt="Avatar">
                        </div>
                    </div>
                    <div class="col-sm-9">
                        <div class="well">
                            <p>${edition.description}</p>
                            <p style="text-align: left">
                                <fmt:message key="admin.price"/>: <b style="color: red">${edition.price}</b><br>
                                <fmt:message key="admin.theme"/>: ${edition.theme}
                            </p>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

    </div>
</div>


<footer class="container-fluid text-center">
    <p>Periodical</p>
</footer>
</body>
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

