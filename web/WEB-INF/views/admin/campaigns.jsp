<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
    <title>Кампании</title>
    <script src="http://code.jquery.com/jquery.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>
    <!-- Bootstrap -->
    <script src="/helloapp/js/bootstrap.js"></script>
    <link href="/helloapp/css/bootstrap.css" rel="stylesheet" media="screen">
</head>
<body>

<c:import url="header.jsp">
    <c:param name="current" value="campaigns"/>
</c:import>


<div class="tabbable tabs-left">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#campaigns-tab" data-toggle="tab">Акции</a></li>
        <li class=""><a href="#new-campaign-tab" data-toggle="tab">Новая акция </a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="campaigns-tab">
            <div class="container">

                <div class="row">
                    <h2>Компании</h2>

                    <div class="span11">

                        <table class="table table-condensed">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Кампания</th>
                                <th>Описание</th>
                                <th>Скидка</th>
                                <th>Начало</th>
                                <th>Окончание</th>
                                <th>Организация</th>
                                <th>Состояние</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${campaigns}" var="d" varStatus="loop">
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
                                            ${d.company.name}
                                    </td>
                                    <td>

                                    </td>
                                    <td>
                                        <i id="${d.id}" class="icon-trash"></i>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>


                <c:import url="messanger.jsp">
                    <c:param name="mess" value="${param.mess}"/>
                    <c:param name="mess" value="${param.camp}"/>
                </c:import>

                <hr/>

                <footer>
                    <p>© Hello.kz 2013</p>
                </footer>
            </div>
        </div>
        <div class="tab-pane" id="new-campaign-tab">
            Новая акция здесь
        </div>
    </div>

    <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="myModalLabel">Удаление акции</h3>
        </div>
        <div class="modal-body">
            <p>Вы действительно хотите удалить акцию ?</p>
        </div>
        <div class="modal-footer">
            <button class="btn" data-dismiss="modal">Нет</button>
            <button class="btn btn-primary" id="remove-btn" aria-hidden="true">Да</button>
        </div>
    </div>

    <form action="/helloapp/admin/action/remove-camp" id="campaign-remove" method="post">
        <input type="hidden" name="camp-id" id="campaign">
    </form>

    <script>
        var delButtons = document.getElementsByTagName('i');
        for (var i = 0; i < delButtons.length; ++i) {
            var but = delButtons[i];
            $(but).mouseenter(function () {
                $(this).css('opacity', '0.3');
            });
            $(but).mouseout(function () {
                $(this).css('opacity', '1');
            });
            $(but).click(function () {
                $('#campaign').val(this.id);
                $('#remove-btn').click(function () {
                    document.getElementById("campaign-remove").submit();
                });
                $('#myModal').modal('show');

            });
        }

    </script>

</body>
</html>