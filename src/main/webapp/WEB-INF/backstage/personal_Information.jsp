<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/personal_Information.css" rel="stylesheet">
    <script src="../../js/jquery.min.js" ></script>
    <script src="../../js/bootstrap.min.js" ></script>
    <script src="../../js/personal_Information.js" ></script>
    <title>我的资料-个人中心</title>
</head>
<body ondragstart="window.event.returnValue=false" oncontextmenu="window.event.returnValue=false" onselectstart="event.returnValue=false">
<div class="information">
    <%--获取用户头像路径--%>
        <h1>${requestScope.msg}</h1>
        <p id="path" hidden>${sessionScope.path}</p>
    <img src="${sessionScope.path}" alt="修改头像" class="img-thumbnail " id="portrait">
    <br><br><br>
    <form class="form-horizontal" action="/manager1/updateNamePicture"  method="POST" enctype="multipart/form-data">
        <%--<label for="file">更改头像</label>--%>
        <%--<input type="file" name="file" id="file"><br>--%>
            <div class="form-group">
                <label for="file" class="col-sm-2 control-label">更改头像</label>
                <div class="col-md-4">
                    <input type="file" name="file" id="file">
                </div>
                <a href="https://www.uupoop.com/zp/" style="font-size: 16px" target="_blank">在线修改尺寸</a>
            </div>
        <br><br>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">昵称</label>
            <div class="col-sm-8">
                <input type="text" class="form-control" id="name" name="name" placeholder="请输入要修改的昵称" required>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-8">
                <button type="button" class="btn btn-primary" id="change">修改</button>
            </div>
        </div>


    </form>
</div>
</body>
</html>