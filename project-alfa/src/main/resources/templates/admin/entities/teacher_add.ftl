<head>

</head>
<body>
<form action="/admin" method="get">
    <button>На главную</button>
</form>
<form action="/admin/teachers" method="get">
    <button>Назад к списку</button>
</form>
<form method="post" action="/admin/teachers/add" enctype="multipart/form-data">
    <label for="name"> ФИО </label>
    <input type="text" name="name" id="name">
    <label for="file">Фото</label>
    <input id="file" type="file" name="photo" accept="image/*"/>
    <label for="subjects"></label>
    <select id="subjects" name="subjectsId" multiple>
        <#list model.subjects as subject>
            <option value="${subject.id}">${subject.name}</option>
        </#list>
    </select>
    <input type="submit">
</form>
</body>