<#ftl encoding='UTF-8'>
<head>
    <head>
        <link href="/css/bootstrap.min.css" rel="stylesheet"/>
    </head>
</head>
<body>
<#if error??>
<div class="alert alert-danger" role="alert">${error}</div>
</#if>
<form action="/registration" method="post">
    <input name="login" placeholder="Логин">
    <input name="password" placeholder="Пароль">
    <input name="key" placeholder="key">
    <input type="submit">
</form>
</body>