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
<form method="get" action="/admin/teachers/add">
    <button>Добавить</button>
</form>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th></th>
    </tr>
<#list model.teachers as teacher>
    <tr>
        <td>${teacher.id}</td>
        <td>${teacher.name}</td>
        <td>
            <form method="post" action="/admin/teachers/${teacher.id}/delete">
                <button>delete</button>
            </form>
        </td>
    <#--
            <td>${user.user.group}</td>
    -->
    </tr>
</#list>
</table>
</body>
