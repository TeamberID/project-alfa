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
            <input class="form-group" name="university" placeholder="university" type="text" required>
        </div>
        <div class="form-group">
            <input class="form-group" name="institute" placeholder="institute" type="text" required>
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
</body>
</html>