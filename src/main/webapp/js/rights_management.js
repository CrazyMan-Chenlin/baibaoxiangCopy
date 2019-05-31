$(function () {
    //查询管理员
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
                    $("#information").append("<tr><td><input type=\"checkbox\" name=\"username\">"+item.username+"</td>\n" +
                        "<td>"+item.name+"</td>\n" +
                        "<td>"+item.area+"</td></tr>>");
                });
            }
        });


    });

    //添加管理员
    $("#add").on('click',function () {

        var username = $.trim($("#username").val());
        var name = $.trim($("#name").val());
        var password = $.trim($("#password").val());
        var area = $.trim($("#area").val());
        var data1 = {username:username,name:name,password:password,area:area};
        $.ajax({
            url:'/manager1',
            type: "POST",
            data: JSON.stringify(data1),
            dataType:"json",
            contentType:"application/json; charset=utf-8",
            success:function (data) {
                alert(data["msg"]);
                $("#username").val("");
                $("#name").val("");
                $("#password").val("");
                $("#area").val("");
            }
        });
    });

    //删除管理员
    $("#delete").on('click',function () {
        var checked=[];
        $("input[name='username']:checked").each(function (i) {
            checked[i]=$(this).parents("td").text();
        });
        var usernames = checked.join(",");
        // console.log(usernames);
        $.ajax({
            url:"/manager1/deleteBatch",
            type:"POST",
            data: {usernames:usernames},
            async:false,
            success:function (data) {
                alert(data["msg"]);
                $("#query").trigger('click');
            }
        });
    });
});