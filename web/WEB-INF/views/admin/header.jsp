<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="brand" href="./index.html">Скидкоклуб</a>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="${param.current == 'partners' ? 'active' : ''}">
                        <a href="./partners">Партнеры</a>
                    </li>
                    <li class="${param.current == 'campaigns' ? 'active' : ''}">
                        <a href="./campaigns">Кампании</a>
                    </li>
                    <li class="${param.current == 'reports' ? 'active' : ''}">
                        <a href="./reports">Отчеты</a>
                    </li>
                    <li class="">
                        <a href="/helloapp/logout">Выход</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div style="margin-top: 75px">
</div>