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
<script lang="javascript">
    function OnClose(check)
    {
        if(check == true)
        {
            var value = document.getElementById("password").value;
            window.returnValue = value;
        }
        window.close();
    }
</script>
<style type="text/css">
    .horizontal{
        float:left;
        margin-left:10px;
        margin-top: 10px;
    }
</style>

<div class="horizontal" style="width: 100%">
    <div style="width: 200px" class="horizontal">
        <button type="button" class="btn btn-default btn-info horizontal" data-toggle="modal" data-target="#myModal" style="margin-top: 1px">添加</button>
        <button type="button" class="btn btn-default btn-info horizontal" onclick="update()" style="margin-top: 1px;margin-left: 5px">刷新</button>
    </div>
</div>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    添加商品种类
                </h4>
            </div>
            <div style="height: 180px">
                <div class="form-group" style="top: 10px">
                    <label for="goods_name" class="col-sm-2 control-label">商品名：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="goods_name" placeholder="请输入商品名">
                    </div>
                </div>
                <div class="form-group" style="top: 10px">
                    <label for="goods_cost" class="col-sm-2 control-label">进货价：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="goods_cost" placeholder="请输入进货价">
                    </div>
                </div>
                <div class="form-group" style="top: 10px">
                    <label for="goods_price" class="col-sm-2 control-label">售价：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="goods_price" placeholder="请输入售价">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="add()">
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>商品id</th>
            <th>商品名</th>
            <th>成本价</th>
            <th>售价</th>
            <th>数量</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${listgoodssql}" var="g">
            <tr class="active">
                <td><c:out value="${g.goods_ID}" /></td>
                <td><c:out value="${g.goods_name}" /></td>
                <td><c:out value="${g.goods_cost}" /></td>
                <td><c:out value="${g.goods_price}" /></td>
                <td><c:out value="${g.goods_Rquantity}" /></td>
                <td>
                    <a style="margin-right: 10px" data-target="#myModal" data-toggle="modal" onclick="edit('${g.goods_name}','${g.goods_cost}','${g.goods_price}')">编辑</a>
                    <a href="${pageContext.request.contextPath}/delgoodssql.do?goods_ID=${g.goods_ID}" style="margin-right: 10px">删除</a>
                    <a href="${pageContext.request.contextPath}/equipmentdel.do?temp=0" style="margin-right: 10px">详情</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
<script type="text/javascript">
    function edit(goodsname,goodscost,goodsprice) {
        $("#goods_name").val(goodsname);
        $("#goods_cost").val(goodscost);
        $("#goods_price").val(goodsprice);
    }
    function update() {
        location.reload();
    }
    function add() {
        var goods_name = $("#goods_name").val();
        var goods_cost = $("#goods_cost").val();
        var goods_price = $("#goods_price").val();
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/addgoodssql.do",
            dataType: 'json',
            data: {
                "goods_name":goods_name,
                "goods_cost":goods_cost,
                "goods_price":goods_price
            },
            success: function(){
                window.location.replace("${pageContext.request.contextPath}/showgoodssql.do");
            },
            error:function(){
                window.location.replace("${pageContext.request.contextPath}/showgoodssql.do");
            }
        });

    }


</script>
</html>