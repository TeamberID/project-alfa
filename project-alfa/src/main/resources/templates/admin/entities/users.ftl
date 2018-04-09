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
<table>
    <tr>
        <th>Id</th>
        <th>Login</th>
        <th>Name</th>
        <th>Role</th>
        <th>User Status</th>
        <th>Institute</th>
        <th>Course</th>
        <th>Group</th>
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
        <td> - </td>
        <td>
            <form method="get" action="/admin/users/${user.id}">
            <button>edit</button>
            </form>
        </td>
        <td>
            <form method="post" action="/admin/users/${user.id}/delete">
                <button >delete</button>
            </form>
        </td>

    <#--
            <td>${user.user.group}</td>
    -->
    </tr>
</#list>
</table>
</body>
