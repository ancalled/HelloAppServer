<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
    <title>Партнеры</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/helloapp/js/bootstrap.min.js"></script>

    <!-- Bootstrap -->
    <link href="/helloapp/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<c:import url="header.jsp">
    <c:param name="current" value="reports"/>
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
                <c:forEach items="${partners}" var="p" varStatus="loop">
                    <tr>
                        <td>${loop.index + 1}</td>
                        <td>${p.name}</td>
                        <td>
                            <i id="${p.id}" class="icon-trash"></i>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>


    <c:import url="messanger.jsp" >
        <c:param name="mess" value="${param.mess}" />
        <c:param name="mess" value="${param.partner}" />
    </c:import>

    <hr/>

    <footer>
        <p>© Hello.kz 2013</p>
    </footer>
</div>

<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">Удаление партнерской компании</h3>
    </div>
    <div class="modal-body">
        <p>Вы действительно хотите удалить партнерскую компанию ?</p>
    </div>
    <div class="modal-footer">
        <button class="btn" data-dismiss="modal">Нет</button>
        <button class="btn btn-primary" id="remove-btn" aria-hidden="true">Да</button>
    </div>
</div>

<form id="partner-remove" action="/helloapp/admin/action/remove-partner" method="post">
   <input type="hidden" id="partner" name="partner" >
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
            $('#partner').val(this.id);
            $('#remove-btn').click(function () {
                document.getElementById("partner-remove").submit();
            });
            $('#myModal').modal('show');

        });
    }

</script>
</body>
</html>