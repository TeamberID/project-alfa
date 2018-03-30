<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    <link href="/css/exam-page.css" rel="stylesheet" type="text/css">
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
            <ul class="nav navbar-nav navbar-left">
                <#if model.post.exam??>
                    <li><a href="/user/session/exam/${model.post.exam.id}"><span class="glyphicon glyphicon-th-list"></span> к экзамену</a></li>
                <#else>
                    <li><a href="/registration"><span class="glyphicon glyphicon-user"></span> регистрация</a></li>
                    <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> вход</a></li>
                </#if>
            </ul>
        </div>
    </div>
</nav>

<#if model.post??>

<div class="container" style="padding-top: 60px">
    <h2>${model.post.exam.subject.name}</h2>
    <div class="panel panel-default">
        <div class="panel-heading">
            <div class="pull-left">
                <p>${model.post.author.name}</p>
            </div>
            <div class="pull-right">
                <p>${model.post.date}</p>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="panel-body">
            <div class="row">
                <div class="col-md-12">
                    <p>${model.post.text}</p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            <div id="attachmentCarousel" class="carousel slide" data-ride="carousel" data-interval=false>
                <div id="post-attachments" class="carousel-inner">
                    <#list model.post.attachments as image>
                        <#if image?index == 0>
                        <div class="item active">
                        <#else >
                        <div class="item">
                        </#if>
                            <img src="/file/${image.fileInfo.id}">
                        </div>
                    </#list>
                </div>
                <a class="left carousel-control" href="#attachmentCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#attachmentCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
        <div class="col-md-6">
            <div id="post-comments" class="well well-sm pre-scrollable">
                <#list model.post.comments as comment>
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="pull-left">
                                <p>${comment.author.name}</p>
                            </div>
                            <div class="pull-right">
                                <p>${comment.date}</p>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-md-12">
                                    <p>${comment.text}</p>
                                </div>
                                <div class="clearfix"></div>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>
            <div id="post-form-alert"></div>
            <form id="post-comment-form">
                <div class="form-group">
                    <textarea id="post-comment-text" name="text" class="form-control" placeholder="оставьте комментарий" rows="4" required></textarea>
                </div>
                <button type="button" class="btn btn-default" onclick="addNewPostComment(${model.post.id})">добавить комментарий</button>
            </form>
        </div>
        <div class="clearfix"></div>
    </div>
</div>

<#else>
    <h1>данный пост не существует</h1>
</#if>
<script>
    function addNewPostComment(examPostId) {
        if (isCommentValid()) {
            uploadExamPostCommentByAjax(examPostId);
        } else {
            createAlertMessage("ваш комментарий пуст");
        }
    }

    function clearAlertMessage() {
        $("#post-form-alert").html("");
    }

    function createAlertMessage(message) {
        $("#post-form-alert").html("");
        $("#post-form-alert").append(
                '<div class="alert alert-danger alert-dismissible">' +
                '<a class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                '<p><strong>кажется, у нас проблемы!</strong> ' + message + '</p>' +
                '</div>'
        );
    }

    function isCommentValid() {
        return $("#post-comment-text").val().length > 0;
    }

    function uploadExamPostCommentByAjax(examPostId) {
        var form = $("#post-comment-form")[0];
        var data = new FormData(form);
        data.append("examPostId", examPostId);

        $.ajax({
            url: '/api/exam-post-comment',
            type: 'POST',
            data: data,
            enctype: 'multipart/form-data',
            dataType: 'json',
            contentType: false,
            processData: false,
            success: function (data) {
                successUpload(data);
            },
            error: function () {
                createAlertMessage("сервер едва дышит. извините, попробуйте позже");
                console.log('uploadExamPostCommentByAjax method error')
            }
        })
    }

    function successUpload(data) {
        clearAlertMessage();
        clearCommentForm();
        fillComments(data);
    }

    function clearCommentForm() {
        $("#post-comment-form")[0].reset();
    }

    function fillComments(comments) {
        $("#post-comments").html("");
        for (var i = 0; i < comments.length; i++) {
            var comment = comments[i];
            $("#post-comments").append(
                    '<div class="panel panel-default">' +
                        '<div class="panel-heading">' +
                            '<div class="pull-left">' +
                                '<p>' + comment.author.name + '</p>' +
                            '</div>' +
                            '<div class="pull-right">' +
                                '<p>' + comment.date + '</p>' +
                            '</div>' +
                            '<div class="clearfix"></div>' +
                        '</div>' +
                        '<div class="panel-body">' +
                            '<div class="row">' +
                                '<div class="col-md-12">' +
                                    '<p>' + comment.text + '</p>' +
                                '</div>' +
                                '<div class="clearfix"></div>' +
                            '</div>' +
                        '</div>' +
                    '</div>'
            );
        }
    }
</script>
</body>
</html>