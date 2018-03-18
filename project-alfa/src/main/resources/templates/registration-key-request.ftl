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
            <form method="post" action="/registration-key-request" enctype="multipart/form-data" class="">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input class="form-control" name="name" placeholder="имя" type="text" required>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <input class="form-control" name="countOfKey" placeholder="количество ключей" type="number" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input class="form-control" name="surname" placeholder="фамилия" type="text" required>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <input class="form-control" name="course" placeholder="курс" type="text" required>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group">
                            <input class="form-control" name="email" placeholder="email" type="email" required>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group">
                            <input class="form-control" name="group" placeholder="номер группы" type="text" required>
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
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <label for="file">фото студенческого билета</label>
                            <input id="file" name="documentImageMultipartFile" type="file" accept="image/*" required/>
                            <img name="image" id="image" src="/images/temp-pic.jpg" alt="Stud Image" width="600" height="250">
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-group">
                            <button class="btn btn-default btn-block" type="submit">отправить запрос</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <script>

        document.getElementById('file').addEventListener('change', handleFileSelect, false);

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