$(function () {


    //删除操作
    $(document).on("click",'.delete',function () {
        var no=$(this).parent().parent().parent().children(".panel-body").text();
        $.ajax({
            type:'delete',
            url:'/article/'+no,
            success:function () {
                window.location.href="/backstage/article.jsp";
            },
            error:function () {
                alert("删除失败");
            }
        });
    });

    //查询文章类型
    $("#classification").on('click',function () {
        $.ajax({
            type : "GET",
            url: "/articleType",
            // dataType:'json',
            success :function (data) {
                $(".dropdown-menu").children().remove();
                $.each(data,function (index,item) {
                    $(".dropdown-menu").append("<li><a href='#' class='modular'>"+item.type+"</a></li>");
                });
            }
        });

    });

    //点击下拉菜单中的元素，请求对应数据
    $(document).on('click','.modular',function () {
        var data1 = {type:$(this).text()};
        $.ajax({
            type : "POST",
            url: "/article/type",
            // dataType:'json',
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            data:data1,
            success :function (data) {
                $("#articles").children().remove();
                $.each(data, function(index, item){//遍历json中每一个单元
                    $("#articles").append(         //添加新元素（具体内容不重要）
                        "<li> " +
                        "<div class='panel panel-default'>"+
                        "<div class='panel-heading'>"+item.title+"</div>"+
                        "<div class='panel-body' id='no'>"+item.no+"</div>"+
                        "<div class='panel-footer'><span>点赞数"+item.likeNum+"</span><span>阅读数"+item.readNum+"</span>"+
                        "<div class='manage'>"+
                        "<div class='delete'></div>"+
                        "<div class='edit'></div>"+
                        "<div class='up'></div>"+
                        "</div>"+
                        "</div>"+
                        "</li>"
                    )
                });
            }
        });
    });

});
