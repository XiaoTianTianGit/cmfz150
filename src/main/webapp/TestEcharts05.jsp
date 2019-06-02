<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <!--引入jquery-->
    <script src="${pageContext.request.contextPath}/boot/js/jquery-2.2.1.min.js"></script>
    <!--引入echarts插件-->
 <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/echarts/china.js"></script>

</head>
<body>
<!-- 为Echarts 装备一个具备大小（高度）的DOM-->
<div id="main" style="width:600px; height: 400px"></div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    option = {
        title : {
            text: '全国注册人员分布图',
            subtext: '第一期',
            left: 'center'
        },
        tooltip : {
            trigger: 'item'
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data:['第一期']  //选项卡 可添加
        },
        visualMap: {
            min: 0,
            max: 2500,
            left: 'left',
            top: 'bottom',
            text:['高','低'],           // 文本，默认为数值文本
            calculable : true
        },

        toolbox: {
        show: true,
            orient : 'vertical',
            left: 'right',
            top: 'center',
            feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    series : [
        {
            name: '第一期',
            type: 'map',
            mapType: 'china',
            roam: false,
            label: {
                normal: {
                    show: false
                },
                emphasis: {
                    show: true
                }
            }
        }
    ]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
    //发送ajax异步请求 从数据库中查询 数据
    $.ajax({
        url:"${pageContext.request.contextPath}/User/getTest1",
        type:"get",
        datatype:"json",
        success:function (data) {
            console.log("hhhhh");
            myChart.setOption({
                series : [
                    {
                        data:data
                    }
                ]
            })
        }
    })

</script>
</body>
</html>