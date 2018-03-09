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
        <input name="login" placeholder="Логин">
        <input name="password" placeholder="Пароль">
        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-9">
                <div class="checkbox">
                    <label>
                        <input type="checkbox" name="remember-me">Запомнить
                    </label>
                </div>
            </div>
        </div>
        <input type="submit">
    </form>
</div>
</body>