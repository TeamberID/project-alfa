<head>
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
<form action="/admin" method="get">
    <button>На главную</button>
</form>
<form method="get" action="/admin/manuals/add">
    <button>Добавить</button>
</form>
<table>
    <tr>
        <th>Id</th>
        <th>Title</th>
        <th>Author</th>
        <th>Institute</th>
        <th>Semester</th>
        <th>Subject</th>
        <th>Teacher</th>
        <th></th>
    </tr>
<#list model.manuals as manual>
    <tr>
        <td>${manual.id}</td>
        <td>${manual.title}</td>
        <td>${manual.author}</td>
        <td>${manual.exam.institute.name}</td>
        <td>${manual.exam.semesterNumber}</td>
        <td>${manual.exam.subject.name}</td>
        <td>${manual.exam.teacher.name}</td>
        <td>
            <form method="get" action="/admin/manuals/${manual.id}/delete">
                <button>delete</button>
            </form>
        </td>
    </tr>
</#list>
</table>
</body>
