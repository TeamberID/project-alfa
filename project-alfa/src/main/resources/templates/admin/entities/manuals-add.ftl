<head>

</head>
<body>
<form action="/admin" method="get">
    <button>На главную</button>
</form>
<form action="/admin/manuals/" method="get">
    <button>Назад к списку</button>
</form>
<form method="post" action="/admin/manuals/add" enctype="multipart/form-data">
    <label for="title">Заголовок</label>
    <input type="text" name="title" id="title">

    <label for="author">Автор</label>
    <input type="text" name="author" id="author">

    <label for="file">Документ</label>
    <input id="file" type="file" name="file"/>

    <label for="exam"></label>
    <select id="exam" name="examId">
        <#list model.exams as exam>
            <option value="${exam.id}">${exam.institute.name} ${exam.semesterNumber} ${exam.subject.name} ${exam.teacher.name}</option>
        </#list>
    </select>
    <input type="submit">
</form>
</body>