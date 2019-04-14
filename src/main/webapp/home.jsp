<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>智能超市管理系统的设计与实现</title>
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
<style>
    .layui-layout-admin .layui-body {
        top: 60px;
        bottom: 0px;
        /*left: 0px;*/
    }
    .mapTree.checkbox{
        margin-left: 18px;
        margin-right: 10px;
        width: 16px;
        height: 16px;
    }
    .layui-layout-admin .layui-side {
        top: 60px;
        width: 300px;
        overflow-x: hidden;
    }
    .layui-nav-tree {
        width: 300px;
        padding: 0;
    }
    .layui-side-scroll {
        width: 300px;
        height: 100%;
        overflow-x: hidden;
    }
    .layui-body {
        position: absolute;
        left: 300px;
        right: 0;
        top: 0;
        bottom: 0;
        z-index: 998;
        width: auto;
        overflow: hidden;
        overflow-y: auto;
        box-sizing: border-box;
    }
    .layui-layout-admin .layui-logo {
        position: absolute;
        left: 0;
        top: 0;
        width: 400px;
        height: 100%;
        line-height: 60px;
        text-align: center;
        color: #fff;
        font-size: 22px;
    }
</style>
<body class="layui-layout-body">
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    修改管理员密码
                </h4>
            </div>
            <div style="height: 180px;width: 100%">
                <div class="form-group" style="top: 18px">
                    <label for="input_password1" class="col-sm-2 control-label" style="width: 300px">请输入新密码：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width: 400px" id="input_password1" placeholder="请输入新密码">
                    </div>
                </div>
                <div class="form-group" style="top: 10px;margin-top: 30px">
                    <label for="input_password2" class="col-sm-2 control-label" style="width: 300px">请确认新密码：</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" style="width: 400px" id="input_password2" placeholder="请确认新密码">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary" onclick="updatepassword()" data-dismiss="modal">
                    提交
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header" style="width: 100%">
        <div class="layui-logo">智能超市管理系统的设计与实现</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item layui-this">
                <a href="javascript:showhomeimg();">首页</a>
            </li>
            <li class="layui-nav-item">
                <a href="javascript:;">商品管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;" onclick="showgoodssql()">商品信息管理</a></dd>
                    <dd><a href="javascript:;">商品销售记录</a></dd>
                </dl>
            </li>

            <li class="layui-nav-item">
                <a href="javascript:;">用户管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">查看用户信息</a></dd>
                </dl>
            </li>

            <li class="layui-nav-item">
                <a href="javascript:;">其他管理</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">添加促销信息</a></dd>
                </dl>
            </li>

            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    ${username}
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">基本资料</a></dd>
                    <dd><a data-toggle="modal" data-target="#myModal">修改密码</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="#" onclick="javascript:history.back(-1);session.invalidate();">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree layui-inline" style="margin-right: 10px;" lay-filter="demo">
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">读卡模块</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:Cardreaderset();">设置</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a href="javascript:;">监控模块</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:monitorset();">监控设置</a></dd>
                        <dd><a href="">实时监控</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div id="mapDiv" style="width: 100%; height: 100%">
            <iframe id="iframeid" style="width: 100%;height: 100%;" scrolling="no" src="homeimg.jsp"></iframe>
        </div>
    </div>

</div>
<script>
    //JavaScript代码区域
    //alert(returnCitySN['cip'] + returnCitySN['cname']);

    layui.use('element', function(){
        var element = layui.element;

    });
    debugger
    var container = document.getElementById('popup');
    var closer = document.getElementById('popup-closer');
    closer.onclick = function(){
        container.style.display = 'none';
        closer.blur();
        return false;
    };
    var overlay = new ol.Overlay({
        element: container
    });
</script>
<script type="text/javascript">
    function Cardreaderset(){
        $("#iframeid").attr("src", '${pageContext.request.contextPath}/cardreadset.do');
    }
    function monitorset(){
        $("#iframeid").attr("src", '${pageContext.request.contextPath}/monitorset.do');
    }
    function showgoodssql(){
        $("#iframeid").attr("src", '${pageContext.request.contextPath}/showgoodssql.do');
    }
    function showhomeimg(){
        $("#iframeid").attr("src", '${pageContext.request.contextPath}/homeimg.jsp');
    }
    function updatepassword() {
        var password1 = $("#input_password1").val();
        var password2 = $("#input_password2").val();
        if(password1==password2){
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/updatepassword.do",
                dataType: 'json',
                data: {
                    "password":password1,
                    "id":"0"
                },
                success: function(){
                    alert("修改成功");
                },
                error:function(){
                    alert("修改成功");
                }
            });
        }else{
            alert("密码不一致，请重新输入");
        }
    }
</script>
</body>
</html>