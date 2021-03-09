<%--
  Created by IntelliJ IDEA.
  User: 47440
  Date: 2021/2/16
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>开始使用layui</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <link rel="stylesheet" href="/layui/css/login.css">
</head>
<body>
<div class="login-main">
  <header class="layui-elip">登录</header>
  <form class="layui-form" action="/user/login" method="get">
    <div class="layui-input-inline">
      <label>
        <input type="text" name="id" required placeholder="用户名" class="layui-input">
      </label>
    </div>
    <div class="layui-input-inline">
      <label>
        <input type="password" name="password" required  placeholder="密码" class="layui-input">
      </label>
    </div>
    <div class="layui-input-inline login-btn">
      <button class="layui-btn">登录</button>
    </div>
    <hr/>
    <p><a href="#" class="fl">立即注册</a><a href="#" class="fr">忘记密码？</a></p>
  </form>
</div>
<!-- 你的HTML代码 -->

<script src="/layui/layui.js"></script>
<script>
  //一般直接写在一个js文件中
  layui.use(['layer', 'form'], function(){
    var layer = layui.layer
            ,form = layui.form;
  });
</script>
</body>
</html>