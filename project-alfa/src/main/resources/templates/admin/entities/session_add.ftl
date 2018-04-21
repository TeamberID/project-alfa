<head>
    <meta charset="utf-8">
    <title>name project alfa. registration key requests</title>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    <link href="/css/registration-key-request-page.css" rel="stylesheet" type="text/css">
    <link href="/css/navbar.css" rel="stylesheet" type="text/css">
</head>


<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid" style="color: #9acfea">
        <div class="navbar-header">
            <div class="navbar-brand">
                <a href="/admin/sessions"><span class="glyphicon glyphicon-circle-arrow-left"></span></a>
            </div>

            <div class="navbar-brand">
            </div>

            <div class="navbar-brand">
                <a href="/admin">Admin mode</a>
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

<br><br><br>
<div class="container">
    <div class="key-form">
        <form class="form-horizontal" method="post" action="/admin/sessions/add">


            <div class="row">


                <div class="col-md-8">
                    <label for="university">университет</label>
                    <select class="form-control" id="university" name="university_id"
                            onchange="updateInstituteSelect(this.value)">
                        <option value="-1" selected>выберите университет</option>
                    <#list model.universities as university>
                        <option id="${university?index}" value=${university.id}>${university.name}</option>
                    </#list>
                    </select>
                </div>
            </div>
            <div class="row">

                <div class="col-md-8">
                    <div class="form-group">
                        <label for="institute">институт</label>
                        <select class="form-control" id="institute" name="institute_id"></select>
                    </div>
                </div>

            </div>

            <div class="row">

                <div class="col-md-8">
                    <label>
                        семестр
                    </label>
                    <input type="text" name="semester_number">
                </div>
            </div>
            <div class="row">

                <div class="col-md-8">
                    <input type="submit">
                </div>

            </div>

    </div>
    </form>
</div>
</div>


<script>

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