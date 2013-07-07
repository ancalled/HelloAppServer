<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
    <title>Проверка кода</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>
    <!-- Bootstrap -->
    <link href="/helloapp/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<c:import url="header.jsp">
    <c:param name="current" value="check-sign"/>
</c:import>

<div class="container">

    <div class="row">
        <h2>Проверка кода скидки</h2>

        <div class="span4">

            <c:choose>
                <c:when test="${param.checked eq 'yes'}">
                    <h4>Код подтвержен</h4>
                </c:when>
                <c:otherwise>
                    <c:choose>
                        <c:when test="${param.er eq 'wrong-hash'}">
                            <h4>Неверный код</h4>

                        </c:when>
                        <c:when test="${param.er eq 'wrong-user'}">

                            <h4>Пользователь не найден</h4>

                        </c:when>
                        <c:when test="${param.er eq 'wrong-campaign'}">

                            <h4>Кампания не найдена</h4>

                        </c:when>
                        <c:otherwise>

                            <form action="/helloapp/partner/action/check-sign" method="post">

                                <div class="control-group info">
                                    <label class="control-label" for="user">Пользователь</label>

                                    <div class="controls">
                                        <input name="user" type="text" id="user">
                                        <span class="help-inline"></span>
                                    </div>
                                </div>

                                <div class="control-group info">
                                    <label class="control-label" for="campaign">Кампания</label>

                                    <div class="controls">
                                        <select id="campaign" name="camp">
                                            <c:forEach items="${campaigns}" var="c">
                                                <option value="${c.id}">${c.title}</option>
                                            </c:forEach>
                                        </select>

                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group info">

                                    <label class="control-label" for="price">Цена</label>

                                    <div class="controls">
                                        <input name="prc" type="text" id="price">
                                        <span class="help-inline"></span>
                                    </div>
                                </div>
                                <div class="control-group info">
                                    <label class="control-label" for="sign-code">Код</label>

                                    <div class="controls">
                                        <input name="code" type="text" id="sign-code">
                                        <span class="help-inline"></span>
                                    </div>
                                </div>


                                <div class="form-actions">
                                    <button type="submit" class="btn btn-primary">Проверить</button>
                                </div>

                            </form>

                        </c:otherwise>
                    </c:choose>
                </c:otherwise>
            </c:choose>


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