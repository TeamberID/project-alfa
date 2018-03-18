<#ftl encoding='UTF-8'>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    <link href="/css/entry-page.css" rel="stylesheet" type="text/css">
    <link href="/css/navbar.css" rel="stylesheet" type="text/css">
</head>
<body>
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container-fluid" style="color: #9acfea">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"></a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/registration-key-request"><span class="glyphicon glyphicon-wrench"></span> запрос ключей</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="entry-form">
            <#if loginModel?? && loginModel.loginError.isPresent()>
                <div class="alert alert-danger" role="alert">Логин или пароль введены неверно</div>
            </#if>
            <#if registrationError??>
                <div class="alert alert-danger" role="alert">${registrationError}</div>
            </#if>

            <ul class="nav nav-tabs">
                <#if loginModel??>
                    <li class="active">
                <#else>
                    <li>
                </#if>
                    <a data-toggle="tab" href="#signin">вход</a>
                </li>
                <#if registrationModel??>
                <li class="active">
                <#else>
                <li>
                </#if>
                    <a data-toggle="tab" href="#signup">регистрация</a>
                </li>
            </ul>

            <div class="tab-content top-tab-padding">
                <#if loginModel??>
                    <div id="signin" class="tab-pane fade in active">
                <#else>
                    <div id="signin" class="tab-pane fade">
                </#if>
                    <form role="Form" method="post" action="/login" accept-charset="UTF-8">
                        <div class="form-group">
                            <input type="text" name="login" placeholder="логин" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" placeholder="пароль" class="form-control">
                        </div>
                        <div class="form-group">
                            <div class="row">
                                <div class="col-lg-2">
                                    <input type="checkbox" name="remember-me">
                                </div>
                                <div class="col-lg-10">
                                    <label><- запомнить меня</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-default">вход</button>
                        </div>
                    </form>
                </div>

                <#if registrationModel??>
                    <div id="signup" class="tab-pane fade in active">
                <#else>
                    <div id="signup" class="tab-pane fade">
                </#if>
                    <form role="Form" method="post" action="/registration" accept-charset="UTF-8">
                        <div class="form-group">
                            <input type="text" name="login" placeholder="логин" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="password" name="password" placeholder="пароль" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="text" name="key" placeholder="ключ регистрации" class="form-control">
                            <a href="/registration-key-request">нет ключа? пожалуйста, оставьте запрос.</a>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-default">регистрация</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>