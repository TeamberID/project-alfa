<head>
    <link href="/css/table-style.css" rel="stylesheet" type="text/css">
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/session-page.css" rel="stylesheet" type="text/css">
</head>
<body>


<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid" style="color: #9acfea">
        <div class="navbar-header">
            <div class="navbar-brand">
                <a href="/admin/institutes"><span class="glyphicon glyphicon-circle-arrow-left"></span></a>
            </div>

            <div class="navbar-brand">
            </div>

            <div class="navbar-brand">
                <a href="/admin">Admin mode</a>
            </div>
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

<br><br><br>

<div class="container">
    <div class="key-form">
        <h3>Добавление института</h3>

        <form method="post" action="/admin/institutes/add" class="">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <input class="form-control" name="name" id="name" placeholder="Название" type="text" required>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <select class="form-control" name="universityId" id="universityId" title="university">
                        <#list model.universities as uni>
                            <option value="${uni.id}"> ${uni.name}</option>
                        </#list>
                        </select>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <button class="btn btn-default btn-block" type="submit">добавить</button>
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>


</body>