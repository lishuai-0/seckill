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
    <script src="https://cdn.staticfile.org/vue/2.2.2/vue.min.js"></script>
    <style>
        *{
            margin: 0px;
            padding: 0px;
        }
    </style>
</head>
<body>
<div style="width: 900px;height: 400px;margin:auto">
    <!-- 你的HTML代码 -->
    <blockquote class="layui-elem-quote">欢迎秒杀</blockquote>
    <div id="handle">
        <button type="button"  id='seckillpre' style="" class="layui-btn layui-btn-disabled layui-btn-fluid"></button>
        <button type="button"  id='seckilling' style="display: none" class="layui-btn layui-btn-disabled layui-btn-fluid">点击抢购</button>
        <button type="button"  id='seckillend' style="display: none" class="layui-btn layui-btn-disabled layui-btn-fluid">秒杀结束</button>
        <button type="button"  id='seckillSuccess' style="display: none" class="layui-btn layui-btn-disabled layui-btn-fluid">秒杀成功，点击支付</button>
    </div>
</div>
<script src="/layui/layui.js"></script>
<script>

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
    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form
            ,table=layui.table
            ,$=layui.$;
        var id = <%=request.getParameter("id")%>;
        //获取抢购商品的信息
        $.ajax({
            type:"post"
            ,url:"/seckill/getSecKillItem"
            ,data:{"id":id}
            ,success:function (result) {
                //获取button所在div
                var handleDiv = $(".handle")[0];
                //创建两个button,分别为秒杀前显示，秒杀后显示
                var preButton = $("#seckillpre");
                var ingButton = $("#seckilling").hide();
                var endButton = $("#seckillend").hide();
                var successButton = $("#seckillSuccess").hide();
                //秒杀倒计时
                preInterval = window.setInterval(function () {
                    //获取时间差（结束时间-当前时间）
                    var time = result.startTime-new Date().getTime();
                    var timeStr = getTimeStr(time);
                    if (time>0){
                        preButton.html(timeStr);
                        return null;
                    }
                    //客户端时间到，清除倒计时
                    clearInterval(preInterval);
                    //添加抢购按钮点击事件
                    ingButton.click(function () {
                        $.ajax({
                            type:"post"
                            ,url:"/seckill/getSeckillURL"
                            ,data:{"id":id}
                            ,success:function (result) {

                                console.log(result);
                                if (result.enable){
                                    $.ajax({
                                        type:"post"
                                        ,url:"/seckill/excuteSeckill/"+id+"/"+result.seckillUrl
                                        ,success:function (result) {
                                            alert(result.data);
                                            if(result.code==200&&result.msg=="ok"){
                                                window.location.href=result.url;
                                            }else if (result.code==300){
                                                window.location.href="/";
                                            }
                                        }
                                    })
                                }else{
                                    alert(result.msg);
                                }
                            }
                        })
                    })

                    preButton.remove();
                    ingButton.removeClass("layui-btn-disabled").show();
                    //秒杀开始


                    //判断秒杀商品是否结束已经抢完或秒杀是否已经结束
                    $.ajax({
                        type:"post"
                        ,url:"/seckill/getSecKillItem"
                        ,data:{"id":id}
                        ,success:function (result) {
                            var number = Number(result.number);
                            var time = result.endTime-new Date().getTime();
                            if (number<1||time<0){
                                //秒杀结束
                                ingButton.remove();
                                endButton.show();
                            }
                        }
                    })
                },1000)
            }
        })

    });
</script>
</body>
</html>