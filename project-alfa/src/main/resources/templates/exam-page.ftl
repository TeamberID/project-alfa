<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body>
<#if model.exam??>

<div class="container">
    <div id="exam-upper-block">
        <h2>${model.exam.subject.name}</h2>
        <div class="row">
            <div class="col-md-6">
                <h4>Принимать экзамен у вас будет:</h4>
                <h3>${model.exam.teacher.name}</h3>
            </div>
            <div class="col-md-6">
                <h4>Дидактические материалы:</h4>
            </div>
        </div>
    </div>
    <hr>
    <div id="exam-form-block">
        <form method="post" enctype="multipart/form-data" id="exam-post-form">
            <div class="form-group">
                <textarea id="exam-post-text" name="text" class="form-control" placeholder="отзыв об экзамене" required></textarea>
            </div>
            <div class="form-group">
                <input id="files" type="file" class="form-control" multiple="multiple" name="files" accept="image/*"/>
            </div>
            <button class="btn btn-default" type="button" onclick="addNewExamPost(${model.exam.id})">Оставить отзыв</button>
        </form>
    </div>
    <hr>
    <div id="exam-bottom-block">
        <div id="exam-post-filter">

        </div>
        <div id="exam-post-list">
            <#list model.exam.posts as post>
                <div class="panel">
                    <p>${post.date}</p>
                    <p>${post.author.name}</p>
                    <p>${post.text}</p>
                    <p>${post.comments?size}</p>
                    <p>${post.attachments?size}</p>
                    <a>Подробнее</a>
                </div>
            </#list>
        </div>
    </div>
</div>

<#else>
    <h1>Данный экзамен не существует</h1>
</#if>

<script>
    function addNewExamPost(examId) {
        uploadExamPostByAjax(examId);
    }

    function uploadExamPostByAjax(examId) {
        var form = $("#exam-post-form")[0];
        var data = new FormData(form);
        data.append("examId", examId);

        $.ajax({
            url: '/api/exam-post',
            type: 'POST',
            enctype: 'multipart/form-data',
            data: data,
            cache: false,
            dataType: 'json',
            contentType: false,
            processData: false,
            success: function (data) {
                writeResult(data);
            },
            error: function () {
                console.log('sendFileByAjax method error')
            }
        })
    }

    function writeResult(data) {
        var posts = data;
        $('#exam-post-list').html("");
        for (var i = 0; i < posts.length; i++) {
            var currentPost = posts[i];
            $('#exam-post-list').append(
                '<div class="panel">' +
                    '<p>' + currentPost.date + '</p>' +
                    '<p>' + currentPost.author.name + '</p>' +
                    '<p>' + currentPost.text + '</p>' +
                    '<p>' + currentPost.commentsCount + '</p>' +
                    '<p>' + currentPost.attachmentsCount + '</p>' +
                    '<a>Подробнее</a>' +
                '</div>'
            );
        }
    }
</script>
</body>
</html>