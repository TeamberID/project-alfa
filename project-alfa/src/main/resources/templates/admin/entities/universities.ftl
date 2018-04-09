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
<form method="get" action="/admin/universities/add">
    <button>Добавить</button>
</form>
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Institutes</th>
        <th></th>
    </tr>
<#list model.universities as uni>
    <tr>
        <td>${uni.id}</td>
        <td>${uni.name}</td>
        <td>
            <details>
                <ol>
                    <#list uni.institutes as inst>
                        <li>${inst.name} </li>
                    </#list>
                </ol>
            </details>
        </td>
        <td>
            <form method="post" action="/admin/universities/${uni.id}/delete">
                <button>delete</button>
            </form>
        </td>
    </tr>
</#list>
</table>
</body>
