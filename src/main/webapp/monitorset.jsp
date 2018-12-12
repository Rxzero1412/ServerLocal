<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>监控设置</title>
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
        margin-left:20px;
        margin-top: 10px;
    }
</style>
<div class="horizontal" style="width: 100%">
    <div class="form-group horizontal" style="width: 400px">
        <div class="col-sm-9">
            <label for="name" class="col-sm-3 control-label"  style="width:110px;margin-top: 5px">IP:</label>
            <input type="text" class="form-control" id="name" placeholder="请输入监控模块IP" name="name" style="width: 150px">
        </div>
    </div>
    <div class="input-group horizontal" style="width: 200px;margin-right: 10px">
        <div class="col-sm-9">
            <label for="com" class="col-sm-3 control-label"  style="width:110px;margin-top: 5px">端口号:</label>
            <input type="text" class="form-control" id="com" placeholder="请输入端口号" name="com" style="width: 150px">
        </div>
    </div><!-- /input-group -->

    <div style="width: 10%" class="horizontal">
        <button type="button" class="btn btn-default btn-info" onclick="add()" style="margin-top: 1px">添加</button>
        <button type="button" class="btn btn-default btn-info" onclick="update()" style="margin-top: 1px;margin-left: 10px">刷新</button>
    </div>

</div>
<div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>id</th>
            <th>IP地址</th>
            <th>端口号</th>
            <th>状态</th>
            <th> </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${Listip}" var="g">
            <tr class="active">
                <td><c:out value="${g.id}" /></td>
                <td><c:out value="${g.name}" /></td>
                <td><c:out value="${g.com}" /></td>
                <td>
                    <c:choose>
                        <c:when test="${g.status==1}">
                            <a href="${pageContext.request.contextPath}/equipmentupdate.do?temp=1&id=${g.id}&status=1">开启</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/equipmentupdate.do?temp=1&id=${g.id}&status=0">关闭</a>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/equipmentdel.do?temp=1&id=${g.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script type="text/javascript">
    function update(com) {
        location.reload();
    }
    function add() {
        var name = $("#name").val();
        var com = $("#com").val();
        var temp="1";
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/equipmentadd.do",
            dataType: 'json',
            data: {
                "name":name,
                "com":com,
                "temp":temp
            },
            success: function(){
                window.location.replace("${pageContext.request.contextPath}/monitorset.do");
            },
            error: function(){
                window.location.replace("${pageContext.request.contextPath}/monitorset.do");
            }
        });

    }


</script>
</html>