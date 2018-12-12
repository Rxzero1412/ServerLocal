<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>读卡器设置</title>
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
            <label for="name" class="col-sm-3 control-label"  style="width:110px;margin-top: 5px">读卡模块名:</label>
            <input type="text" class="form-control" id="name" placeholder="请输入读卡模块名" name="projectname" style="width: 150px">
        </div>
    </div>
    <div class="input-group horizontal" style="width: 200px;margin-right: 10px">
        <input type="text" id="com" class="form-control" placeholder="请选择端口号">
        <div class="input-group-btn" style="width: 40%">
            <button type="button" class="btn btn-default
                        dropdown-toggle" data-toggle="dropdown">请选择
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu pull-right">
                <c:forEach items="${list}" var="g">
                    <li>
                        <a href="#" onclick="onclickchoose('${g.com}')"><c:out value="${g.com}"/></a>
                    </li>
                </c:forEach>
            </ul>
        </div><!-- /btn-group -->
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
            <th>名称</th>
            <th>端口号</th>
            <th>状态</th>
            <th> </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${Listequipment}" var="g">
            <tr class="active">
                <td><c:out value="${g.id}" /></td>
                <td><c:out value="${g.name}" /></td>
                <td><c:out value="${g.com}" /></td>
                <td>
                    <c:choose>
                        <c:when test="${g.status==1}">
                            <a href="${pageContext.request.contextPath}/admindetails.do">开启</a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/showaddnext.do">关闭</a>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/cardreaddel.do?id=${g.id}">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script type="text/javascript">
    function onclickchoose(com) {
        $("#com").val(com);
        console.log(com);
    }

    function update(com) {
        location.reload();
    }


</script>
</html>