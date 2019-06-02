<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法洲后台管理系统</title>
    <link rel="icon" href="img/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" href="./statics/boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="./statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
    <script src="./statics/boot/js/jquery-3.3.1.min.js"></script>
    <script src="./statics/boot/js/bootstrap.min.js"></script>
    <script src="./statics/jqgrid/js/trirand/jquery.jqGrid.min(1).js"></script>
    <script src="./statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
    <!-- 配置文件 -->
    <script type="text/javascript" src="./statics/utf8-jsp/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="./statics/utf8-jsp/ueditor.all.js"></script>
    <script type="text/javascript" src="./statics/echarts/echarts.min.js"></script>
    <script type="text/javascript" src="./statics/echarts/china.js"></script>
    <script type="text/javascript" src="./statics/echarts/shine.js"></script>
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>
    <script>
        function changePage(page) {
            $("#loadPage").load(page);




        }
    </script>
    <style>
        body {
            padding-top: 70px;
            padding-bottom: 70px;
        }

        h5 {
            text-align: center;
        }

        .list-group-item {
            text-align: center;
        }
    </style>
</head>
<body>
<!--导航条-->
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <!--导航条标题-->
        <div class="navbar-header">
            <a href="" class="navbar-brand">
                持明法洲后台管理系统
            </a>
        </div>
        <!--导航内容-->
        <div class="collapse navbar-collapse">
            <!--将元素放在右侧-->
            <ul class="nav navbar-nav navbar-right">
                <li><a>欢迎: <span style="color: deepskyblue">${login.username}</span></a></li>
                <li><a href="${pageContext.request.contextPath}/admin/exit">安全退出 <span
                        class="glyphicon glyphicon-log-out"></span></a></li>
            </ul>
        </div>
    </div>
</nav>
<div class="container-fluid">
    <div class="row">
        <div class="col-sm-2">
            <!--创建手风琴实例-->
            <div class="panel-group" id="panelgroup">


                <!--创建面板-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <!--使用连接完成折叠效果-->
                            <a href="#p1" data-toggle="collapse" data-parent="#panelgroup"><h5>用户管理</h5></a>
                        </div>
                    </div>

                    <div class="panel-collapse collapse" id="p1">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="#" onclick="changePage('./chart.jsp')">用户列表</a></li>
                        </ul>
                    </div>
                </div>

                <!--创建面板-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <!--使用连接完成折叠效果-->
                            <a href="#p2" data-toggle="collapse" data-parent="#panelgroup"><h5>上师管理</h5></a>
                        </div>
                    </div>

                    <div class="panel-collapse collapse" id="p2">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="#" onclick="changePage('./album.jsp')">上师列表</a></li>
                        </ul>
                    </div>
                </div>

                <!--创建面板-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <!--使用连接完成折叠效果-->
                            <a href="#p3" data-toggle="collapse" data-parent="#panelgroup"><h5>文章管理</h5></a>
                        </div>
                    </div>

                    <div class="panel-collapse collapse" id="p3">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="#" onclick="changePage('./album.jsp')" >文章列表</a></li>
                        </ul>
                    </div>
                </div>

                <!--创建面板-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <!--使用连接完成折叠效果-->
                            <a href="#p4" data-toggle="collapse" data-parent="#panelgroup"><h5>专辑管理</h5></a>
                        </div>
                    </div>

                    <div class="panel-collapse collapse" id="p4">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="#" onclick="changePage('./user.jsp')">专辑列表</a></li>
                        </ul>
                    </div>
                </div>

                <!--创建面板-->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <div class="panel-title">
                            <!--使用连接完成折叠效果-->
                            <a href="#p5" data-toggle="collapse" data-parent="#panelgroup"><h5>轮播图管理</h5></a>
                        </div>
                    </div>

                    <div class="panel-collapse collapse" id="p5">
                        <ul class="list-group">
                            <li class="list-group-item"><a href="#" onclick="changePage('./log.jsp')">轮播图列表</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <%--引入其他页面--%>
        <div id="loadPage">
            <div class="col-sm-10">

                <div class="page-header" style="margin-top: 0">
                    <h3 style="margin-top: 0">欢迎来到持明法洲后台管理系统</h3>
                </div>

                <!--轮播图-->


                <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <!--控制轮播图的顺序-->
                    <ol class="carousel-indicators">
                        <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                        <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    </ol>
                    <!-- Wrapper for slides -->
                    <div class="carousel-inner">
                        <div class="item active">
                            <!--引入对应的图片-->
                            <img src="./img/shouye.jpg" alt="..." >
                            <!--对于图片的描述-->
                            <div class="carousel-caption">
                                法论大法好
                            </div>
                        </div>
                        <div class="item" >
                            <img src="./img/shouye.jpg" alt="..." >
                            <div class="carousel-caption">
                                苦海无边，回头是岸
                            </div>
                        </div>
                    </div>
                    <!-- Controls -->
                    <!--左移轮播图-->
                    <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left"></span>
                    </a>
                    <!--右移轮播图-->
                    <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    </a>
                </div>



               <%-- <div class="panel panel-default">
                    <div class="panel-body">
                        <img src="./image/323696.jpg" style="width: 100%">
                    </div>
                </div>--%>
            </div>
        </div>
    </div>
</div>


<footer>
    <nav class="navbar navbar-inverse navbar-fixed-bottom">

        <p class="navbar-text" style="text-align: center;width: 100%">@百知教育 <a href="#" style="color: deepskyblue">1603179097@qq.com</a>
        </p>
    </nav>
</footer>
</body>
</html>
