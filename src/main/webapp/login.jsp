<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" class="no-js">

<head>

    <meta charset="utf-8">
    <title>小码哥分校教育管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- CSS -->
    <link rel="shortcut icon" href="/static/plugins/assets/img/favicon.ico" type="image/x-icon" />
    <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
    <link rel="stylesheet" href="/static/loginPage/assets/css/reset.css">
    <link rel="stylesheet" href="/static/loginPage/assets/css/supersized.css">
    <link rel="stylesheet" href="/static/loginPage/assets/css/style.css">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
    <script type="text/javascript" src="/static/plugins/easyui/jquery.min.js"></script>
    <jsp:include page="/play/fireworkd.jsp"></jsp:include>
    <script type="text/javascript">
        function submitForm() {
            if ($("[name='username']").val() && $("[name='password']").val()) {
                $("form").submit();
            }
        }

        //按回车键直接提交表单
        $(document).keyup(function (event) {
            //判断是否是回车键
            if (event.keyCode == 13) {
                //提交表单
                submitForm();
            }
        });


        $(function () {
            //判断是否有错误信息
            var msg = '${errorMsg}';
            if (msg) {
                alert(msg);
            }
        })

    </script>

</head>

<body style="background:#000;margin-top:100px">

<div class="page-container">
    <h1>Login</h1>
    <form action="/login.do" method="post">
        <input type="text" name="username" class="username" value="root" placeholder="Username">
        <input type="password" name="password" class="password" value="666" placeholder="Password">
        <button type="submit" onclick="submitForm()">Sign me in</button>
        <div class="error"><span>+</span></div>
    </form>
    <div class="connect">
        <p>Or connect with:</p>
        <p>
            <a class="facebook" href=""></a>
            <a class="twitter" href=""></a>
        </p>
    </div>
</div>
<div align="center"><p>Copyright 广东工业大学</p></div>

<!-- Javascript -->
<script src="/static/loginPage/assets/js/jquery-1.8.2.min.js"></script>
<script src="/static/loginPage/assets/js/supersized.3.2.7.min.js"></script>
<script src="/static/loginPage/assets/js/supersized-init.js"></script>
<script src="/static/loginPage/assets/js/scripts.js"></script>


</body>

</html>

