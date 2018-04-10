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
<form action="/admin/sessions" method="get">
    <button>Назад к списку</button>
</form>
        Инфо:
        <h5>Id: ${model.session.id}</h5>
        <h5>Semester: ${model.session.semesterNumber}</h5>
        <h5>Institute: ${model.session.institute.name}</h5>
        <h5>Exams:</h5>
<table>
    <tr>
        <th>Id</th>
        <th>Subject</th>
        <th>Teacher</th>
    </tr>
        <#list model.session.exams as exam>
        <tr>
        <td>${exam.id}</td>
        <td>${exam.subject.name}</td>
        <td>${exam.teacher.name}</td>
            <td>
                <form method="post" action="/admin/exams/${exam.id}/delete">
                    <button>delete</button>
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
