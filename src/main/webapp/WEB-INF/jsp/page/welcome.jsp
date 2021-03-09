<%--
  Created by IntelliJ IDEA.
  User: 47440
  Date: 2021/2/28
  Time: 21:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
</head>
<body>
<div style="width: 1200px;height: 400px;margin:auto">
    <blockquote class="layui-elem-quote">欢迎进入秒杀</blockquote>
    <table id="demo" lay-filter="test"></table>
</div>

<script src="/layui/layui.js"></script>
<script >
    //一般直接写在一个js文件中
    layui.use(['layer', 'form','table'], function(){
        var layer = layui.layer
            ,form = layui.form;
        var table = layui.table;

        function DateFormat(sjc){
            var date = new Date(sjc);
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            m = m<10?'0'+m:m;
            var d = date.getDate();
            d = d<10?("0"+d):d;
            var h = date.getHours();
            h = h<10?("0"+h):h;
            var min = date.getMinutes();
            min = min<10?("0"+min):min;
            var s = date.getSeconds();
            s = s<10?("0"+s):s;
            var str = y+"-"+m+"-"+d+" "+h+":"+min+":"+s;
            return str;
        }
        //第一个实例
        table.render({
            elem: '#demo'
            ,height: 312
            ,url: '/seckill/getSecKillItems' //数据接口
            ,page: true //开启分页
            ,cols: [[ //表头
                {field: 'id', title: '商品ID', width:120, sort: true, fixed: 'left'}
                ,{field: 'name', title: '秒杀商品', width:120}
                ,{field: 'number', title: '秒杀数量', width:120, sort: true}
                ,{field: 'price', title: '秒杀价', width:120}
                ,{field: 'startTime', title: '秒杀开始时间', width: 177
                    ,templet:function (row){
                            return DateFormat(row.startTime);
                    }}
                ,{field: 'endTime', title: '秒杀结束时间', width: 177, sort: true
                    ,templet:function (row){
                        return DateFormat(row.endTime);
                    }}
                ,{ field:'',title: '秒杀', width:120,color:"#red",templet:function (row) {
                        return "<a style='color:red;font-weight:bold' href='/view/seckill?id="+row.id+"'>"+"进入秒杀"+"</a>";
                    }}
            ]]
        });
    });

</script>
</body>

</html>
