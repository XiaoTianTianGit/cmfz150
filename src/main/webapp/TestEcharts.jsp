<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <!--引入echarts插件-->
 <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
</head>
<body>
<!-- 为Echarts 装备一个具备大小（高度）的DOM-->
<div id="main" style="width:600px; height: 400px"></div>

<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));

    // 指定图表的配置项和数据
    var option = {
        title: {
            text: 'ECharts 入门示例', //鼠标移入时的提示
            subtext:'副标题'
        },
        tooltip: {},
        legend: { //选项卡
            data:['销量','库存']
        },
        xAxis: {  // x轴
            data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
        },
        yAxis: {},  // y轴
        series: [{
            name: '销量',
            type: 'line', //形状  bar 表示柱状统计;   pie 表示 扇形统计（饼状）; line 线壮统计
            data: [5, 20, 36, 10, 10, 200] //y的具体值
        },
            {
                name: '库存',
                type: 'pie', //形状  bar 表示柱状统计;   pie 表示 扇形统计（饼状）; line 线壮统计
                data: [50, 200, 360, 1, 100, 20] //y的具体值
            }



        ]
    };

    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);

</script>
</body>
</html>