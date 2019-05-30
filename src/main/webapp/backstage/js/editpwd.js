$(function () {
    $("#change").click(function () {
        var check_id = $("#check_id").val();

        var initial_password= $("#initial_password").val();

        var new_password = $("#new_password").val();

        var confirm_password = $("#confirm_password").val();
        if (new_password==confirm_password){
            $.ajax({
                type : "post",
                url:"/manager1/updatepassword",
                data:{oldPassword:initial_password,newPassword:new_password },
                success:function (data) {
                    console.log(data["msg"]);
                }
            });
        }else {
            alert("确认密码不正确");
        }
    });
});