<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2019.5.14
  Time: 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>搜索</title>
    <script src="/js/front/jquery-3.2.1.js"></script>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="/js/front/jquery-3.2.1.js"></script>
    <link rel="stylesheet" href="/css/front/css.css"/>
    <link rel="stylesheet" href="/css/front/style.css"/>
</head>
<script type="text/javascript">
    /*聚集光标在输入框*/
    $(document).ready(function () {
        $("#searchInput").focus();
    })
</script>
<style>
    .firstTd p span {
        font-size: 0.8rem;
    }

    .firstTd p {
        margin-bottom: 2px;
        font-size: 0.9rem;
        font-weight: bold;
    }

    .table td {
        padding-top: 0.24rem;
    }

</style>
<body>
<div class="input-group mb-3">
    <input id="searchInput" type="text" class="form-control" placeholder="请输入您想获取内容的关键字"
           aria-label="Recipient's username" aria-describedby="basic-addon2">
    <div class="input-group-append">
        <button id="search" class="btn btn-outline-secondary" type="button">搜索</button>
    </div>
</div>
<div>
    <table class="table">
        <tbody id="content">
        </tbody>
    </table>
</div>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#search").on("click", function () {
            let searchInput = $("#searchInput").val().trim();
            if (searchInput == "") {
                alert("输入为空")
            } else {
                $.post("/search/searchSomething", {query: searchInput}, function (data) {
                    if (data == ""|| data == null){
                        $("#content").html(
                            "<tr><td class=\"firstTd\">哦噢，搜索不到关于"+searchInput+"的内容！</td></tr>"
                        )
                    }else{
                        $("#content").empty();
                        $.each(data, function (i, obj) {
                            $("#content").append("<tr>\n" +
                                "            <td class=\"firstTd\">\n" +
                                "                <p><span>"+obj.createTime+"</span>&nbsp;\n" +
                                "                    <span>"+obj.type+"</span></p>\n" +
                                "                <p><a href=\"/detail?no="+obj.no+"\">"+obj.title+"</a></p>\n" +
                                "                <p><span>"+obj.likeNum+"</span>&nbsp;<img class=\"littleIcon\" src=\"/images/front/love.svg\"/>&nbsp;\n" +
                                "                    </p>\n" +
                                "            </td>\n" +
                                "        </tr>")
                        });
                    }
                })
            }
        })
    })
</script>
</body>
</html>
