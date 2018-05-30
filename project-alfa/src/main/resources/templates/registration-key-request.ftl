<html>
<head>
    <meta charset="utf-8">
    <title>name project alfa. registration key requests</title>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/js/UploadImage.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    <link href="/css/registration-key-request-page.css" rel="stylesheet" type="text/css">
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
                    <li><a href="/registration"><span class="glyphicon glyphicon-user"></span> регистрация</a></li>
                    <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> вход</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="key-form">
            <h3>запрос ключей регистрации</h3>
            <#if error??>
                <div class="alert alert-danger">${error}</div>
            </#if>
            <div id="key-alert"></div>
            <form id="key-data-form">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input id="name" class="form-control" name="name" placeholder="имя" type="text" required>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <input id="countOfKey" class="form-control" name="countOfKey" placeholder="количество ключей" type="number" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input id="surname" class="form-control" name="surname" placeholder="фамилия" type="text" required>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <input id="course" class="form-control" name="course" placeholder="курс" type="text" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input id="email" class="form-control" name="email" placeholder="email" type="email" required>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <input id="group" class="form-control" name="group" placeholder="номер группы" type="text" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="university">университет</label>
                            <select class="form-control" id="university" name="universityId" onchange="updateInstituteSelect(this.value)">
                                <option value="-1" selected>выберите университет</option>
                                <#list model.universities as university>
                                    <option id="${university?index}" value=${university.id}>${university.name}</option>
                                </#list>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <label for="institute">институт</label>
                            <select class="form-control" id="institute" name="instituteId"></select>
                        </div>
                    </div>
                </div>
                <input id="document-storage-name" type="hidden" name="documentStorageName" value="">
            </form>
            <form id="key-file-form" action="${model.credentials.serverUrl}" method="post" enctype="multipart/form-data">
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
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="file">фото студенческого билета</label>
                            <input id="file" name="file" type="file" accept="image/*" required/>
                            <img id="image" src="/images/temp-pic.jpg" alt="Stud Image" width="650" height="300">
                        </div>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="col-md-12">
                    <div class="form-group">
                        <button class="btn btn-default btn-block" type="button" onclick="uploadRegistrationKeyRequest()">отправить запрос</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>

        document.getElementById('file').addEventListener('change', handleFileSelect, false);

        function uploadRegistrationKeyRequest() {
            if (isDataValid()) {
                var documentStorageName = generateDocumentStorageName();
                uploadDocumentDataToServer(documentStorageName)
            } else {
                createMessage("Проверьте заполненность полей и попробуйте снова.", "danger");
            }
        }

        function isDataValid() {
            return $('#name').val().length > 0 && $('#countOfKey').val().length > 0 &&
                    $('#surname').val().length > 0 && $('#group').val().length > 0 &&
                    $('#course').val().length > 0 && $('#email').val().length > 0;
        }

        function generateDocumentStorageName() {
            var fileName = document.getElementById("file").value;
            var extension = fileName.split('.').pop();
            return Date.now() + '.' + extension;
        }

        function uploadDocumentDataToServer(documentStorageName) {
            $('#document-storage-name').val(documentStorageName);
            var form = $('#key-data-form')[0];
            var data = new FormData(form);
            $.ajax({
                url: '/registration-key-request', //todo
                type: 'POST',
                enctype: 'multipart/form-data',
                data: data,
                contentType: false,
                processData: false,
                success: function () {
                    uploadDocumentToStorage(documentStorageName);
                    resetForms();
                    createMessage("Запрос успешно отправлен! Еще ключей? :)", "success");
                    window.location.replace("http://p-alpha.website/registration-key-request-success");
                },
                error: function () {
                    createMessage("При загрузке файла произошла ошибка. Проверьте заполненность полей и попробуйте снова.", "danger");
                    console.log('uploadManualDataToServer method error')
                }
            })
        }

        function uploadDocumentToStorage(documentStorageName) {
            $('#key').val(documentStorageName);
            document.getElementById("key-file-form").submit();
        }

        function resetForms() {
            $('#key-data-form')[0].reset();
            $('#key-file-form')[0].reset();
            $("#image").attr("src","/images/temp-pic.jpg");
        }

        function createMessage(message, type) {
            clearMessage();
            $("#key-alert").append(
                    '<div class="alert alert-' + type + ' alert-dismissible">' +
                    '<a class="close" data-dismiss="alert" aria-label="close">&times;</a>' +
                    '<p>' + message + '</p>' +
                    '</div>'
            );
        }

        function clearMessage() {
            $("#key-alert").html("");
        }

        function updateInstituteSelect(instituteId) {
            clearInstituteSelect();
            if (instituteId !== '-1') {
                $.ajax({
                    url: '/api/university/' + instituteId + '/institutes',
                    type: 'GET',
                    dataType: 'json',
                    success: function (data) {
                        fillInstituteSelect(data);
                    },
                    error: function () {
                        console.log('getInstitutesByUniversityId method failed')
                    }
                })
            }
        }
        
        function clearInstituteSelect() {
            $("#institute").html("");
        }

        function fillInstituteSelect(data) {
            var institutes = data;
            var instituteSelect = document.getElementById("institute");
            for (var i = 0; i < institutes.length; i++) {
                var currentInstitute = institutes[i];
                var option = document.createElement("option");
                option.value = currentInstitute.id;
                var instituteNameText = document.createTextNode(currentInstitute.name);
                option.appendChild(instituteNameText);
                instituteSelect.appendChild(option);
            }
        }
    </script>
</body>
</html>