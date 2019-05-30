$(function () {

    $("#login").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var validatecode = $("#validatecode").val();

        $.ajax({
            type:"post",
            url:"/manager1/loginVerify",
            data:{username:username,password:password,validatecode:validatecode},
            success:function (data) {
                if (data.code==1){
                    window.location.href="/backstage/admin_index.jsp";
                }else {
                    alert(data.code);
                    window.location.href="/backstage/login.jsp";
                }
            }
        });
    });
});