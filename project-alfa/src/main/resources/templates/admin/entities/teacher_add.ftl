<head>

</head>
<body>
<form action="/admin" method="get">
    <button>На главную</button>
</form>
<form action="/admin/teachers" method="get">
    <button>Назад к списку</button>
</form>
<form class="form-horizontal" method="post" action="/admin/teachers/add">
    <p> ФИО </p>
    <input type="text" name="name" id="name">
    <input type="submit">
</form>
</body>