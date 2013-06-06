<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>



<!DOCTYPE html>
<html>
<head>
    <title>Новая кампания</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'>
    <!-- Bootstrap -->
    <link href="/helloapp/css/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>

<c:import url="header.jsp" >
    <c:param name="current" value="new-campaign" />
</c:import>

<div class="container">

    <div class="row">
        <h2>Новая компания</h2>

        <div class="span10">

            <form action="/helloapp/partner/action/add-campaign" method="post"enctype="multipart/form-data">

                <div class="control-group info">
                    <label class="control-label" for="title">Название</label>
                    <div class="controls">
                        <input name="title" type="text" id="title">
                        <span class="help-inline"></span>
                    </div>
                </div>
                <div class="control-group info">

                    <label class="control-label" for="descr">Описание</label>
                    <div class="controls">
                        <textarea id="descr" name="descr" rows="3"></textarea>
                        <%--<input name="descr" type="text" id="descr">--%>
                        <%--<span class="help-inline"></span>--%>
                    </div>
                </div>
                <div class="control-group info">
                    <label class="control-label" for="rate">Скидка в %</label>
                    <div class="controls">
                        <input name="rate" type="text" id="rate">
                        <span class="help-inline"></span>
                    </div>
                </div>
                <div class="control-group info">
                    <label class="control-label" for="icon" >Изображение</label>

                    <div class="controls">
                       <span>
                           <input name="icon" type="file" id="icon" onchange="handleFiles(this.files)">
                        <div id="preview"></div>
                           </span>
                        <span class="help-inline"></span>
                    </div>
                </div>
                <c:import url="messanger.jsp" >
                    <c:param name="mess" value="${param.mess}" />
                </c:import>
                <div class="control-group info">
                    <label class="control-label" for="from">Начало кампании</label>
                    <div class="controls">
                        <input name="from" type="text" id="from">
                        <span class="help-inline"></span>
                    </div>
                </div>

                <div class="control-group info">
                    <label class="control-label" for="to">Окночание</label>
                    <div class="controls">
                        <input name="to" type="text" id="to">
                        <span class="help-inline"></span>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Создать</button>
                    <button type="button" class="btn">Отмена</button>
                </div>

            </form>


        </div>
    </div>

    <hr/>

    <footer>
        <p>© Hello.kz 2013</p>
    </footer>
</div>


<script>
    function handleFiles(files) {
        for (var i = 0; i < files.length; i++) {
            var file = files[i];
            var imageType = /image.*/;

//            if (!file.type.match(imageType)) {
//                continue;
//            }
            var img = document.createElement("img");
            img.classList.add("obj");
            img.file = file;

            $('#preview').html(img);

            var reader = new FileReader();
            reader.onload = (function(aImg) { return function(e) { aImg.src = e.target.result; }; })(img);
            reader.readAsDataURL(file);
        }
    }
</script>

<script src="http://code.jquery.com/jquery.js"></script>
<script src="/helloapp/js/bootstrap.min.js"></script>
</body>
</html>