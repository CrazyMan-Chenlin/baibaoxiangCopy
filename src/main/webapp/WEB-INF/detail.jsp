<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
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
    <link rel="stylesheet" href="/css/front/style.css"/>
    <script src="/js/front/jquery-3.2.1.js"></script>
</head>
<style>
    .rounded-circle {
        height: 2.5rem;
        width: 2.5rem;
    }

    #nav {
        height: 3rem;
        text-align: center;
    }

    #nav img {
        width: 1rem;
        height: 1rem;
        margin-top: 1rem;
        margin-left: 1rem;
        margin-right: 1rem;
    }

    #message {
        margin: 1rem;
        height: 3rem;
    }

    hr {
        margin: 0;
    }

    #content {
        margin: 0.8rem;
    }

    #content p {
        font-size: 1.1rem;
        text-indent: 1.5em
    }

    .rounded {
        height: 100%;
    }
</style>
<body>
<div id="nav">
    <div id="return" style="width: 15%;float: left;height: 100%;" onclick="javascript:history.back(-1)"><img src="/images/front/left.svg"></div>
    <div style="float: left;width: 70%">
        <p style="font-size: 1.2rem;margin-top: 0.62rem;margin-bottom: 0;color: #141414">${article.type}</p>
    </div>
    <div style="width: 15%;float: left;height: 100%"><img src="/images/front/share.svg"></div>
</div>
<hr>
<div id="title" style="margin: 0.8rem">
    <h5 style="font-size: 1.2rem;color:#141414;line-height:1.5;font-weight: bold">${article.title}</h5>
</div>
<div id="message">
    <div style="float: left;width: 15%"><img src="/images/front/who.jpg" class="rounded-circle"></div>
    <div style="float: left;font-size: 0.8rem;width: 80%;margin-top: 0.1rem" class="text-dark"><span
            style="">${article.author}</span></div>
    <div style="float: left;font-size: 0.7rem;width: 80%;margin-top: 0.15rem" class="text-muted"><span style="">${timeFormat} 阅读量：${article.readNum}</span>
    </div>
</div>
<div id="content">
    <img style="width: 100%;margin-bottom: 1rem" src="/images/front/killer.jpg" class="rounded mx-auto d-block"/>
        ${article.message}
</div>
<div style="text-align: right;margin-right: 1rem;margin-bottom: 0.7rem">
    <span class="text-muted" style="font-size: 0.75rem">点赞</span>
    <img id="love" src="/images/front/love2.svg"/>
</div>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $("#love").on("click", function () {
            $("#love").attr("src", "/images/front/love.svg")
        })
        $("#return img").on("touchend", function () {
            $("#return").css("background-color","#cccccc")
            setTimeout(function f(){$("#return").css("background-color","#ffffff")},100)
        })
    })
</script>
</body>
</html>