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
</head>
<body>
<!-- 你的HTML代码 -->

<script src="/layui/layui.js"></script>
<script>
    //一般直接写在一个js文件中
    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form,table=layui.table;
    });
</script>
</body>
</html>