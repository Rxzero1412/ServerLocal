<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>显示商品种类</title>
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
<style type="text/css">
    .horizontal{
        float:left;
        margin-left:10px;
        margin-top: 10px;
    }
</style>

<div class="horizontal" style="width: 100%">
    <div style="width: 200px" class="horizontal">
        <button type="button" class="btn btn-default btn-info horizontal" style="margin-top: 1px" onclick="getrfid()">获取RFID</button>
        <button type="button" class="btn btn-default btn-info horizontal" onclick="update()" style="margin-top: 1px;margin-left: 5px">提交</button>
    </div>
    <div style="width: 200px;margin-top: 10px;margin-left: 10px" class="horizontal">
        <h5 class="horizontal">端口号:</h5>
        <div>
            <c:forEach items="${docomlist}" var="g">
                <h5 class="horizontal"><c:out value="${g}" /> </h5>
            </c:forEach>
        </div>
    </div>
</div>

<div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>RFID</th>
            <th>商品id</th>
            <th>商品名</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
<script type="text/javascript">
    var bool=false;
    function getrfid() {
        bool=true;
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/opengetRFID.do",
            success: function(){

            },
            error:function(){
            }
        });
    }
    function update() {
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/savegoodsRFID.do",
            success: function(){

                bool=false;
                window.clearInterval(t2);
            },
            error:function(){
            }
        });
    }

    var t2 = window.setInterval(function() {
        if(bool){
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/getRFID.do",
                success: function(request){
                    console.log(request);
                },
                error:function(){
                }
            });
        }
    },2500);
</script>

</html>