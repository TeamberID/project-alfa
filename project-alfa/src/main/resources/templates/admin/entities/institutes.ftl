<head>
    <link href="/css/table-style.css" rel="stylesheet" type="text/css">
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/session-page.css" rel="stylesheet" type="text/css">
</head>
<body>
<style>
    /* внешние границы таблицы серого цвета толщиной 1px */
    table {border: 1px solid grey;}
    /* границы ячеек первого ряда таблицы */
    th {border: 1px solid grey; text-align: center}
    /* границы ячеек тела таблицы */
    td {border: 1px solid grey;}
</style>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid" style="color: #9acfea">
        <div class="navbar-header">
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

<h4>
<a href="/admin/institutes/add" class ="addbutton">Добавить</a>
</h4>

<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>University</th>
        <th></th>
    </tr>
<#list model.institutes as inst>
    <tr>
        <td>${inst.id}</td>
        <td>${inst.name}</td>
        <td>${inst.university.name}</td>
        <td>
            <form method="post" action="/admin/institutes/${inst.id}/delete">
                <button class = "option">delete</button>
            </form>
        </td>
    <#--
            <td>${user.user.group}</td>
    -->
    </tr>
</#list>
</table>
</body>
