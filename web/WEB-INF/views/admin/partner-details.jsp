<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Компания партнер</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/helloapp/js/bootstrap.min.js"></script>

    <!-- Bootstrap -->
    <link href="/helloapp/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<c:import url="header.jsp">
    <c:param name="current" value="partners"/>
</c:import>
<div class="tabbable tabs-left">
    <ul class="nav nav-tabs">
        <li class="active"><a href="#users-tab" data-toggle="tab">Пользователи</a></li>
        <li class=""><a href="#confirmers-tab" data-toggle="tab">Подтверждатели</a></li>
    </ul>
    <div class="tab-content">
        <div class="tab-pane active" id="users-tab">

            <h2>Пользователи</h2>

            <div class="span10">

                <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>E-mail</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="u" varStatus="loop">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${u.email}</td>
                            <td>
                                <i id="${u.id}" class="icon-trash"></i>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="tab-pane" id="confirmers-tab">
            <h2>Подтверждатели скидки</h2>

            <div class="span10">

                <table class="table table-condensed">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Имя</th>
                        <th>Код</th>
                        <th>QR</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${confirmers}" var="c">
                        <tr>
                            <td>${loop.index + 1}</td>
                            <td>${c.name}</td>
                            <td>${c.code}</td>
                            <td>
                                <button class="btn show-qr" id="${c.id}"><i class="icon-qrcode"></i>QR-код</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <div>
                <c:forEach items="${confirmers}" var="c">
                    <div id="qr-${c.id}" class="qr-preview">
                        <div>
                            <img src="/helloapp/partner/qrcode?cid=${c.id}" width="512" height="512" alt="${c.code}"/>
                        </div>
                        <div>
                                <%----%>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    (function($) {

        // DOM Ready
        $(function() {

            // Binding a click event
            // From jQuery v.1.7.0 use .on() instead of .bind()
            $('.show-qr').bind('click', function(e) {

                // Prevents the default action to be triggered.
                e.preventDefault();

                var currentId = $(this).attr('id');


                // Triggering bPopup when click event is fired
                $('#qr-'+currentId).bPopup();

            });

        });

    })(jQuery);
</script>


</body>
</html>