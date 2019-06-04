$(function() {
    //加载文本编辑器
    $('#edit').on('froalaEditor.initialized', function (e, editor) {
        if ($('.dataReturn').html()!=""){
            $('#edit').froalaEditor('html.set',$('.dataReturn').html());
        }
    }).froalaEditor({
        imageUploadURL:'/article/uploadArticleImg',
        enter: $.FroalaEditor.ENTER_BR,
        language:'zh_cn',
    });

    //发布推文
    $(".submitVal").on('click',function () {
        var title = $.trim($("#title").val());
        var type = $.trim($("#type").val());
        var message = $("#edit").froalaEditor('html.get', true);
        var area = $("#area").text();
        var picture = "/aaaa";
        var author = $("#username").text();
        var date = new Date();
        var date1 = date.getFullYear()+"-"+(date.getMonth()+1)+"-"+date.getDate();
        var data1 ={title:title,type:type,area:area,message:message,createTime:date1,readNum:"0",likeNum:"0",
            picturePath:picture,author:author,top:"4"};
        if($.trim(title).length==0||title==""){
            alert("标题未填");
            return false;
        }else if($.trim(type).length==0||type==""){
            alert("类型未填");
            return false;
        } else if($.trim(area).length==0||area==""){
            alert("地区未填");
            return false;
        } else {
            $.ajax({
                type:"POST",
                url:"/article/",
                dataType:"json",
                contentType:"application/json",
                data:JSON.stringify(data1),
                success:function (data) {
                    window.location.href="../success.jsp";
                }
            });
        }
    });


    //选择文章类型
    $("#classification").on('click',function () {
        $.ajax({
            url:"/articleType",
            type:"get",
            success:function (data) {
                $.each(data,function (index,item) {
                    // $("#article_type").children().remove();
                    $("#article_type").append("<li><a href='#' class='type1'>"+item.type+"</a></li>");
                });
            }
        });
    });

    //添加类型到输入框
    $(document).on('click',".type1",function () {
        //获取下拉框的内容
        var type = ($(this).text());
        //添加到输入框内容
        $("#type").val(type);
    });
});
