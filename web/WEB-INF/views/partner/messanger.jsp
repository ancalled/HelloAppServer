<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link href="/helloapp/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <script src="/helloapp/js/bootstrap.min.js"></script>
    <title></title>
</head>
<body>

<c:choose>
    <c:when test="${param.mess eq 'file-err'}">
        <c:set var="type" value="alert alert-success"/>
        <c:set var="messange" value="Файл иконки не был загружен"/>
    </c:when>
</c:choose>

<div class=${type}>${messange}</div>

</body>
</html>