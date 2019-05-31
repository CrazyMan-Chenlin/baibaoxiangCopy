$(function () {

    $("#login").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var validatecode = $("#validatecode").val();

        if(username!=null&&username!=""){
            if (password!=null&&password!="") {
                if (validatecode!=null&&validatecode!="") {
                    alert("密码错误将会重新回到登录页面");
                    $("#form").submit();
                }else {
                    alert("验证码不能空");
                }
            }else {
                alert("密码不能空");
            }
        }else {
            alert("用户名不能空");
        }
    });
});