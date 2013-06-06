<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<html>
<head>
    <title>Новая компания партнер</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>
    <!-- Bootstrap -->
    <link href="/helloapp/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<c:import url="header.jsp" >
    <c:param name="current" value="add-partner" />
</c:import>

<div class="container">

    <div class="row">
        <h2>Новая компания партнер</h2>

        <div class="span10">

            <form action="/helloapp/admin/action/add-partner" method="post">

                <div class="control-group info">
                    <label class="control-label" for="title">Название</label>
                    <div class="controls">
                        <input name="title" type="text" id="title">
                        <span class="help-inline"></span>
                    </div>
                </div>
                <button type="submit" class="btn btn-primary">Создать</button>
            </form>


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