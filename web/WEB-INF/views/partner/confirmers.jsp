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
    <style type="text/css">
        .qr-preview {
            display: none;
        }
    </style>
</head>
<body>

<c:import url="header.jsp" >
    <c:param name="current" value="confirmers" />
</c:import>

<div class="container">

    <div class="row">
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

                    </div>
                </div>
            </c:forEach>
        </div>
    </div>

    <hr/>

    <footer>
        <p>© Hello.kz 2013</p>
    </footer>
</div>


<script src="http://code.jquery.com/jquery.js"></script>
<script src="/helloapp/js/bootstrap.min.js"></script>
<script src="/helloapp/js/jquery.bpopup.min.js"></script>
<script type="text/javascript">
    ;(function($) {

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