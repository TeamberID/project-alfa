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
                <li><a href="/user/session/"><span class="glyphicon glyphicon-th-large"></span> назад к экзаменам</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> выйти</a></li>
            </ul>
        </div>
    </div>
</nav>

<#if model.exam??>

<div class="container" style="padding-top: 60px">
    <h2>${model.exam.subject.name}</h2>
    <div id="exam-upper-block">
        <div class="row">
            <div class="col-md-6">
                <h4>принимать экзамен у вас будет:</h4>
                <a href="/user/teachers/${model.exam.teacher.id}"><h3 style="font-weight: bold">${model.exam.teacher.name}</h3></a>
                <img src="${model.exam.teacher.photo.url}" class="img-thumbnail img-responsive" alt="Your Teacher is Watching You!" width="200" height="400">
            </div>
            <div class="col-md-6">
                <h4>дидактические материалы:</h4>
                <table class="table table-condensed table-responsive">
                    <thead>
                    <tr>
                        <th>Название</th>
                        <th>Автор</th>
                        <th>Скачать</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list model.manuals as manual>
                    <tr>
                        <td>${manual.title}</td>
                        <td>${manual.author}</td>
                        <td><a href="${manual.url}" class="btn btn-default" role="button" download><span class="glyphicon glyphicon-download"></span></a></td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
                <a href="/user/manuals/${model.exam.id}">получить больше материалов</a>
                <hr>
                <h4>Тьюторы:</h4>
                <#if model.tutors??>
                <div style="margin-left: 3%">
                <#list model.tutors as tutor>
                    <div class="row">
                        ${tutor.name}
                        ${tutor.contacts}
                    </div>
                </#list>
                </div>
                </#if>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <hr>
    <#if errors??>
        <div class="alert alert-danger" role="alert">${model.errors}</div>
    </#if>
    <div id="exam-form-block" class="post-form">
        <div id="exam-post-alert"></div>
        <form id="exam-post-form">
            <div class="form-group">
                <textarea id="exam-post-text" name="text" class="form-control" placeholder="отзыв об экзамене" rows="6" wrap="hard" cols="20" required></textarea>
            </div>
            <div class="clearfix"></div>
        </form>
        <form id="post-file-form" action="${model.credentials.serverUrl}" method="post" enctype="multipart/form-data">
            <input id="key" type="hidden" name="key" value="${model.credentials.key}" /><br />
            <input type="hidden" name="acl" value="${model.credentials.acl}" />
            <input type="hidden" name="Content-Type" value="${model.credentials.contentType}" /><br />
            <input type="hidden" name="x-amz-server-side-encryption" value="${model.credentials.serverSideEncryption}" />
            <input type="hidden" name="X-Amz-Credential" value="${model.credentials.amzCredential}" />
            <input type="hidden" name="X-Amz-Algorithm" value="${model.credentials.amzAlgorithm}" />
            <input type="hidden" name="X-Amz-Date" value="${model.credentials.amzDate}" />

            <input type="hidden" name="Policy" value="${model.credentials.policy}" />
            <input type="hidden" name="X-Amz-Signature" value="${model.credentials.signature}" />

            <div class="form-group">
                <input id="files" type="file" multiple="multiple" accept="image/*"/>
            </div>
        </form>
        <button class="btn btn-default" type="button" onclick="addNewExamPost(${model.exam.id})">оставить отзыв</button>
    </div>
    <hr>
    <div id="exam-bottom-block">
        <div id="exam-post-filter">

        </div>
        <div id="exam-post-list">
            <#list model.exam.posts as post>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="pull-left">
                            <p>${post.author.name}</p>
                        </div>
                        <div class="pull-right">
                            <p>${post.date}</p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-10">
                                <p>${post.text}</p>
                            </div>
                            <div class="col-md-2">
                                <a href="/user/session/exam-post/${post.id}" class="btn btn-lg">
                                    <span class="glyphicon glyphicon-chevron-right"></span>
                                </a>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    <div class="panel-footer">
                        <div class="pull-left">
                            <p>прикрепленные файлы: ${post.attachments?size}</p>
                        </div>
                        <div class="pull-right">
                            <p>количество комментариев: ${post.comments?size}</p>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
            </#list>
        </div>
    </div>


</div>

<#else>
<h1>данный экзамен не существует</h1>
</#if>

<script>

    function addNewExamPost(examId) {
        if (isExamPostTextareaValid()) {
            uploadExamPostByAjax(examId);
        } else {
            createAlertMessage("ваш отзыв пуст.");
        }

    }

    function isExamPostTextareaValid() {
        return $("#exam-post-text").val().length > 0;
    }

    function clearAlertMessage() {
        $("#exam-post-alert").html("");
    }

    function clearCommentForm() {
        $("#exam-post-form")[0].reset();
    }

    function createAlertMessage(message) {
        clearAlertMessage();
        $("#exam-post-alert").append(
                '<div class="alert alert-danger alert-dismissible">' +
                '<a class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                '<p><strong>кажется, у нас проблемы!</strong> ' + message + '</p>' +
                '</div>'
        );
    }

    function uploadExamPostByAjax(examId) {
        var fileStorageNames = generateFileStorageNames();
        var data = createPostDataForm(fileStorageNames, examId);

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
                uploadFilesToStorage(fileStorageNames);
                successUpload(data);
            },
            error: function () {
                createAlertMessage("сервер едва дышит. извините, попробуйте позже");
                console.log('uploadExamPostByAjax method error')
            }
        })
    }

    function createPostDataForm(fileStorageNames, examId) {
        var form = $("#exam-post-form")[0];
        var data = new FormData(form);
        for (var i = 0; i < fileStorageNames.length; i++) {
            data.append("fileStorageNames", fileStorageNames[i]);
        }
        data.append("examId", examId);
        return data;
    }

    function generateFileStorageNames() {
        var files = $("#files")[0].files;
        var fileNames = [];
        for (var i = 0; i < files.length; i++) {
            fileNames[i] = generateFileName(files[i].name, i);
        }
        return fileNames;
    }

    function generateFileName(fileName, salt) {
        var extension = fileName.split('.').pop();
        return Date.now() + salt + '.' + extension;
    }

    function uploadFilesToStorage(fileNames) {
        var url = $("#post-file-form").attr('action');
        var files = $("#files")[0].files;
        var isSuccess = true;
        for (var i = 0; i < files.length && isSuccess; i++) {
            var key = fileNames[i];
            var file = files[i];

            changeKeyValue(key);
            var data = createFilePostData(file);

            $.ajax({
                url: url,
                type: 'POST',
                enctype: 'multipart/form-data',
                data: data,
                dataType: 'xml',
                contentType: false,
                processData: false,
                error: function () {
                    isSuccess = false;
                    console.log('uploadFilesToStorage method error')
                }
            })
        }
    }

    function changeKeyValue(key) {
        $('#key').val(key);
    }

    function createFilePostData(file) {
        var form = $("#post-file-form")[0];
        var data = new FormData(form);
        data.append("file", file);
        return data;
    }

    function successUpload(data) {
        clearAlertMessage();
        clearCommentForm();
        writeResult(data);
    }

    function writeResult(data) {
        var posts = data;
        $('#exam-post-list').html("");
        for (var i = 0; i < posts.length; i++) {
            var post = posts[i];
            $('#exam-post-list').append(
                    '<div class="panel panel-default">' +
                        '<div class="panel-heading">' +
                            '<div class="pull-left">' +
                                '<p>' + post.author.name + '</p>' +
                            '</div>' +
                            '<div class="pull-right">' +
                                '<p>' + post.date + '</p>' +
                            '</div>' +
                            '<div class="clearfix"></div>' +
                        '</div>' +
                        '<div class="panel-body">' +
                            '<div class="row">' +
                                '<div class="col-md-10">' +
                                    '<p>' + post.text + '</p>' +
                                '</div>' +
                                '<div class="col-md-2">' +
                                    '<a href="/user/session/exam-post/' + post.id + '" class="btn btn-lg">' +
                                        '<span class="glyphicon glyphicon-chevron-right"></span>' +
                                    '</a>' +
                                '</div>' +
                                '<div class="clearfix"></div>' +
                            '</div>' +
                        '</div>' +
                        '<div class="panel-footer">' +
                            '<div class="pull-left">' +
                                '<p>прикрепленные файлы: ' + post.attachments.length + '</p>' +
                            '</div>' +
                            '<div class="pull-right">' +
                                '<p>количество комментариев: ' + post.comments.length + '</p>' +
                            '</div>' +
                            '<div class="clearfix"></div>' +
                        '</div>' +
                    '</div>'
            );
        }
    }
</script>
</body>
</html>