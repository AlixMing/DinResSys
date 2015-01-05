<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="css/login.css">
    <link rel="shortcut icon" href="img/head.ico" type="image/x-icon"/>
    <script type="text/javascript">
    </script>
</head>
<body>
<h1>自贡市城乡管理执法信息系统</h1>

<div class="loginContainer">
    <form action="user!addUser" method="post">
        <div class="inputArea">
            <label>账号：</label><input class="input" type="text" name="user.name" placeholder="${ user.name }">
            <label>密码：</label><input class="input" type="password" name="user.password" placeholder="请在此输入密码">
            <label>手机号码：</label><input class="input" type="text" name="user.tel" placeholder="请在此输入手机号码">
            <input class="input sub" type="submit"  value="保存">
            <span class="error" id="error"><s:property value="errorString"/></span>
        </div>
    </form>
</div>
</body>
</html>