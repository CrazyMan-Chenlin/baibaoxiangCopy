$(function () {
    //加载滚动条
    $(".left").mCustomScrollbar();

    //查询学校
    $("#query").on('click',function () {
        $.ajax({
            type : "GET",
            url: "/school/allSchool",
            // dataType:'json',
            success :function (data) {
                $("#schools").children().remove();
                $.each(data,function (index,item) {
                    $("#schools").append("<tr>\n" +
                        "                <td>"+item.name+"</td>\n" +
                        "                <td>"+item.area+"</td>\n" +
                        "                <td class='itemNo' style='text-align: center'>"+item.no+"</td>\n" +
                        "                <td><input type=\"checkbox\" name=\"schoolNo\" </td>\n" +
                        "            </tr>");
                })
            }
        });
    });

    
    //添加学校
    $("#add").on('click',function () {
        var name = $.trim($("#name").val());
        var area = $.trim($("#area").val());
        var data1 = {name:name,area:area};
        $.ajax({
            url:'/school/',
            type: 'POST',
            data: JSON.stringify(data1),
            dataType:"json",
            contentType:"application/json; charset=utf-8",
            success:function (data) {
                alert(data["msg"]);
                $("#name").val("");
                $("#area").val("");
                $("#query").trigger('click');
            }
        });
    });


    //删除学校
    $("#delete").on('click',function () {
        if(confirm("是否决定删除学校?")){
            var checked=[];
            $("input[name='schoolNo']:checked").each(function (i) {
                checked[i]=$(this).parents().parents().children(".itemNo").text();
                console.log(checked[i]);
            });
            var ids = checked.join(",");
            $.ajax({
                url:"/school/deleteBatch",
                type:"POST",
                data: {ids:ids},
                async:false,
                success:function(data) {
                    alert(data["msg"]);
                    $("#query").trigger('click');
                }
            });
        }
    });
});