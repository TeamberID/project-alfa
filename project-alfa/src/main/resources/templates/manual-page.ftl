<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    <link href="/css/manual-page.css" rel="stylesheet" type="text/css">
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
                <li><a href="/user/manuals/"><span class="glyphicon glyphicon-book"></span> к списку предметов</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> выйти</a></li>
            </ul>
        </div>
    </div>
</nav>

<#if model.subject??>

<div class="container" style="padding-top: 60px">
    <h2>${model.subject.name}</h2>
    <hr>
    <div>
        <#list model.subject.manuals as manual>
            <div class="row">
                <div class="col-md-5">
                    <p>${manual.title}</p>
                </div>
                <div class="col-md-5">
                    <p>${manual.author}</p>
                </div>
                <div class="col-md-2">
                    <a href="/file/${manual.fileInfo.id}" class="btn btn-info" role="button" download><span class="glyphicon glyphicon-download"></span></a>
                </div>
            </div>
        </#list>
    </div>
</div>

<#else>
    <h1 style="padding-top: 60px">Извините, материалы еще не завезли.</h1>
</#if>
</body>
</html>