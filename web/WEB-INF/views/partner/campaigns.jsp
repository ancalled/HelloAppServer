<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<html>
<head>
    <title>Кампании</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/helloapp/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<c:import url="header.jsp" >
    <c:param name="current" value="campaigns" />
</c:import>

<div class="container">

    <div class="row">
        <h2>Кампании</h2>

        <div class="span10">

            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Кампания</th>
                    <th>Описание</th>
                    <th>Скидка %</th>
                    <th>Начало</th>
                    <th>Окончание</th>
                    <th>Состояние</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${campaigns}" var="d">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${d.title}</td>
                        <td>${d.description}</td>
                        <td>${d.rate}%</td>
                        <td>
                            <fmt:formatDate value="${d.startFrom}" pattern="yyyy-MMM-dd hh:mm"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${d.goodThrough}" pattern="yyyy-MMM-dd hh:mm"/>
                        </td>
                        <td>

                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <hr/>

    <footer>
        <p>© Hello.kz 2013</p>
    </footer>
</div>


<script src="http://code.jquery.com/jquery.js"></script>
<script src="/helloapp/js/bootstrap.min.js"></script>
</body>
</html>