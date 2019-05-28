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
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="/js/front/jquery-3.2.1.js"></script>
    <link rel="stylesheet"  href="/css/front/css.css"/>
    <link rel="stylesheet"  href="/css/front/style.css"/>
</head>
<script type="text/javascript">
    /*聚集光标在输入框*/
    $(document).ready(function(){
        $("#searchInput").focus();
    })
</script>
<style>
    .firstTd p span{
        font-size: 0.8rem;
    }
    .firstTd p {
        margin-bottom: 2px;
        font-size: 1rem;
        font-weight: bold;
    }
    .table td {
        padding-top: 0.24rem;
    }

</style>
<body >
<div class="input-group mb-3">
    <input id = "searchInput" type="text" class="form-control" placeholder="请输入您想获取内容的关键字" aria-label="Recipient's username" aria-describedby="basic-addon2">
    <div class="input-group-append">
        <button class="btn btn-outline-secondary" type="button">搜索</button>
    </div>
</div>
<div id="content">
    <table class="table">
        <tbody>
        <tr>
            <td class="firstTd">
                <p><span>2019.05.08</span>&nbsp;
                    <span>攻略</span></p>
                <p><a href="detail.html">十佳球：西蒙斯鹰击长空点燃全场，利拉德绝杀无悬念领衔</a></p>
                <p><span>300</span>&nbsp;<img class="littleIcon"  src="/images/front/love.svg"/>&nbsp;
                    <span>68</span>&nbsp;<img class="littleIcon" src="/images/front/comment.svg"/></p>
            </td>
        </tr>
        <tr>
            <td class="firstTd">
                <p><span>2019.05.08</span>&nbsp;
                    <span>攻略</span></p>
                <p><a href="detail.html">利拉德50分超远三分绝杀雷霆，总比分4-1晋级下一轮</a></p>
                <p><span>300</span>&nbsp;<img class="littleIcon"  src="/images/front/love.svg"/>&nbsp;
                    <span>68</span>&nbsp;<img class="littleIcon" src="/images/front/comment.svg"/></p>
            </td>
        </tr>
        <tr>
            <td class="firstTd">
                <p><span>2019.05.08</span>&nbsp;
                    <span>攻略</span></p>
                <p><a href="detail.html">十佳球：西蒙斯鹰击长空点燃全场，利拉德绝杀无悬念领衔</a></p>
                <p><span>300</span>&nbsp;<img class="littleIcon"  src="/images/front/love.svg"/>&nbsp;
                    <span>68</span>&nbsp;<img class="littleIcon" src="/images/front/comment.svg"/></p>
            </td>
        </tr>
        <tr>
            <td class="firstTd">
                <p><span>2019.05.08</span>&nbsp;
                    <span>攻略</span></p>
                <p><a href="detail.html">利拉德50分超远三分绝杀雷霆，总比分4-1晋级下一轮</a></p>
                <p><span>300</span>&nbsp;<img class="littleIcon"  src="/images/front/love.svg"/>&nbsp;
                    <span>68</span>&nbsp;<img class="littleIcon" src="/images/front/comment.svg"/></p>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div class="progress">
    <div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 100%"></div>
</div>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>
