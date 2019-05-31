<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/personal_Information.css" rel="stylesheet">
    <script src="../../js/jquery.min.js" ></script>
    <script src="../../js/bootstrap.min.js" ></script>
    <title>我的资料-个人中心</title>
</head>
<body>
<div class="information">
    <img src="../../images/a7691515_s.jpg" alt="修改头像" class="img-thumbnail ">
    <br><br><br>
    <form class="form-horizontal" action="/manager1/updateNamePicture"  method="POST" enctype="multipart/form-data">
        更改头像<input type="file" name="path"  id="path"><br>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">昵称</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="name" name="name">
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