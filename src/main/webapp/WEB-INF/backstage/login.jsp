<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>系统登陆</title>
<link rel="stylesheet" type="text/css" href="/movie/css/login.css">
<script>
     function ZHonblus(){
      var check = false;
        var username=document.getElementById("username");
        var re = /^\d{11}$/;
        if(username.value==""){
            document.getElementById('ZHerror').innerHTML="*请输入用户名";
            check = false;
      
        }
        else if(username.value.length !=11){
            console.log(username.value);
            document.getElementById('ZHerror').innerHTML="*格式错误,长度应为11个字符";
             check = false;

        }
        else if(!re.test(username.value)){

            document.getElementById('ZHerror').innerHTML="*格式错误,只能包含数字!";
             check = false;
        }
        else {
            document.getElementById('ZHerror').innerHTML ="";
             check = true;
        }return check;
    }
    function ZHonfocu(){
            document.getElementById('ZHerror').innerHTML ="";
            
    }
  
//   密码
    function MMonblus(){
        var check = false;
          var password=document.getElementById("password");
          var re = /^(?=.*\d)(?=.*[a-zA-Z])[\da-zA-Z]{6,}$/;
         // var reg=/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/;

        if(password.value==""){
        document.getElementById('MMerror').innerHTML="*请输入密码";
        check = false;
        }
          else if(password.value.length < 6){
             document.getElementById('MMerror').innerHTML="*格式错误,,密码长度至少为6位";
             check = false;
       
         }

        // else if(!re.test(password.value)){
          //   document.getElementById('MMerror').innerHTML="*格式错误,必须包含英文字母大小写和数字";
            // check = false;
        //}
         else {
        document.getElementById('MMerror').innerHTML ="";
        check = true;

        }return check;
}
    function MMonfocu(){
        document.getElementById('MMerror').innerHTML ="";
        
    } 
    function check(){
    var check = ZHonblus()&&MMonblus();
    return check;
   }
	</script>

</head>
<body>
<div class="login">
		<form action="${pageContext.request.contextPath }/loginVerify" method="post" onSubmit="return check()">
			<div class="login_box">
				<h3>欢迎回来！</h3>
				<div class="box1">
					<span class="name1">账号</span>
					<input type="text" id="username" name="username" value="" placeholder="请输入您的用户名" onfocus="ZHonfocu()" onblur="ZHonblus()">
                    <span id="ZHerror"></span>
				</div>
				<div class="box2">
					<span class="name2">密码</span>
					<input type="password" id="password" name="password" value=""  placeholder="请输入您的密码" onfocus="MMonfocu()" onblur="MMonblus()">
                    <span id="MMerror"></span>
				</div>
				<div class="box3">
					<span class="box3_1">
						<input  id="submit" type="submit" value="登陆">
					</span>
					<span class="box3_2">
						<a href="${pageContext.request.contextPath }/userRegister">注册</a>
					</span>
				</div>
			</div>
		</form>
	</div>  
</body>
</html>