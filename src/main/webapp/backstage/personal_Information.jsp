<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/personal_Information.css" rel="stylesheet">
    <script src="../js/jquery.min.js" ></script>
    <script src="../js/bootstrap.min.js" ></script>
    <title>我的资料-个人中心</title>
</head>
<body>
<div class="information">
    <img src="../images/a7691515_s.jpg" alt="修改头像" class="img-thumbnail ">
    <form class="form-horizontal" role="form">
        <div class="form-group">
            <label for="firstname" class="col-sm-2 control-label">昵称</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="firstname">
            </div>
        </div>
        <div class="form-group">
            <label for="qianming" class="col-sm-2 control-label">个性签名</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="qianming">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-8">
                <button type="submit" class="btn btn-default">修改</button>
            </div>
        </div>


    </form>
</div>
</body>
</html>