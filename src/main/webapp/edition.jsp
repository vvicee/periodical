<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edition</title>

</head>
<header>
    <%@ include file="/WEB-INF/jspf/header.jspf" %>
</header>
<body>
<div class="container mt-5" style="width: 1200px">
    <div class="alert alert-info mt-2">
        <h1>${edition.title}</h1>
        <h1>${edition.publisher}</h1>
        <span>${edition.description}</span>
        <h2>${edition.price}</h2>
        <h2>${edition.theme}</h2>
        <h2>${edition.category}</h2>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
            Subscribe
        </button>

        <form method="post" action="/edition/subscribe">
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <input hidden name="edit_id" value="${edition.id}">
                            <h5 class="modal-title" id="exampleModalLongTitle">Subscription</h5>
                        </div>
                        <div class="modal-body">
                            <h4>Choose months of subscription</h4>
                            <c:forEach items="${months}" var="m">
                                <li>
                                    <input name="months" type="checkbox" value="${m}"> ${m}
                                </li>
                            </c:forEach>

                            <input type="button" value="Get price" onclick="getPrice();"><br>
                            <div id="result"></div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" style="width: 100px" class="btn btn-secondary" data-dismiss="modal">
                                Close
                            </button>
                            <input type="submit" class="btn btn-primary" value="Subscribe">
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <a href="${pageContext.request.contextPath}/admin/edit-edition?edition_id=${edition.id}" class="btn btn-warning ml-5">
        Edit </a>
    <a href="${pageContext.request.contextPath}/admin/delete-edition?edition_id=${edition.id}" class="btn btn-warning ml-5">
        Delete </a>
</div>
<script>
    function getPrice() {
        var userDateEntry = new Date(document.getElementById('dateTo').value);
        const today = new Date();
        var todayMonth = today.getMonth();
        var testMonth = userDateEntry.getMonth();
        var todayYear = today.getFullYear();
        var testYear = userDateEntry.getFullYear();
        const diffOfMonths = testMonth - todayMonth + (testYear - todayYear) * 12;
        const price = document.getElementById('price');
        const result = diffOfMonths * price;

        document.getElementById('result').innerHTML = "<h1>" + result + "</h1>";
    }
</script>

</body>
</html>

