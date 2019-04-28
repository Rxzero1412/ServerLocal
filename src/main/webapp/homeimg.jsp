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
<body class="layui-layout-body">
<div style="height: 100%;width: 100%">
    <script src="js/echarts.min.js"></script>
    <div id="main1" style="width: 500px;height:300px;z-index: 10000;margin-left: 40px;margin-top: 30px;"></div>
    <div id="main2" style="width: 500px;height:300px;z-index: 20000;margin-left: 620px;margin-top: -290px;"></div>
    <div id="main3" style="width: 480px;height:400px;z-index: 30000;margin-left: 40px;margin-top: -40px;"></div>
    <div id="Layer1" style="position:fixed; left:0px; top:0px; width:100%; height:100%;">
        <div style="width:100%; height:100%;z-index: 2000;">
            <img src="img/timg.gif" width="40%" height="45%" style="margin-left:640px;margin-top: 380px"/>
        </div>
    </div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        //alert();
        var sortJson = function(obj, callback) {
            var endValue, item, key, keyArray, keyArray2, o, _i, _len;
            endValue = {};
            keyArray = [];
            keyArray2 = [];
            for (key in obj) {
                o = {};
                o[key] = obj[key];
                keyArray.push(key);
            }
            keyArray2 = keyArray.sort();
            for (_i = 0, _len = keyArray2.length; _i < _len; _i++) {
                item = keyArray2[_i];
                endValue[item] = obj[item];
            }
            return typeof callback === "function" ? callback(endValue) : void 0;
        };

        sortJson(${remaps},function(data){
            console.log(data);
            var remap=data;
            var rq=new Array();;
            var rqdate=new Array();;
            for(var key in remap){
                rq.push(key);
                rqdate.push(remap[key]);
            }
            var myChart1 = echarts.init(document.getElementById('main1'));
            option1 = {
                title: {
                    text: '商品销售额',
                    subtext: '按日统计'
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data:['销售额']
                },
                toolbox: {
                    show: true,
                    feature: {
                        dataZoom: {
                            yAxisIndex: 'none'
                        },
                        dataView: {readOnly: false},
                        magicType: {type: ['line', 'bar']},
                        restore: {},
                        saveAsImage: {}
                    }
                },
                xAxis:  {
                    type: 'category',
                    boundaryGap: false,
                    data: rq
                },
                yAxis: {
                    type: 'value',
                    axisLabel: {
                        formatter: '{value} ￥'
                    }
                },
                series: [
                    {
                        name:'销售额',
                        type:'line',
                        data:rqdate,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'}
                            ]
                        }
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart1.setOption(option1);
        });

        var c2date=${regoods};
        var goodsname=new Array();
        c2d=[];
        for(var key in c2date){
            var json={};
            goodsname.push(key);
            json.value=c2date[key];
            json.name=key;
            c2d.push(json);
        }

        // 基于准备好的dom，初始化echarts实例
        var myChart2 = echarts.init(document.getElementById('main2'));
        option2 = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} <br/>{b}: {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                x: 'left',
                data:goodsname
            },
            series: [
                {
                    name:'商品名',
                    type:'pie',
                    radius: ['50%', '70%'],
                    avoidLabelOverlap: false,
                    label: {
                        normal: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            show: true,
                            textStyle: {
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            show: false
                        }
                    },
                    data:c2d
                }
            ]
        };

        myChart2.setOption(option2);


        var myChart3 = echarts.init(document.getElementById('main3'));
        option3 = {
            tooltip : {
                formatter: "{a} <br/>{b} : {c}%"
            },
            toolbox: {
                feature: {
                    restore: {},
                    saveAsImage: {}
                }
            },
            series: [
                {
                    name: 'CPU',
                    type: 'gauge',
                    detail: {formatter:'{value}%'},
                    data: [{value: 50, name: 'CPU使用率'}]
                }
            ]
        };

        setInterval(function () {
            $.ajax({
                type: "POST",
                url: "${pageContext.request.contextPath}/getcpu.do",
                success: function(e){
                    option3.series[0].data[0].value = e;
                },
                error:function(e){
                }
            });

            myChart3.setOption(option3, true);
        },2000);

        myChart3.setOption(option3);
    </script>

    <script type="text/javascript">

    </script>



</div>
</body>
</html>