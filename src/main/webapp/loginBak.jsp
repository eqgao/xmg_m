<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>小码哥客户关系管理系统</title>
    <link rel="shortcut icon" href="/static/plugins/assets/img/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="/static/css/style.css">
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
<div style="width: 100%; height: 100%; background-image: 'url(/play/timg.gif)';background-color: black">
    <section class="container">
        <div class="login">
            <h1>用户登录</h1>
            <form method="post" action="/login.do">
                <p><input type="text" name="username" value="root" placeholder="账号"></p>
                <p><input type="password" name="password" value="666" placeholder="密码"></p>
                <p class="submit">
                    <input type="button" value="登录" onclick="submitForm()">
                    <input type="button" value="重置">
                </p>
            </form>
        </div>
    </section>
    <div style="text-align:center;" class="login-help">
        <p>Copyright 广东工业大学</p>
    </div>
</div>
</body>
</html>