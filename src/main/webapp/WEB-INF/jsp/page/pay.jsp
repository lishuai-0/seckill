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
    <title>支付页面</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body>
<!-- 你的HTML代码 -->

<div id="handle">
    <button type="button"  id='countDown' style="" class="layui-btn layui-btn-fluid"></button>
</div>

<script src="/layui/layui.js"></script>
<script>
    //一般直接写在一个js文件中
    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer,$=layui.$
            ,form = layui.form,table=layui.table;
        //通过时间戳的差，转化00天00小时00分钟00秒
        function getTimeStr(time){
            var d,h,m,s;
            d=Math.floor(time/60/60/1000/24);
            h=Math.floor(time/60/60/1000%24);
            m=Math.floor(time/60/1000%60);
            s=Math.floor(time/1000%60);
            if (d<10){
                d="0"+d;
            }
            if (h<10){
                h="0"+h;
            }
            if (m<10){
                m="0"+m;
            }
            if (s<10){
                s="0"+s
            }
            return "秒杀倒计时:"+d+"天"+h+"小时"+m+"分钟"+s+"秒";
        }

        var orderId = <%=request.getParameter("orderId")%>;
        var countDown = $("#countDown");
        $.ajax({
            url:"/order/getOrder/"+orderId
            ,type:"post"
            ,success:function (result) {
                interval = window.setInterval(function () {

                    //获取时间差（结束时间-当前时间）
                    var time = result.createTime+10*60*1000-new Date().getTime();
                    var timeStr = getTimeStr(time);
                    console.log(time);
                    if (time>0){
                        countDown.html(timeStr);
                        return null;
                    }

                    // //客户端时间到，清除倒计时
                    // clearInterval(interval);
                });

            }
        })



    });
</script>
</body>
</html>