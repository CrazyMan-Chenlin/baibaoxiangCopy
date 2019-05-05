<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../../css/bootstrap.min.css" rel="stylesheet">
    <link href="../../css/rights_management.css" rel="stylesheet">
    <script src="../../js/jquery.min.js" ></script>
    <script src="../../js/bootstrap.min.js" ></script>
    <title>权限管理</title>
</head>
<body>
<!--控制表单样式的div-->
<div class="table_management">
    <table class="table table-bordered">
        <caption>地区管理人员</caption>
        <thead>
        <tr>
            <th>管理员ID</th>
            <th>所属地区</th>
            <th>学校</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td><input type="checkbox" name="cur_right">aaa</td>
            <td>花都校区</td>
            <td>广东第二师范学院</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="cur_right">aaa</td>
            <td>花都校区</td>
            <td>广东第二师范学院</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="cur_right">aaa</td>
            <td>花都校区</td>
            <td>广东第二师范学院</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="cur_right">aaa</td>
            <td>花都校区</td>
            <td>广东第二师范学院</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="cur_right">aaa</td>
            <td>花都校区</td>
            <td>广东第二师范学院</td>
        </tr>
        <tr>
            <td><input type="checkbox" name="cur_right">aaa</td>
            <td>花都校区</td>
            <td>广东第二师范学院</td>
        </tr>
        </tbody>
    </table>
</div>
<br><br>

<!--控制底部按钮样式的div-->
<div class="btn_management">
    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">删除所选管理员</button>
    <!-- 弹出对话框-->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-hidden="true">×
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        警告!
                    </h4>
                </div>
                <div class="modal-body">
                    数据一旦删除将无法恢复（按下 ESC 按钮退出。）
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">关闭
                    </button>
                    <button type="button" class="btn btn-primary">
                        删除
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

</div>
</body>
</html>