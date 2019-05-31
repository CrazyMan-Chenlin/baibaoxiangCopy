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
        
        
        // $.ajax({
        //     type:"post",
        //     url:"/manager1/loginVerify",
        //     data:{username:username,password:password,validatecode:validatecode},
        //     success:function (data) {
        //         if (data.code==1){
        //             window.location.href="../backstage/admin_index.jsp";
        //         }else {
        //             alert("用户名或密码错误！");
        //             window.location.href="../login.jsp";
        //         }
        //     }
        // });
    });
});