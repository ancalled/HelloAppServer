<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<html>
<head>
    <title>Новая кампания</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/helloapp/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<c:import url="header.jsp" >
    <c:param name="current" value="new-campaign" />
</c:import>

<div class="container">

    <div class="row">
        <h2>Регистрация пользователя</h2>

        <div class="span6">

            <form action="/helloapp/customer/action/register" method="post">

                <div class="control-group info">
                    <label class="control-label" for="name">Имя</label>
                    <div class="controls">
                        <input name="name" type="text" id="name">
                        <span class="help-inline"></span>
                    </div>
                </div>
                <div class="control-group info">
                    <label class="control-label" for="email">e-mail</label>
                    <div class="controls">
                        <input name="email" type="text" id="email">
                        <span class="help-inline"></span>
                    </div>
                </div>
                <div class="control-group info">
                    <label class="control-label" for="pass">Пароль</label>
                    <div class="controls">
                        <input name="pass" type="password" id="pass">
                        <span class="help-inline"></span>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Зарегистрировать</button>
                </div>

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