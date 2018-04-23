<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    <link href="/css/teacher-list-page.css" rel="stylesheet" type="text/css">
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
            <ul class="nav navbar-nav navbar-left">
                <li><a href="/user/profile"><span class="glyphicon glyphicon-home"></span> на главную</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> выйти</a></li>
            </ul>
        </div>
    </div>
</nav>

<#if model.teachers??>

<div class="container" style="padding-top: 60px">
    <h2>преподавательский состав на данную сессию</h2>
    <#list model.teachers?chunk(3) as teacherGroup>
        <div class="row">
            <#list teacherGroup as teacher>
                <div class="col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <img src="/file/${teacher.photo.fileInfo.id}" class="img-thumbnail img-responsive" alt="Your Teacher is Watching You!" width="200" height="300">
                            <h3 style="font-weight: bold">${teacher.name}</h3>
                            <a href="/user/teachers/${teacher.id}" class="btn btn-lg">
                                <span class="glyphicon glyphicon-chevron-down"></span>
                            </a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>
        <hr>
    </#list>
</div>

<#else>
    <h1>Вам повезло и никто из преподавателей не будет принимать у Вас экзамен</h1>
</#if>
</body>
</html>