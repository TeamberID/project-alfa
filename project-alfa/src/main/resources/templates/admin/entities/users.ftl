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
<table>
    <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Name</th>
        <th>Role</th>
        <th>User Status</th>
        <th>Institute</th>
        <th>Course</th>
        <th>Role</th>
        <th></th>
        <th></th>
    </tr>
<#list model.users as user>
    <tr>
        <td>${user.id}</td>
        <td>${user.login}</td>
        <td>${user.user.name}</td>
        <td>${user.role}</td>
        <td>${user.userStatus}</td>
        <td>${user.user.university.name} ${user.user.institute.name}</td>
        <td>${user.user.course}</td>
        <td>${user.role}</td>
        <td>
            <form method="get" action="/admin/users/${user.id}">
            <button class = "option">edit</button>
            </form>
        </td>
        <td>
            <form method="post" action="/admin/users/${user.id}/delete" >
                <button class = "option">delete</button>
            </form>
        </td>
    </tr>
</#list>
</table>
</body>
