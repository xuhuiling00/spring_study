<%--
  Created by IntelliJ IDEA.
  User: zero
  Date: 2021/2/2
  Time: 22:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>这是主页</p>
    <p>${msg}</p>
    <p>当前登录用户：${users.uname}</p>
    <p>当前用户可用金额：${users.money}</p>

    <form action="/portal/users/setmoney.action" method="post">
        存款金额：<input type="text" placeholder="请输入金额" name="money">
        <br>
        <input type="submit" value="确认存款">
    </form>

    <form action="/portal/users/getmoney.action" method="post">
        取款金额：<input type="text" placeholder="请输入金额" name="money">
        <br>
        <input type="submit" value="确认取款">
    </form>

    <form action="/portal/users/atob.action" method="post">
        转账用户名：<input type="text" placeholder="请输入用户名" name="bname">
        存款金额：<input type="text" placeholder="请输入金额" name="money">
        <br>
        <input type="submit" value="确认转账">
    </form>
</body>
</html>
