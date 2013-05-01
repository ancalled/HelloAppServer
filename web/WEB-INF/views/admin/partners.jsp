<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<html>
<head>
    <title>Партнеры</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/helloapp/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<c:import url="header.jsp" >
    <c:param name="current" value="reports" />
</c:import>

<div class="container">

    <div class="row">

        <h2>Партнеры</h2>


        <div class="span10">

            <table class="table table-condensed">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Партнер</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${partners}" var="p">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${p.name}</td>
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