<#ftl encoding='UTF-8'>
<head>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<#if model.error.isPresent()>
<div class="alert alert-danger" role="alert">Логин или пароль введены неверно</div>
</#if>
<h2>Sign In</h2>
<div class="content-block">
    <form class="form-horizontal" action="/login" method="post">
        <input name="login" type="text" placeholder="Логин">
        <input name="password" type="password" placeholder="Пароль">
        <input type="submit">
    </form>
</div>
</body>