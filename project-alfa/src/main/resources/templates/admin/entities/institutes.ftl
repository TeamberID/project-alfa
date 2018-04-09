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
<form method="get" action="/admin/institutes/add">
    <button>Добавить</button>
</form>
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
