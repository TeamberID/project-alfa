<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    <link href="/css/session-page.css" rel="stylesheet" type="text/css">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid" style="color: #9acfea">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> выйти</a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container" style="padding-top: 60px">
    <div class="row">
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h4><strong>сессия</strong></h4>
                    <h5>подготовьтесь к ближайшей сессии с помощью материалов прошлых годов</h5>
                    <a href="/user/session/" class="btn btn-default" role="button">к сессии</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h4><strong>материалы</strong></h4>
                    <h5>воспользуйтесь учебными материалами, которые мы собрали для вас</h5>
                    <a href="" class="btn btn-default" role="button">к материалам</a>
                </div>
            </div>
        </div>
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-body">
                    <h4><strong>преподаватели</strong></h4>
                    <h5>изучите отзывы студентов о преподавательском составе</h5>
                    <a href="/user/teachers/" class="btn btn-default" role="button">к преподавателям</a>
                </div>
            </div>
        </div>
    </div>
    <hr>
</div>
</body>
</html>