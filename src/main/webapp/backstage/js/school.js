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
                        "                <td><input type=\"checkbox\" name=\"schoolNo\">"+item.no+"</td>\n" +
                        "            </tr>");
                })
            }
        });
    });

    
    //添加学校
    $("#add").on('click',function () {
        var name = $("#name").val();
        var area = $("#area").val();
        var data1 = {name:name,area:area};
        $.ajax({
            url:'/school/',
            type: 'POST',
            data: JSON.stringify(data1),
            dataType:"json",
            contentType:"application/json; charset=utf-8",
            error:function () {
                alert("添加成功");
                $("#name").val("");
                $("#area").val("");
                $("#query").trigger('click');
            }
        });
    });


    //删除学校
    $("#delete").on('click',function () {
        var checked=[];
        $("input[name='schoolNo']:checked").each(function (i) {
            checked[i]=$(this).parents("td").text();
        });
        var ids = checked.join(",");
        // console.log(ids);
        // for (var i=0;i<checkkids.length;i++){
        //     console.log(checkkids[i]);
        // }

        $.ajax({
            url:"/school/deleteBatch",
            type:"POST",
            data: "ids="+ids,
            error:function () {
                $("#query").trigger('click');
            }
        });
    });
});