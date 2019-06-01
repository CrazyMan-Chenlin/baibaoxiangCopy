$(function () {
    $("#change").click(function () {

        var initial_password= $.trim($("#initial_password").val());

        var new_password = $.trim($("#new_password").val());

        var confirm_password = $.trim($("#confirm_password").val());

        if (new_password==confirm_password){
            $.ajax({
                type : "post",
                url:"/manager1/updatepassword",
                data:{oldPassword:initial_password,newPassword:new_password },
                async:false,
                success:function (data) {
                    alert(data["msg"]);
                    $("#initial_password").val("");
                    $("#new_password").val("");
                    $("#confirm_password").val("");
                }
            });
        }else {
            alert("确认密码不正确");
        }
    });
});