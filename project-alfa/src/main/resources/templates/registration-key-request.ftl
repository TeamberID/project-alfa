<html>
<head>
    <meta charset="utf-8">
    <title>name project alfa. registration key requests</title>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body>
    <h2>Send request for registration keys</h2>
    <#if error??>
            <div class="alert alert-danger">${error}</div>
    </#if>
    <form method="post" action="/registration-key-request" enctype="multipart/form-data">
        <div class="form-group">
            <input class="form-group" name="name" placeholder="name" type="text" required>
        </div>
        <div class="form-group">
            <input class="form-group" name="surname" placeholder="surname" type="text" required>
        </div>
        <div class="form-group">
            <input class="form-group" name="email" placeholder="email" type="email" required>
        </div>
        <div class="form-group">
            <input class="form-group" name="countOfKey" placeholder="count of keys" type="number" required>
        </div>
        <div class="form-group">
            <label for="university">University</label>
            <select class="form-control" id="university" name="universityId" onchange="updateInstituteSelect(this.value)">
                <option value="-1" selected>Выберите университет</option>
                <#list model.universities as university>
                    <option id="${university?index}" value=${university.id}>${university.name}</option>
                </#list>
            </select>
        </div>
        <div class="form-group">
            <label for="institute">Institute</label>
            <select class="form-control" id="institute" name="instituteId"></select>
        </div>
        <div class="form-group">
            <input class="form-group" name="course" placeholder="course" type="text" required>
        </div>
        <div class="form-group">
            <input class="form-group" name="group" placeholder="group number" type="text" required>
        </div>
        <div class="form-group">
            <input name="documentImageMultipartFile" type="file" accept="image/*" required/>
        </div>
        <div class="form-group">
            <button class="btn btn-lg btn-primary btn-block" type="submit" >Send Request</button>
        </div>
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
</html>