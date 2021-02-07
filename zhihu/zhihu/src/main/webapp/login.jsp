<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="login.css"/>

</head>
<body>
	<div id="login_frame">
 
    <p id="image_logo"><img src="image/logo.png" width="80" height="80"></p>
 
    <form action="/portal/user/login.do" method="post">
 
        <p><label class="label_input">手机</label><input type="text" id="uphone" name="uphone" class="text_field"/></p>
        <p><label class="label_input">密码</label><input type="password" id="upwd" name="upwd" class="text_field"/></p>

        <input type="submit" id="btn_login" value="登录"/>
        
    </form>
</div>

</body>
</html>