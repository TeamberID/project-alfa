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
<form method="get" action="/admin/sessions/add">
    <button>Добавить</button>
</form>
<table>
    <tr>
        <th>Id</th>
        <th>Semester</th>
        <th>Institute</th>
        <th>Exams</th>
        <th></th>
    </tr>
<#list model.sessions as session>
    <tr>
        <td>${session.id}</td>
        <td>${session.semesterNumber}</td>
        <td>${session.institute.name}</td>
        <td>
            <details>
                <ol>
                    <#list session.exams as exam>
                        <li>${exam.subject.name} - ${exam.teacher.name}</li>
                    </#list>
                </ol>
            </details>
        </td>
        <td>
            <form method="get" action="/admin/sessions/${session.id}">
                <button>edit</button>
            </form>
        </td>
        <td>
            <form method="post" action="/admin/sessions/${session.id}/delete">
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
