$(function() {
    //加载文本编辑器
    $('textarea').froalaEditor()

    //发表文章
    $(".submitVal").on('click',function () {
        var title = $("#title").val();
        var type = $("#type").val();
        var message = $("textarea").val();
        var picture = "/aaaa";
        var author = $("#username").text();
        var date = new Date();
        var date1 = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();

        var data1 ={"title":title,"type":type,"area":area,"message":message,"createTime":date1,"readNum":"0","likeNum":"0",
            "picturePath":picture,"author":author,"top":"4"};
        if($.trim(title).length==0||title==""){
            alert("标题未填");
            return false;
        }else if($.trim(type).length==0||type==""){
            alert("类型未填");
            return false;
        } else if($.trim(area).length==0||area==""){
            alert("地区未填");
            return false;
        } else if(message==""){
            alert("文章未填");
            return false;
        } else {
            $.ajax({
                type:"POST",
                url:"/article/",
                // dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(data1),
                success:function (data) {
                    window.location.href="../success.jsp";
                }
            });
        }




    });
});