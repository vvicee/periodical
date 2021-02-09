<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<header>
    <%@include file="WEB-INF/jspf/header.jspf" %>
</header>
<body>
<div class="container mt-5">
    <div class="alert alert-info mt-2">
        <h1>${user.name}</h1>
        <h1>${user.surname}</h1>
        <span>${user.email}</span>
        <h2>${user.balance}</h2>
        <!-- Button trigger modal -->
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
            Top up balance!
        </button>

        <form method="post" action="/profile/balance">
            <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
                 aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">Balance</h5>
                        </div>
                        <div class="modal-body">
                            Credit card number: <input name="numberCard" type="number" size="10"><br><br>
                            CVV: <input name="cvv" type="number" size="3"><br><br>
                            Amount of money: <input name="money" type="number"><br><br>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <input type="submit" class="btn btn-primary" value="Top up balance">
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <a href="${pageContext.request.contextPath}/profile/edit" class="btn btn-warning ml-5"> Edit</a>
    <form action="${pageContext.request.contextPath}/profile" method="post">
        <button class="btn btn-warning ml-5" type="submit"> Delete</button>
    </form>

<h3>Subscription</h3>
    <div class="card-deck">
        <c:forEach items="${edition}" var="ed">
            <div class="card">
                <img class="card-img-top" src="${pageContext.request.contextPath}/images/books.png" style="height: 150px; width: 100px" alt="Card image cap">
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
</div>
</body>
</html>
