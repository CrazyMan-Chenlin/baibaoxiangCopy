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
                    $("#information").append("<tr><td class='username'><input type=\"checkbox\" name=\"username\">"+item.username+"</td>\n" +
                        "<td>"+item.name+"</td>\n" +
                        "<td>"+item.area+"<button type=\"button\" class=\"btn btn-danger\" id=\"edit\">修改密码</button>"+"</td></tr>>");
                });
            }
        });
    });

    //修改密码
    $(document).on('click','#edit',function () {
        var id = $(this).parent().parent().children(".username").text();
        var password = prompt("想要修改的密码:");
        if (isChinese(password)){
            alert("不能为中文");
        }else {
            $.ajax({
                type:"post",
                url:"/manager1/updateBy",
                data:{id:id,password:password},
                success:function (data) {
                    alert(data["msg"]);
                }
            });
        }
        // console.log(password);
    });


    //添加管理员
    $("#add").on('click',function () {
        var username = $.trim($("#username").val());
        var name = $.trim($("#name").val());
        var password = $.trim($("#password").val());
        var area = $.trim($("#area").val());
        if(isChinese(username)||isChinese(password)){
            alert("用户名或密码不能携带中文");
        }else {
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
        }

    });

    //删除管理员
    $("#delete").on('click',function () {
        if(confirm("是否决定删除管理员?")){
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
        }
    });
});

/**
 * 判断输入框是否为中文
 * @param temp
 * @returns {boolean}
 */
function isChinese(temp)
{
    var re=/^[a-zA-Z0-9_]{0,}$/;
    if (re.test(temp))
        return false ;
    return true ;
    }