<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="../img/2.gif">
        <!--引入bootstrap 相关 css-->
    <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
    <link rel="stylesheet" href="../boot/css/bootstrap.css">
        <!--引入 jqgrid 相关 css  ..表示返回上一级目录-->
    <link rel="stylesheet" href="../jqgrid/css/jquery-ui.css">
    <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
     <!--必须先引入 jquery-->
    <script src="../boot/js/jquery-2.2.1.min.js"></script>
    <!--引入bootstrap相关 is-->
    <script src="../boot/js/bootstrap.min.js"></script>
    <!--引入 jqgrid 相关 js-->
    <script src="../jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>

    <%--<sccrip type="text/javascript" src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></sccrip>--%>
   <%-- <script type="text/javascript" src="js/i18n/grid.locale-cn.js" ></script>--%>
    <%-- ajaxFileUpload  --%>
    <script src="../boot/js/ajaxfileupload.js"></script>

<!--引入kindeditor  富文本编辑器 相关-->
<script charset="UTF-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all-min.js"></script>
<script charset="UTF-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>

    <!--引入echarts插件-->
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/echarts/china.js"></script>

</head>
<body>
<!--导航条-->

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持明法州后台管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">


            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎<span class="text-primary">&nbsp;${sessionScope.admin.username}</span></a></li>
                <li><a href="${pageContext.request.contextPath}/Admin/secedeLogin.do" >退出登录&nbsp;<span class="glyphicon glyphicon-log-out"></span></a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<!-- body部分  引入栅格系统-->

<div class="container-fluid">
    <div class="row">
        <!--手风琴 占2列-->
        <div class="col-xs-2">

            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                               aria-expanded="true" aria-controls="collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel"
                         aria-labelledby="headingOne">
                        <div class="panel-body">
                            <li><a href="javascript:$('#changContent').load('userAll.jsp')">用户列表</a></li>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                上师管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <li><a>上师列表</a></li>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingThree">
                        <div class="panel-body">
                            <li><a href="javascript:$('#changContent').load('articleAll.jsp')">文章列表</a></li>
                        </div>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFore">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseFore" aria-expanded="false" aria-controls="collapseFore">
                            专辑管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFore" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFore">
                    <div class="panel-body">
                        <li><a href="javascript:$('#changContent').load('albumAll.jsp')">专辑列表</a></li>
                    </div>
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading" role="tab" id="headingFive">
                    <h4 class="panel-title">
                        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                           href="#collapseFive" aria-expanded="false" aria-controls="collapseFive">
                            轮播图管理
                        </a>
                    </h4>
                </div>
                <div id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                    <div class="panel-body">

                        <li><a href="javascript:$('#changContent').load('bannerAll.jsp')">轮播图列表</a></li>
                    </div>
                </div>
            </div>

        </div>

        <!--巨幕 和轮播图 占10列-->
        <div id="changContent" class="col-xs-10">
            <!--上部分   巨幕-->
            <div class="jumbotron">
                <h3>欢迎来到持明法州后台管理系统</h3>

            </div>

            <!--下部分 轮播图-->
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="../img/shouye.jpg" alt="...">
                        <div class="carousel-caption">

                        </div>
                    </div>
                    <div class="item">
                        <img src="../img/shouye.jpg " alt="..." style="height: 60%;width: 100%">
                        <div class="carousel-caption">

                        </div>
                    </div>

                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

        </div>

    </div>
</div>
</br>
<!--下部分 页脚-->
<div class="panel panel-default">
    <h5 style="text-align: center">@百知教育 baizhi@zparkhr.com.cn</h5>
</div>

</body>
</html>