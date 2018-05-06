<head>

    <link href="/css/table-style.css" rel="stylesheet" type="text/css">
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    <link href="/css/session-page.css" rel="stylesheet" type="text/css">
</head>
<body>


<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid" style="color: #9acfea">
        <div class="navbar-header">
            <div class="navbar-brand">
                <a href="/admin/teachers"><span class="glyphicon glyphicon-circle-arrow-left"></span></a>
            </div>

            <div class="navbar-brand">
            </div>

            <div class="navbar-brand">
                <a href="/admin/">Admin mode</a>
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

<br>
<br>
<br>





<div class="container">
    <div class="key-form">
        <h3>Добавление преподавателя</h3>

        <form id="teacher-data-form" method="post" action="/admin/teachers/add" class="" enctype="multipart/form-data">
            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <input class="form-control" name="name" id="name" placeholder="ФИО" type="text">
                    </div>
                </div>

            </div>

            <input id="teacher-photo-storage-name" type="hidden" name="teacherPhotoStorageName" value="">

            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <select class="form-control" id="subjects" name="subjectsId" multiple>
                        <#list model.subjects as subject>
                            <option value="${subject.id}">${subject.name}</option>
                        </#list>
                        </select>
                    </div>
                </div>
            </div>
        </form>
        <form id="teacher-photo-form" action="${model.credentials.serverUrl}" method="post" enctype="multipart/form-data">
            <input id="key" type="hidden" name="key" value="${model.credentials.key}" /><br />
            <input type="hidden" name="acl" value="${model.credentials.acl}" />
            <input type="hidden" name="Content-Type" value="${model.credentials.contentType}" /><br />
            <input type="hidden" name="x-amz-server-side-encryption" value="${model.credentials.serverSideEncryption}" />
            <input type="hidden" name="X-Amz-Credential" value="${model.credentials.amzCredential}" />
            <input type="hidden" name="X-Amz-Algorithm" value="${model.credentials.amzAlgorithm}" />
            <input type="hidden" name="X-Amz-Date" value="${model.credentials.amzDate}" />

            <input type="hidden" name="Policy" value="${model.credentials.policy}" />
            <input type="hidden" name="X-Amz-Signature" value="${model.credentials.signature}" />

            <div class="row">
                <div class="col-md-8">
                    <div class="form-group">
                        <input id="file" type="file" name="file"/>
                    </div>
                </div>
            </div>
        </form>
        <div class="row">
            <div class="col-md-8">
                <div class="form-group">
                    <button class="btn btn-default btn-block" type="button" onclick="uploadTeacher()">добавить</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    function uploadTeacher() {
        if (isDataValid()) {
            var teacherPhotoStorageName = generateTeacherPhotoStorageName();
            uploadTeacherDataToServer(teacherPhotoStorageName)
        }
    }

    function isDataValid() {
        return $('#name').val().length > 0;
    }

    function generateTeacherPhotoStorageName() {
        var fileName = document.getElementById("file").value;
        var extension = fileName.split('.').pop();
        return Date.now() + '.' + extension;
    }

    function uploadTeacherDataToServer(teacherPhotoStorageName) {
        $('#teacher-photo-storage-name').val(teacherPhotoStorageName);
        var form = $('#teacher-data-form')[0];
        var data = new FormData(form);
        $.ajax({
            url: '/admin/teachers',
            type: 'POST',
            enctype: 'multipart/form-data',
            data: data,
            contentType: false,
            processData: false,
            success: function () {
                uploadTeacherPhotoToStorage(teacherPhotoStorageName);
                resetForms();
            },
            error: function () {
                console.log('uploadTeacherDataToServer method error')
            }
        })
    }

    function uploadTeacherPhotoToStorage(teacherPhotoStorageName) {
        var keyInput = $('#key');
        var currentKeyValue = keyInput.val();
        keyInput.val(currentKeyValue + teacherPhotoStorageName);
        document.getElementById("teacher-photo-form").submit();
    }

    function resetForms() {
        $('#teacher-data-form')[0].reset();
        $('#teacher-photo-form')[0].reset();
    }

</script>
</body>