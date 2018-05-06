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
                <a href="/admin/manuals/"><span class="glyphicon glyphicon-circle-arrow-left"></span></a>
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


<div class="container">
    <div class="key-form">
        <h3>Добавление материалов</h3>

        <div id="manual-alert"></div>
        <form id="manual-data-form">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <input class="form-control" name="title" id="title" placeholder="Заголовок" type="text" required>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group">
                        <input class="form-control" name="author" id="author" placeholder="Автор" type="text" required>
                    </div>
                </div>
            </div>

            <input id="manual-storage-name" type="hidden" name="fileStorageName" value="">

            <div class="row">
                <div class="col-md-6">
                    <div class="form-group">
                        <select class="form-control" id="exam" name="examId" title="Экзамен">
                        <#list model.exams as exam>
                            <option value="${exam.id}">${exam.institute.name} ${exam.semesterNumber} ${exam.subject.name} ${exam.teacher.name}</option>
                        </#list>
                        </select>
                    </div>
                </div>
            </div>
        </form>
        <form id="manual-file-form" action="${model.credentials.serverUrl}" method="post" enctype="multipart/form-data">
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
            <div class="col-md-12">
                <div class="form-group">
                    <button class="btn btn-default btn-block" type="button" onclick="uploadManual()">добавить</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    function uploadManual() {
        if (isDataValid()) {
            var manualStorageName = generateManualStorageName();
            uploadManualDataToServer(manualStorageName);
        }
    }

    function isDataValid() {
        return $('#title').val().length > 0 && $('#author').val().length > 0;
    }

    function generateManualStorageName() {
        var fileName = document.getElementById("file").value;
        var extension = fileName.split('.').pop();
        return Date.now() + '.' + extension;
    }

    function uploadManualDataToServer(manualStorageName) {
        $('#manual-storage-name').val(manualStorageName);
        var form = $('#manual-data-form')[0];
        var data = new FormData(form);
        $.ajax({
            url: '/admin/manuals/add',
            type: 'POST',
            enctype: 'multipart/form-data',
            data: data,
            contentType: false,
            processData: false,
            success: function () {
                uploadManualToStorage(manualStorageName);
                resetForms();
                createMessage("Файл успешно добавлен!", "success");
            },
            error: function () {
                createMessage("При загрузке файла произошла ошибка. Проверьте заполненность полей и попробуйте снова.", "danger");
                console.log('uploadManualDataToServer method error')
            }
        })
    }

    function uploadManualToStorage(manualStorageName) {
        var keyInput = $('#key');
        var currentKeyValue = keyInput.val();
        keyInput.val(currentKeyValue + manualStorageName);
        document.getElementById("manual-file-form").submit();
    }

    function resetForms() {
        $('#manual-data-form')[0].reset();
        $('#manual-file-form')[0].reset();
    }

    function createMessage(message, type) {
        clearMessage();
        $("#manual-alert").append(
                '<div class="alert alert-' + type + ' alert-dismissible">' +
                '<a class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                '<p>' + message + '</p>' +
                '</div>'
        );
    }

    function clearMessage() {
        $("#manual-alert").html("");
    }
</script>
</body>