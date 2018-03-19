<html>
<head>
    <meta charset="utf-8">
    <title>name project alfa. success request</title>
    <link href="/bootstrap/bootstrap-3.3.2-dist/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="/js/jquery.js"></script>
    <script src="/bootstrap/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
    <link href="/css/navbar.css" rel="stylesheet" type="text/css">
    <link href="/css/success-message.css" rel="stylesheet" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="http://cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js"></script>
    <script>
    </script>
    <script language="javascript" type="text/javascript">
        jQuery(function ($) {

            $( document ).ready(function doConnect() {
                websocket = new SockJS("p-alpha/website:80/echoHandler");
            });



            $ ('#send')
                    .click(function () {
                        if(typeof websocket != 'undefined') {
                            websocket.send("There is another 1 new request, senior!");
                        } else {
                            alert("You`ve already send it");
                        }
                        if (typeof websocket != 'undefined') {
                            websocket.close();
                            websocket = undefined;
                        }
                    });

        });
    </script>
</head>
<body class="container">

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
                </ul>
            </div>
        </div>
    </nav>
    <div class="success-message">
        <div class="row">
            <div class="col-md-6">
                <img name="image" id="image" src="/images/request-success.gif" alt="Success Image" width="400" height="400">
            </div>
            <div class="col-md-6">
                <h3>Ваш запрос успешно отправлен на обработку!</h3>
                <p>После проверки заявки модератором на указанный Вами адрес будет отправлено письмо.</p>
                <button id = "send">Уведомить администратора</button>
            </div>
        </div>
    </div>
</body>
</html>