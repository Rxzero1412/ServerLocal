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
<div style="margin-top: 80px;margin-left: 80px">
   <h2 style="margin-top: 20px;margin-left: 20px">支付成功，正在开门...</h2>
    <time id="time"></time>
</div>
</body>
<script type="text/javascript">
    var t=5;
    jump();
    function jump(){
        if(t==1){
            window.location.href= '${pageContext.request.contextPath}/getRFIDgoods.do';
        }
        document.getElementById("time").innerHTML=t+"秒后关门";
        t--;
        setTimeout('jump()',1000);
    }

</script>

</html>