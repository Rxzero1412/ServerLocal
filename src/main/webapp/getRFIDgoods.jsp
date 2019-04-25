<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title></title>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="layui/layui.js"></script>
    <script type="text/javascript" src="js/ol-debug.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <link href="css/ol.css" rel="stylesheet" type="text/css" />
    <link href="css/popup.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="layui/css/layui.css">
</head>

<body class="layui-layout-body">
<div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>RFID</th>
            <th>商品名</th>
            <th>价格</th>
        </tr>
        </thead>
        <tbody id="idtbody">
        </tbody>
    </table>
</div>
</body>
<script type="text/javascript">
    window.onload=function(){
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/opengetRFID.do",
            success: function(request){
            },
            error:function(){
            }
        });
    }
    var t=document.getElementById("idtbody");
    var bool=true;
    var t2 = window.setInterval(function() {
        if(bool){
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/facegetgoods.do",
                success: function(request){
                    var length= t.rows.length;             //获得Table下的行数
                    if(length!=0){                      //如果有行，则清空
                        for(var i=length-1;i>=0;i--)
                        {
                            t.deleteRow(i);
                        }
                    }
                    if(request.length>0){
                        for (var i = 0; i < request.length; i++) {
                            //tr
                            var r = t.insertRow(t.rows.length);//创建新的行
                            //td
                            var c1 = r.insertCell();                //创建新的列
                            c1.innerHTML = request[i].rfid;
                            var c2 = r.insertCell();                //创建新的列
                            c2.innerHTML =request[i].goods_name;
                            var c3 = r.insertCell();                //创建新的列
                            c3.innerHTML =request[i].goods_price;
                        }
                    }else{
                        var r = t.insertRow();
                        var c = r.insertCell();
                        c.innerHTML="暂无列表";
                    }
                    document.getElementById('idtbody').appendChild(t);
                    console.log(request);
                },
                error:function(){
                }
            });
        }
    },2000);
</script>

</html>