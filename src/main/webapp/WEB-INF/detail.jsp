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
    <link rel="stylesheet" href="../css/style.css"/>
    <script src="../js/jquery-3.2.1.js"></script>
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
    <div id="return" style="width: 15%;float: left;height: 100%;" onclick="javascript:history.back(-1)"><img src="../img/left.svg"></div>
    <div style="float: left;width: 70%">
        <p style="font-size: 1.2rem;margin-top: 0.62rem;margin-bottom: 0;color: #141414">校内社团</p>
    </div>
    <div style="width: 15%;float: left;height: 100%"><img src="../img/share.svg"></div>
</div>
<hr>
<div id="title" style="margin: 0.8rem">
    <h5 style="font-size: 1.2rem;color:#141414;line-height:1.5;font-weight: bold">十佳球：西蒙斯鹰击长空点燃全场，利拉德绝杀无悬念领衔</h5>
</div>
<div id="message">
    <div style="float: left;width: 15%"><img src="../img/who.jpg" class="rounded-circle"></div>
    <div style="float: left;font-size: 0.8rem;width: 80%;margin-top: 0.1rem" class="text-dark"><span
            style="">除了装逼你还会干啥</span></div>
    <div style="float: left;font-size: 0.7rem;width: 80%;margin-top: 0.15rem" class="text-muted"><span style="">2019-4-27 阅读量：54654</span>
    </div>
</div>
<div id="content">
    <img style="width: 100%;margin-bottom: 1rem" src="../img/killer.jpg" class="rounded mx-auto d-block"/>
    <p class="text-justify">
        今日，开拓者官方在Instagram上发布了一张合成图，记录了后卫达米安-利拉德在2014年季后赛首轮G6中0.9秒命中绝杀，将火箭淘汰出局。
    </p>
    <p class="text-justify">
        以及今年季后赛首轮G5中最后时刻命中超远距离三分，
        绝杀淘汰雷霆的出手瞬间（见新闻配图）。
<<<<<<< Updated upstream:src/main/webapp/front/html/detail.html
    </p>
=======
    </p>--%>1-
    ${article.message}
>>>>>>> Stashed changes:src/main/webapp/WEB-INF/detail.jsp
</div>
<div style="text-align: right;margin-right: 1rem;margin-bottom: 0.7rem">
    <span class="text-muted" style="font-size: 0.75rem">点赞</span>
    <img id="love" src="../img/love2.svg"/>
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
            $("#love").attr("src", "../img/love.svg")
        })
        $("#return img").on("touchend", function () {
            $("#return").css("background-color","#cccccc")
            setTimeout(function f(){$("#return").css("background-color","#ffffff")},100)
        })
    })
</script>
</body>
</html>