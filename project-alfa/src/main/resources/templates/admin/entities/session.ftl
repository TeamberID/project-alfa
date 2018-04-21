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
    th {border: 1px solid grey;}
    /* границы ячеек тела таблицы */
    td {border: 1px solid grey;}
</style>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid" style="color: #9acfea">
        <div class="navbar-header">
            <div class="navbar-brand">
                <a href="/admin/sessions"><span class="glyphicon glyphicon-circle-arrow-left"></span></a>
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

        О сессии
        <h5>Id: ${model.session.id}</h5>
        <h5>Semester: ${model.session.semesterNumber}</h5>
        <h5>Institute: ${model.session.institute.name}</h5>
        <h2 style="text-align: center">Exams</h2>
<table>
    <tr>
        <th>Id</th>
        <th>Subject</th>
        <th>Teacher</th>
        <th>Action</th>
    </tr>
        <#list model.session.exams as exam>
        <tr>
        <td>${exam.id}</td>
        <td>${exam.subject.name}</td>
        <td>${exam.teacher.name}</td>
            <td>
                <form method="post" action="/admin/exams/${exam.id}/delete">
                    <button class = "option">delete</button>
                </form>
            </td>
        </tr>

</#list>
</table>
<h5>Добавить экзамен
</h5>
<form method="post" action="/admin/exams/add">
    <input hidden name="semester_number" value="${model.session.semesterNumber}">
    <input hidden name="institute_id" value="${model.session.institute.id}">
    <input hidden name="session_id" value="${model.session.id}">
    <select name="subject_id">
        <#list model.subjects as subject>
        <option value="${subject.id}">${subject.name}</option>
        </#list>
    </select>
    <select name="teacher_id">
    <#list model.teachers as teacher>
        <option value="${teacher.id}">${teacher.name}</option>
    </#list>
    </select>
    <input type="submit">
</form>
</body>
