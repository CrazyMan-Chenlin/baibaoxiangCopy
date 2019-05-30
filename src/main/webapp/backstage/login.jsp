<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <script src="../js/jquery.min.js" ></script>
    <script src="../js/bootstrap.min.js" ></script>
    <script src="../js/login.js" ></script>
</head>
<body>
<form class="form-horizontal" role="form">
    <div class="form-group">
        <label for="username" class="col-sm-2 control-label">用户名</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-6">
            <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">验证码</label>
        <div class="col-sm-6">
            <input type="text" class="form-control" name="validatecode" id="validatecode" placeholder="请输入验证码"><br>
            <img src="/manager1/checkCode" width="180" height="30" class="textinput" style="height:30px;" id="img" />
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-6 col-sm-10">
            <button type="button" class="btn btn-primary" id="login">登录</button>
        </div>
    </div>
</form>
</body>
</html>
