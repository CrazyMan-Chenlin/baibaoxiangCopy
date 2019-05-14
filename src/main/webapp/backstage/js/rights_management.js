$(function () {
    $("#query").on("click",function () {
        var title = {title:'BBBBB'};
        $.ajax({
            url:"/manager1/title",
            type : "POST",
            contentType:"application/x-www-form-urlencoded; charset=utf-8",
            data:title,
            success :function (data) {
                $("#information").children().remove();
                $.each(data,function (index,item) {
                    $("#information").append("<tr><td><input type=\"checkbox\" name=\"cur_right\">"+item.username+"</td>\n" +
                        "<td>"+item.name+"</td>\n" +
                        "<td>"+item.area+"</td></tr>>");
                });
            }
        });


    });

    // var chk_value =[];//定义一个数组
    // $('input[name="cur_right"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数
    //     chk_value.push($(this).val());//将选中的值添加到数组chk_value中
    // });

    $("#add").on('click',function () {

        var username = $("#username").val();
        var name = $("#name").val();
        var password = $("#password").val();
        var area = $("#area").val();
        var data1 = {username:username,name:name,password:password,area:area};
        $.ajax({
            url:'/manager1',
            type: "POST",
            data: JSON.stringify(data1),
            dataType:"json",
            contentType:"application/json; charset=utf-8",
            success:function (data) {
                if (data==1){
                    alert("添加成功");
                    $("#username").val("");
                    $("#name").val("");
                    $("#password").val("");
                    $("#area").val("");
                } else {
                    alert("失败");
                }
            }
        });
    });
});