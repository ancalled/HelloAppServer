<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<html>
<head>
    <title>Новая кампания</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/business/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<c:import url="header.jsp" >
    <c:param name="current" value="campaigns" />
</c:import>

<div class="container">

    <div class="row">
        <h2>Новая компания</h2>

        <div class="span10">


        </div>
    </div>

    <hr/>

    <footer>
        <p>© Hello.kz 2013</p>
    </footer>
</div>


<script src="http://code.jquery.com/jquery.js"></script>
<script src="/business/js/bootstrap.min.js"></script>
</body>
</html>