<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/article.css" rel="stylesheet">

    <script src="../js/jquery.min.js" ></script>
    <script src="../js/bootstrap.min.js" ></script>
    <script src="../js/article.js"></script>
    <title>推文管理</title>
</head>
<body>
<div class="container">
    <!--  搜索栏  -->
    <div class="top">
    <div class="col-md-6" id="search">
        <div class="input-group">
            <input type="text" class="form-control">
            <span class="input-group-btn">
                        <button class="btn btn-default" type="button">Go!</button>
                    </span>
        </div>
    </div>
    <a href="/backstage/edit.jsp" target="mainframe" class="new">编写新推文</a>
    </div>
    <br><br><br>
    <div class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" id="classification">
            文章分类
            <b class="caret"></b>
        </a>
        <ul class="dropdown-menu">
        </ul>
    </div>

<div class="" id="articles"></div>



</ul>
</div>





</body>
</html>