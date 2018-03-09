<html>
<head>
    <meta charset="utf-8">
    <title>admin panel: registration key requests</title>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
</head>
<body>
<h2>Registration keys requests</h2>
<ul id="requests-list-group" class="list-group">
    <#list model.requests as currentRequest>
        <li class="list-group-item">
            <div class="row">
                <div class="col-md-3">
                    <h3>${currentRequest.surname} ${currentRequest.name}</h3>
                    <img src="/file/${currentRequest.documentImage.fileInfo.id}" class="pull-left" height="auto" width="300"/>
                </div>
                <div class="col-md-4">
                    <p>University: ${currentRequest.university}</p>
                    <p>Institute: ${currentRequest.institute}</p>
                    <p>Course: ${currentRequest.course}</p>
                    <p>Group: ${currentRequest.group}</p>
                </div>
                <div class="col-md-4">
                    <p>Email: ${currentRequest.email}</p>
                    <p>Count: ${currentRequest.countOfKey}</p>
                </div>
                <div class="col-md-1">
                    <button class="btn btn-success" onclick="acceptRequest(${currentRequest.id})">
                        Accept
                    </button>
                    <button class="btn btn-danger" onclick="denyRequest(${currentRequest.id})">
                        Deny
                    </button>
                </div>
            </div>
        </li>
    </#list>
</ul>
<script>
    // fillRequestList(model.requests);

    function acceptRequest(requestId) {
        $.ajax({
            url: "/api/admin/accept-key-request/" + requestId,
            type: "GET",
            success: function (data) {
                fillRequestList(data)
            },
            error: function () {
                console.error("Error in request's accepting function")
            }
        })
    }

    function denyRequest(requestId) {
        $.ajax({
            url: "/api/admin/deny-key-request/" + requestId,
            type: "GET",
            success: function (data) {
                fillRequestList(data)
            },
            error: function () {
                console.error("Error in request's accepting function")
            }
        })
    }

    function fillRequestList(data) {
        $("#requests-list-group").html("");
        for (var i = 0; i < data.length; i++) {
            var currentRequest = data[i];
            $("#requests-list-group").append(
                    '<li class="list-group-item">' +
                        '<div class="row">' +
                            '<div class="col-md-3">' +
                                '<h3>' + currentRequest.surname + ' ' + currentRequest.name + '</h3>' +
                                '<img src="/file/' + currentRequest.documentImage.fileInfo.id + '" class="pull-left" height="auto" width="300"/>' +
                            '</div>' +
                            '<div class="col-md-4">' +
                                '<p>University: ' + currentRequest.university + '</p>' +
                                '<p>Institute: ' + currentRequest.institute + '</p>' +
                                '<p>Course: ' + currentRequest.course + '</p>' +
                                '<p>Group: ' + currentRequest.group + '</p>' +
                            '</div>' +
                            '<div class="col-md-4">' +
                                '<p>Email: ' + currentRequest.email + '</p>' +
                                '<p>Count: ' + currentRequest.countOfKey + '</p>' +
                            '</div>' +
                            '<div class="col-md-1">' +
                                '<button class="btn btn-success" onclick="acceptRequest(' + currentRequest.id+ ')">' +
                                    'Accept' +
                                '</button>' +
                                '<button class="btn btn-danger" onclick="denyRequest(' + currentRequest.id+ ')">' +
                                    'Deny' +
                                '</button>' +
                            '</div>' +
                        '</div>' +
                    '</li>'
            );
        }
    }
</script>
</body>
</html>