<#ftl encoding='UTF-8'>
<html>
<head>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    <link href="/css/teacher-page.css" rel="stylesheet" type="text/css">
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
                <li><a href="/user/teachers/"><span class="glyphicon glyphicon-book"></span> к преподавателям</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out"></span> выйти</a></li>
            </ul>
        </div>
    </div>
</nav>

<#if model.teacher??>

<div class="container" style="padding-top: 60px">
    <h2>${model.teacher.name}</h2>
    <div class="row">
        <div class="col-md-3">
            <img src="${model.teacher.photo.fileInfo.url}" class="img-thumbnail img-responsive" alt="Your Teacher is Watching You!" width="200" height="300">
        </div>
        <div class="col-md-3">
            <div class="teacher-subject">
                <h4><strong>предметы:</strong></h4>
                <ul>
                <#list model.teacher.subjects as subject>
                    <li>${subject.name}</li>
                </#list>
                </ul>
            </div>
        </div>
        <div class="col-md-6">
            <div class="teacher-rate">
                <h4 id="teacher-current-score"><strong>текущая оценка преподавателя: ${model.teacher.teacherScore.getAverage()}</strong></h4>
                <#if model.isVoted>
                    <form id="teacher-rate-form">
                        <#list model.teacher.teacherScore.criteriaScores as criteriaScore>
                            <div class="row" style="padding-top: 2px">
                                <div class="col-md-6">
                                    <p>${criteriaScore.criteria.name}</p>
                                </div>
                                <div id="criteria-${criteriaScore?index}" class="col-md-6">
                                    <input id="criteria-value-${criteriaScore?index}" value="${criteriaScore.getAverage()}" class="form-control" type="number" disabled>
                                </div>
                            </div>
                        </#list>
                    </form>
                <#else>
                    <form id="teacher-rate-form">
                        <#list model.teacher.teacherScore.criteriaScores as criteriaScore>
                            <div class="row" style="padding-top: 2px">
                                <div class="col-md-6">
                                    <p>${criteriaScore.criteria.name}</p>
                                </div>
                                <div id="criteria-${criteriaScore?index}" class="col-md-6">
                                    <input id="criteria-value-${criteriaScore?index}" class="form-control" type="number">
                                </div>
                            </div>
                        </#list>
                        <div class="row" style="padding-top: 2px">
                            <div class="col-md-12">
                                <button id="teacher-rate-form-button" class="btn btn-default" type="button" onclick="uploadTeacherVoteForm(${model.teacher.id})">оценить</button>
                            </div>
                        </div>
                    </form>
                </#if>
            </div>
        </div>
    </div>
    <hr>
    <div>
        <h3>отзывы о преподавателе</h3>
        <div id="teacher-comments" class="well well-sm pre-scrollable">
            <#list model.teacher.comments as comment>
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
                    <div>
                        <button onclick="sendReport(${comment.id})">
                            report
                        </button>
                    </div>
                </div>
            </#list>
        </div>
        <div id="teacher-review-form-alert"></div>
        <form id="teacher-review-form">
            <div class="form-group">
                <textarea id="teacher-comment-text" name="text" class="form-control" placeholder="оставьте комментарий" rows="4" required></textarea>
            </div>
            <button type="button" class="btn btn-default" onclick="addNewTeacherComment(${model.teacher.id})">добавить комментарий</button>
        </form>
    </div>
</div>

<#else>
    <h1 style="padding-top: 60px">Извините, данного преподавателя у нас нет.</h1>
</#if>
</body>

<script>
    function uploadTeacherVoteForm(teacherId) {
        var criteriaLength = 5;
        var criteriaList = [];
        for (var i = 0; i < criteriaLength; i++) {
            var criteriaId = i + 1;
            var voteValue = $("#criteria-value-" + i).val();
            criteriaList[i] = {criteriaId: criteriaId, rate: voteValue};
        }
        var data = JSON.stringify({"teacherId": teacherId, "criteriaVotes": criteriaList});

        $.ajax({
            url: '/api/teacher/vote',
            type: 'POST',
            data: data,
            dataType: 'json',
            contentType: "application/json",
            success: function (data) {
                updateTeacherScore(data);
            },
            error: function () {
                createAlertMessage("сервер едва дышит. извините, попробуйте позже");
                console.log('uploadTeacherVoteForm method error')
            }
        });
    }

    function updateTeacherScore(data) {
        var teacherScore = data;
        var averageTeacherScore = teacherScore.sum / teacherScore.voteCount;
        updateCurrentScore(averageTeacherScore);
        updateRateForm(teacherScore.criteriaScores);
        hideRateFormButton();
    }

    function updateCurrentScore(averageTeacherScore) {
        var score = $("#teacher-current-score");
        score.html("");
        score.append(
            '<h4 id="teacher-current-score"><strong>текущая оценка преподавателя: ' + averageTeacherScore + '</strong></h4>'
        );
    }

    function updateRateForm(criteriaScores) {
        for (var i = 0; i < criteriaScores.length; i++) {
            var score = criteriaScores[i];
            var criteriaScore = score.sum / score.voteCount;
            var currentDiv = $("#criteria-" + i);
            currentDiv.html("");
            currentDiv.append(
                '<input id="criteria-value-' + i + '" value="' + criteriaScore + '" class="form-control" type="number" disabled>'
            );
        }
    }

    function hideRateFormButton() {
        $("#teacher-rate-form-button").hide();
    }

    function addNewTeacherComment(teacherId) {
        if (isCommentValid()) {
            uploadTeacherCommentToServer(teacherId);
        } else {
            createAlertMessage("ваш комментарий пуст");
        }
    }

    function isCommentValid() {
        return $("#teacher-comment-text").val().length > 0;
    }

    function uploadTeacherCommentToServer(teacherId) {
        var form = $("#teacher-review-form")[0];
        var data = new FormData(form);
        data.append("teacherId", teacherId);

        $.ajax({
            url: '/api/teacher-comment',
            type: 'POST',
            data: data,
            enctype: 'multipart/form-data',
            dataType: 'json',
            contentType: false,
            processData: false,
            success: function (data) {
                teacherCommentSuccessUpload(data);
            },
            error: function () {
                createAlertMessage("сервер едва дышит. извините, попробуйте позже");
                console.log('uploadTeacherCommentToServer method error')
            }
        })
    }

    function teacherCommentSuccessUpload(data) {
        clearAlertMessage();
        clearCommentForm();
        fillComments(data);
    }

    function clearCommentForm() {
        $("#teacher-review-form")[0].reset();
    }

    function clearAlertMessage() {
        $("#teacher-review-form-alert").html("");
    }

    function fillComments(comments) {
        $("#teacher-comments").html("");
        for (var i = 0; i < comments.length; i++) {
            var comment = comments[i];
            $("#teacher-comments").append(
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

    function createAlertMessage(message) {
        $("#teacher-review-form-alert").html("");
        $("#teacher-review-form-alert").append(
                '<div class="alert alert-danger alert-dismissible">' +
                '<a class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                '<p><strong>кажется, у нас проблемы!</strong> ' + message + '</p>' +
                '</div>'
        );
    }

    function sendReport(commentId) {
               $.ajax({
            url: '/api/report/comment/'+commentId,
            type: 'POST',
            dataType: 'text',
            contentType: false,
            processData: false,
            success: function (data) {
                alert(data);
            },
            error: function () {
                createAlertMessage("сервер едва дышит. извините, попробуйте позже");
                console.log('sendReport method error')
            }
        })
    }
</script>
</html>