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

<#if model.subjects??>

<div class="container" style="padding-top: 60px">
    <h2>Предметы текущей сессии</h2>
    <#list model.subjects?chunk(3) as subjectGroup>
        <div class="row">
            <#list subjectGroup as subject>
                <div class="col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <h4><strong>${subject.name}</strong></h4>
                            <a href="/user/manuals/${subject.id}" class="btn btn-lg">
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
    <h1>Вам повезло - Вам не нужны материалы. Видимо, Вы не сдаете сессию.</h1>
</#if>
</body>
</html>