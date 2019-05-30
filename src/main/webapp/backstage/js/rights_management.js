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
            data: "usernames="+usernames,
            error:function (data) {
                alert("删除成功");
                $("#query").trigger('click');
            }
        });
    });
});