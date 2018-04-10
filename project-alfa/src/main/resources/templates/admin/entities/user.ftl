<head>
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
<form action="/admin" method="get">
    <button>На главную</button>
</form>
<form method="get" action="/admin/users">
    <button>Назад к списку</button>
</form>
<div>
    <form class="form-horizontal" method="post" action="/admin/users/${model.user.id}/edit">

        <p>data id</p>
        <input type="text"  disabled name="id" id="id"  placeholder="id" value="${model.user.id}">

        <p>user id</p>
        <input type="text" readonly  name="user_id"  id="user_id"  placeholder="user id" value="${model.user.user.id}">
        
        <p>login</p>
        <input type="text" name="login" placeholder="login" value="${model.user.login}">
        
        <p>name</p>
        <input type="text" name="name" placeholder="name" value="${model.user.user.name}">
        
        <p>role</p>
        <select name="role">
            <option <#if model.user.role=="ADMIN">selected </#if> value="ADMIN">ADMIN</option>
            <option <#if model.user.role=="USER">selected </#if>value="USER">USER</option>
        </select>
        
        <p>status</p>
        <select name="userStatus">
            <option <#if model.user.userStatus=="CONFIRMED">selected </#if> value="CONFIRMED">CONFIRMED</option>
            <option <#if model.user.userStatus=="UNCONFIRMED">selected </#if>value="UNCONFIRMED">UNCONFIRMED</option>
            <option <#if model.user.userStatus=="BANNED">selected </#if>value="BANNED">BANNED</option>
        </select>
        
        <p>university</p>
        <input disabled type="text" placeholder="university"  value="${model.user.user.university.name} ${model.user.user.institute.name}">
        
        <p>course</p>
        <input type="text" name="course" placeholder="course" value="${model.user.user.course}">
        
        <input type="submit" value="save">
        
    </form>
</div>
<#else>
    user not found
</#if>
</body>
