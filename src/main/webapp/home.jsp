<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>智能超市管理系统的设计与实现</title>
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
<div class="layui-layout layui-layout-admin">
    <div class="layui-header" style="width: 100%">
        <div class="layui-logo">智能超市管理系统的设计与实现</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-right">
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
                    admin
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">基本资料</a></dd>
                    <dd><a href="javascript:;">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="#" onclick="javascript:history.back(-1);session.invalidate();">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">读卡模块</a>
                    <dl class="layui-nav-child">
                        <dd style="margin-left: 10px" onclick="Cardreaderset()">设置</dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">监控模块</a>
                    <dl class="layui-nav-child" id="layer-child">
                        <dd style="margin-left: 10px"onclick="monitorset()">监控设置</dd>
                        <dd style="margin-left: 10px">实时监控</dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div id="mapDiv" style="width: 100%; height: 100%">
            <iframe id="iframeid" style="width: 100%;height: 100%;" scrolling="no"></iframe>
        </div>
        <div id="popup" class="ol-popup">
            <a href="#" id="popup-closer" class="ol-popup-closer"></a>
            <div id="popup-title" class="popup-title"></div>
            <div id="popup-content" class="popup-content"></div>
        </div>
    </div>

</div>
<script>
    //JavaScript代码区域


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
</script>
</body>
</html>