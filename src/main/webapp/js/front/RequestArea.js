$(document).ready(function () {
    $("#areaName").change(function () {
        var schoolName = $("#schoolName").val();
        var schoolArea = $("#areaName").val();
        $("#page").attr("value","2");
        $.post("/index/changeAreaArticle", {
            area: schoolName + schoolArea,
            type: $(".find_nav_cur").text()
        }, function (data) {
            $(" h5").html(schoolName  + "("+schoolArea+")");
            $(".table tbody").empty();
            $.each(data, function (i, article) {
                $(".table tbody").append("<tr>\n" +
                    "                <td class=\"firstTd\">\n" +
                    "                    <div class=\"Title\"><a href=\"/detail?no="+article.no+"\">"+article.title+"</a></div>\n" +
                    "                    <div>\n" +
                    "                        <img class=\"littleIcon\" src=\"/images/front/love.svg\"/>\n" +
                    "                        &nbsp;<span>"+article.likeNum+"</span>\n" +
                    "                        &nbsp; <img class=\"littleIcon\" src=\"/images/front/read.svg\"/>\n" +
                    "                        <span>"+article.readNum+"</span>\n" +
                    "                    </div>\n" +
                    "                </td>\n" +
                    "                <td width=\"30%\">\n" +
                    "                    <img src=\""+article.picturePath+"\" class=\"rounded \" alt=\"...\"></td>\n" +
                    "            </tr>")
            })
        });
    })
})
