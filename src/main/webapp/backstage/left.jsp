<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/left.css" rel="stylesheet">
    <script src="../js/jquery.min.js" ></script>
    <script src="../js/bootstrap.min.js" ></script>
    <script src="../js/left.js" ></script>
    <title>Title</title>
</head>
<body>
<div class="content">
    <!--标题-->
    <div class="page-header">
        <h2>百宝箱</h2>
    </div>

    <!--功能选项-->
    <ul class="nav nav-pills nav-stacked" id="func">
        <h3 style="color: white">管理功能</h3>
        <li><a href="article.jsp" target="mainframe">推文管理</a></li>
        <li><a href="rights_management.jsp" target="mainframe">权限管理</a></li>
        <li><a href="school.jsp" target="mainframe">学校管理</a></li>
        <li><a href="editpwd.jsp" target="mainframe">密码管理</a></li>
        <h3 style="color: white">账号信息</h3>
        <li><a href="personal_Information.jsp" target="mainframe">个人资料</a></li>
        <li><a href="cancellation.jsp" target="mainframe">账号注销</a></li>
        <hr>
        <li><a href="main.jsp" target="mainframe">使用说明书</a></li>
    </ul>
</div>
</body>
</html>