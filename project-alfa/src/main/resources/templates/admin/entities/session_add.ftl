<head>
    <meta charset="utf-8">
    <title>name project alfa. registration key requests</title>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    <link href="/css/registration-key-request-page.css" rel="stylesheet" type="text/css">
    <link href="/css/navbar.css" rel="stylesheet" type="text/css">
</head>
<body class="container">
<form action="/admin" method="get">
    <button>На главную</button>
</form>
<form action="/admin/sessions" method="get">
    <button>Назад к списку</button>
</form>
<form class="form-horizontal" method="post" action="/admin/sessions/add">
    <label>
        семестр
    </label>
    <input type="text" name="semester_number">

    <label for="university">университет</label>
    <select class="form-control" id="university" name="university_id" onchange="updateInstituteSelect(this.value)">
        <option value="-1" selected>выберите университет</option>
    <#list model.universities as university>
        <option id="${university?index}" value=${university.id}>${university.name}</option>
    </#list>
    </select>

    <div class="form-group">
        <label for="institute">институт</label>
        <select class="form-control" id="institute" name="institute_id"></select>
    </div>
    <input type="submit">
</form>


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