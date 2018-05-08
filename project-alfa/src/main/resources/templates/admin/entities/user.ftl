<head>
    <link href="/css/table-style.css" rel="stylesheet" type="text/css">
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="/css/session-page.css" rel="stylesheet" type="text/css">
</head>
<body>
<#if model.user??>

<#if model.isSuccessfullyAdded??>
    <#if model.isSuccessfullyAdded>
        изменения сохранены
    <#elseif !model.isSuccessfullyAdded>
        ошибка
    </#if>
</#if>



<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid" style="color: #9acfea">
        <div class="navbar-header">
            <div class="navbar-brand">
                <a href="/admin/users"><span class="glyphicon glyphicon-circle-arrow-left"></span></a>
            </div>

            <div class="navbar-brand">
            </div>

            <div class="navbar-brand">
                <a href="/admin">Admin mode</a>
            </div>
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
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> выйти</a></li>
            </ul>
        </div>
    </div>
</nav>

<br><br><br>


<div class="container">
    <div class="key-form">

        <form method="post" action="/admin/users/${model.user.id}/edit" class="">

            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <input class="form-control" type="text"  disabled name="id" id="id"  placeholder="id" value="${model.user.id}">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="form-group">
                            <input class="form-control" type="text" readonly  name="user_id"  id="user_id"  placeholder="user id" value="${model.user.user.id}">
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <input class="form-control" type="text" name="login" placeholder="login" value="${model.user.login}">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="form-group">
                            <input class="form-control" type="text" name="name" placeholder="name" value="${model.user.user.name}"">
                        </div>
                    </div>
                </div>
                <input type="hidden" name="hashPassword" value="${model.user.hashPassword}" />
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <select name="role">
                            <option <#if model.user.role=="ADMIN">selected </#if> value="ADMIN">ADMIN</option>
                            <option <#if model.user.role=="USER">selected </#if>value="USER">USER</option>
                        </select>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="form-group">

                            <select name="userStatus">
                                <option <#if model.user.userStatus=="CONFIRMED">selected </#if> value="CONFIRMED">CONFIRMED</option>
                                <option <#if model.user.userStatus=="UNCONFIRMED">selected </#if>value="UNCONFIRMED">UNCONFIRMED</option>
                                <option <#if model.user.userStatus=="BANNED">selected </#if>value="BANNED">BANNED</option>
                            </select>

                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <input class="form-control" disabled type="text" placeholder="university"  value="${model.user.user.university.name} ${model.user.user.institute.name}"">
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <div class="form-group">
                            <input class="form-control" type="text" name="course" placeholder="course" value="${model.user.user.course}">
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <button class="btn btn-default btn-block" type="submit">сохранить</button>
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>



<#else>
    user not found
</#if>
</body>
