<#ftl encoding='UTF-8'>
<head>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<#if error??>
<div class="alert alert-danger" role="alert">${error}</div>
</#if>
<form action="/registration" method="post">
    <input name="login" type="text" placeholder="Логин">
    <input name="password" type="password" placeholder="Пароль">
    <input name="key" type="text" placeholder="key">
    <input type="submit">
</form>
</body>