<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 2021/2/2
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
    <style>
        body{
            padding: 0;
            margin: 0;
            background-color: silver;
        }
        #wk{
           height: 500px;
            width: 400px;
            margin: auto;
        }
        form{
            border: 2px black solid;
            background-color: white;
            text-align: center;
        }
        input{
            margin-bottom: 10px;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div id="wk">
        <p>欢迎来到主页</p>
        <p>${msg}</p>
        <form action="/portal/users/login.action" method="post">
            用户名：<input type="text" placeholder="请输入用户名" name="uname">
            <br>
            密码：<input type="password" placeholder="请输入密码" name="pwd">
            <br>
            <input type="submit" value="提交">
        </form>
    </div>
</body>
</html>
