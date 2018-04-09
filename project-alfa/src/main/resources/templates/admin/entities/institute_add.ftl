<head>

</head>
<body>
<form action="/admin" method="get">
    <button>На главную</button>
</form>
<form action="/admin/institutes" method="get">
    <button>Назад к списку</button>
</form>
<form class="form-horizontal" method="post" action="/admin/institutes/add">
    <p> Название </p>
    <input type="text" name="name" id="name">
    <p> Университет</p>
    <select name="universityId" id="universityId" title="Универ">
        <#list model.universities as uni>
            <option value="${uni.id}"> ${uni.name}</option>
        </#list>
    </select>
    <input type="submit">
</form>
</body>