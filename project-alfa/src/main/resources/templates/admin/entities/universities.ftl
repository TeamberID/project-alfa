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
<table>
    <tr>
        <th>Id</th>
        <th>Name</th>
        <th>Institutes</th>
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
    </tr>
</#list>
</table>
</body>
