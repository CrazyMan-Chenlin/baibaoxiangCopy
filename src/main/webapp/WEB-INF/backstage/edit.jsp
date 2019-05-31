<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <!-- Include external CSS. -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.css">

    <!-- Include Editor style. -->
    <link href="https://cdn.jsdelivr.net/npm/froala-editor@2.9.5/css/froala_editor.pkgd.min.css" rel="stylesheet" type="text/css" />
    <link href="https://cdn.jsdelivr.net/npm/froala-editor@2.9.5/css/froala_style.min.css" rel="stylesheet" type="text/css" />

    <link href="../css/edit.css" rel="stylesheet" />
    <script src="../../js/jquery.min.js" ></script>
    <script src="../../js/bootstrap.min.js" ></script>
</head>

<body>
<p id="username" hidden>${sessionScope.username}</p>
<p id="area" hidden>${sessionScope.area}</p>
<form class="form-horizontal" role="form" id="edit_form">
    <div class="form-group ">
        <label for="title" class="col-sm-2 control-label">标题</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="title" placeholder="请输入标题" required="required">
        </div>
    </div>
    <div class="form-group ">
        <label for="type" class="col-sm-2 control-label">类型</label>
        <div class="col-sm-4">
            <input type="text" class="form-control" id="type" placeholder="请输入类型">
        </div>
    </div>
    <%--<div class="form-group">--%>
        <%--<label for="area" class="col-sm-2 control-label">学校区域</label>--%>
        <%--<div class="col-sm-4">--%>
            <%--<input type="text" class="form-control" id="area" placeholder="请输入学校">--%>
        <%--</div>--%>
    <%--</div>--%>
    <textarea id="message"></textarea>
    <input type="button" class="submitVal" value="发布">
</form>




<!-- Include external JS libs. -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/codemirror.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.25.0/mode/xml/xml.min.js"></script>

<!-- Include Editor JS files. -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/froala-editor@2.9.5/js/froala_editor.pkgd.min.js"></script>
<script type="text/javascript" src="../js/edit.js"></script>
</body>
</html>