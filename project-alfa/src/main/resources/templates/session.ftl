<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
</head>
<body>
<#if model.exams??>
    <#list model.exams as exam>
        <div>
            <h4>${exam.subject.name}</h4>
            <h5>${exam.teacher.name}</h5>
            <a href="/user/session/exam/${exam.id}">К экзамену</a>
        </div>
        <div>
            ______________
        </div>
    </#list>
<#else>
    <h1>Сессия для вас не найдена(</h1>
</#if>
</body>
</html>