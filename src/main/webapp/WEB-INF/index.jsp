<%--
  Created by IntelliJ IDEA.
  User: chenlin
  Date: 2019.5.12
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>百宝箱</title>
    <meta name="viewport"
          content=" height = device-height,
                    width = device-width,
                    initial-scale = 1.0,
                    minimum-scale = 1.0,
                    maximum-scale = 1.0,
                    user-scalable = no"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/front/css.css"/>
    <link rel="stylesheet" href="/css/front/style.css"/>
    <script src="/js/front/jquery-3.2.1.js"></script>
    <script src="/js/front/js.js"></script>
</head>
<body>
<div id="showSearch" style="width:100%;position: absolute;z-index: 1">
    <div id="nav" class="pos-f-t">
        <nav class="navbar navbar-dark bg-dark" style="height:2.1rem">
            <button class="navbar-toggler" type="button" data-toggle="collapse"
                    data-target="#navbarToggleExternalContent" aria-controls="navbarToggleExternalContent"
                    aria-expanded="false" aria-label="Toggle navigation">
                <!--<span class="navbar-toggler-icon"></span>-->
                <img src="/images/front/area.svg"/>
            </button>
            <h5 class="text-white" style="margin: auto">百宝箱</h5>
            <a href="search.html"><img src="/images/front/search.svg"></a>
        </nav>
    </div>
    <div class="find_nav">
        <div class="find_nav_left">
            <div class="find_nav_list">
                <ul>
                    <c:forEach items="${articleTypeList}" var="articleType">
                        <li><a href="javascript:void(0)">${articleType.type}</a></li>
                    </c:forEach>
                    <li class="sideline"></li>
                </ul>
            </div>
        </div>
    </div>
    <table class="table">
        <tbody>
        <c:forEach items="${articleList}" var="article">
            <tr>
                <td class="firstTd">
                    <div class="Title"><a href="/detail?no=${article.no}">${article.title}</a></div>
                    <div>
                        <img class="littleIcon" src="/images/front/love.svg"/>
                        &nbsp;<span>${article.likeNum}</span>
                    </div>
                </td>
                <td width="30%">
                    <img src="/images/front/dali.jpg" class="rounded " alt="..."></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div id="warn" style="position:fixed; bottom:0;z-index:999;margin-bottom: 0;display: none"
     class="alert alert-warning alert-dismissible fade show" role="alert">
    为了您更好的浏览体验，我们建议您点击右上角，使用浏览器访问
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<div id="chooseSchool" style="position: absolute;z-index: 2;left: 600px">
    <div style="margin-left: 2rem;padding-top:1.2rem;padding-bottom: 1rem"><span>地区选择：</span></div>
    <div id="school" class="input-group mb-3">
        <span>学校：</span>
        <select id="schoolName" class="custom-select" id="inputGroupSelect02" STYLE="padding-left: 1rem">
            <c:forEach items="${schoolName}" var="schoolName">
                <option value="${schoolName}">${schoolName}</option>
            </c:forEach>
        </select>
    </div>
    <div id="area" class="input-group mb-3">
        <span>校区：</span>
        <select id="areaName" class="custom-select" id="inputGroupSelect03" STYLE="padding-left: 1rem;">
            <c:forEach items="${areaName}" var="areaName">
                <option value="${areaName}">${areaName}</option>
            </c:forEach>
        </select>
    </div>
</div>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var $chooseSchool = $("#chooseSchool");
        /*展示地址栏*/
        $(".navbar-toggler").on("click", function () {
            if ($chooseSchool.css("left") != '0px') {
                $chooseSchool.css("display", "block")
                $($chooseSchool).animate({"left": 0}, 500);
            }
        })
        /*退出地址栏*/
        $("body").on("click", function (e) {
            var target = $(e.target);
            if (!target.is($("#areaName")) && !target.is($("#schoolName"))) {
                if ($chooseSchool.css("left") == '0px') {
                    $($chooseSchool).animate({"left": 600}, 500);
                    setTimeout(function f() {
                        $chooseSchool.css("display", "none")
                    }, 500)
                }
            }
        })
        //ajax请求学校地址
        $(" #schoolName").change(function () {
            var schoolName = $("#schoolName").val();
            $.post("/index/queryAreaName", {schoolName: schoolName}, function (data) {
                $("#areaName").empty();
                for (var i in data) {
                    $("#areaName").append("<option value=" + data[i] + ">" + data[i] + "</option>")
                }
                if (schoolName != '广东第二师范学院') {
                    var schoolName = $("#schoolName").val();
                    var schoolArea = $("#areaName").val();
                    $.post("/index/changeAreaArticle", {
                        area: schoolName + schoolArea,
                        type: $(".find_nav_cur").text()
                    }, function (data) {
                        $(".table tbody").empty();
                        $.each(data, function (i, article) {
                            $(".table tbody").append("<tr>\n" +
                                "            <td class=\"firstTd\">\n" +
                                "                <div  class=\"Title\"><a href=\"/detail?no=" + article.no + "\">" + article.title + "</a></div>\n" +
                                "                <div>\n" +
                                "                    <img class=\"littleIcon\" src=\"/images/front/love.svg\"/>\n" +
                                "                    &nbsp;<span>" + article.likeNum + "</span>\n" +
                                "                </div>\n" +
                                "            </td>\n" +
                                "            <td width=\"30%\">\n" +
                                "                <img src=\"/images/front/dali.jpg\" class=\"rounded \" alt=\"...\"></td>\n" +
                                "        </tr>")
                        })
                    })
                }
            });
        });
        $(".find_nav_list li ").on('click', function () {
            $.post("/index/getAreaArticle", {
                type: $(this).text(),
                area: $("#schoolName").val() + $("#areaName").val()
            }, function (data) {
                $(".table tbody").empty();
                $.each(data, function (i, article) {
                    $(".table tbody").append("<tr>\n" +
                        "            <td class=\"firstTd\">\n" +
                        "                <div  class=\"Title\"><a href=\"/detail?no=" + article.no + "\">" + article.title + "</a></div>\n" +
                        "                <div>\n" +
                        "                    <img class=\"littleIcon\" src=\"/images/front/love.svg\"/>\n" +
                        "                    &nbsp;<span>" + article.likeNum + "</span>\n" +
                        "                </div>\n" +
                        "            </td>\n" +
                        "            <td width=\"30%\">\n" +
                        "                <img src=\"/images/front/dali.jpg\" class=\"rounded \" alt=\"...\"></td>\n" +
                        "        </tr>")
                })
            });

        })
        $("#areaName").change(function () {
            var schoolName = $("#schoolName").val();
            var schoolArea = $("#areaName").val();
            $.post("/index/changeAreaArticle", {
                area: schoolName + schoolArea,
                type: $(".find_nav_cur").text()
            }, function (data) {
                $(".table tbody").empty();
                $.each(data, function (i, article) {
                    $(".table tbody").append("<tr>\n" +
                        "            <td class=\"firstTd\">\n" +
                        "                <div  class=\"Title\"><a href=\"/detail?no=" + article.no + "\">" + article.title + "</a></div>\n" +
                        "                <div>\n" +
                        "                    <img class=\"littleIcon\" src=\"/images/front/love.svg\"/>\n" +
                        "                    &nbsp;<span>" + article.likeNum + "</span>\n" +
                        "                </div>\n" +
                        "            </td>\n" +
                        "            <td width=\"30%\">\n" +
                        "                <img src=\"/images/front/dali.jpg\" class=\"rounded \" alt=\"...\"></td>\n" +
                        "        </tr>")
                })
            });
        })
        var ua = navigator.userAgent.toLowerCase();
        var isWeixin = ua.indexOf('micromessenger') != -1;
        $(".table").on('click', 'a', function () {
            sessionStorage.setItem("left", $(".sideline").css("left"))
            sessionStorage.setItem("left2", $(".find_nav_list").css("left"))
            sessionStorage.setItem("width", $(".sideline").css("width"))
            var json = new Date().getTime();
            history.pushState({json}, '', window.location.href + "#" + json);
            sessionStorage.setItem("MainContent", $("tbody").html())
        })
        $(function () {
            $("#warn").css("display", "block");
                if (sessionStorage.getItem("MainContent") != null && sessionStorage.getItem("MainContent") != "") {
                    window.history.back(-1)
                    $("tbody").html(sessionStorage.getItem("MainContent"))
                    sessionStorage.setItem("MainContent", "")
                }
        })
        window.onpopstate = function (e) {
            $(".find_nav_list li").first().removeClass("find_nav_cur");
            $(".find_nav_list li").eq(sessionStorage.index).addClass("find_nav_cur")
            $(".sideline").css("left", sessionStorage.getItem("left"))
            sessionStorage.setItem("left", "")
            $(".sideline").css("width", sessionStorage.getItem("width"))
            sessionStorage.setItem("width", "")
            $(".find_nav_list").css("left", sessionStorage.getItem("left2"))
            sessionStorage.setItem("left2", "")
        }
    })
</script>
</body>
</html>
