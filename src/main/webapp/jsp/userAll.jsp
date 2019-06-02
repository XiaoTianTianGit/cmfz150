<%@ page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#userList").jqGrid({
            //获取数据的地址
            url:"${pageContext.request.contextPath}/User/showUserPager.do",
            //增删改 发送 请求
            editurl:"${pageContext.request.contextPath}/User/edit.do",
             // 定义服务器返回的类型  datatype 固定格式
            datatype:"json",
            //定义列名
            //  colNames:["编号","id","标题","状态","描述","创建时间","图片"],
            colNames:["编号","法名","名称","性别","省份","市","签名","手机号","状态","密码","盐值","头像"],
            //启用单元格编辑模式
            //  colModel 填充 数据库 查出 的具体数据
            //postData:"ids",
            colModel:[
                {name:"id"},
                {name:"dharmaname"},
                {name:"name"},
                {name:"sex"},
                {name:"privoince"},
                {name:"city"},
                {name:"sign"},
                {name:"phonenum"},
                {name:"status",editable:true,
                 edittype:"select",
                    editoptions:{value:"冻结:冻结;正常:正常"}
                },
                {name:"password",hidden:true},
                {name:"salt",hidden: true},
                {name:"photo",edittype:"file",
                    formatter:function (cellvalue, options, rowObject) {
                    return "<img style='height:50px;width:150' src='${pageContext.request.contextPath}/upload/img/"+cellvalue+"'/>"
                    }
                },

               /* {name:"",editable:true,
                    formatter:function (cellvalue, options, rowObject) {
                        return"<a href='#' onclick=\"updateStatus('"+rowObject.id+"')\">修改状态</a>";
                    }
                }
*/
            ],
            //分页相关
            pager:"#pageList",
            rowNum:"3",
            rowList:[3,4,5],
            viewrecords:true,
            rownumbers:true, //自动编号
            // Bootstrap 风格
            styleUI:"Bootstrap",
            multiselect:true,//多选
            autowidth:true,//自适应宽度
            caption:"用户信息列表展示",//指定表格名称
            height:'350px',
        }).jqGrid("navGrid","#pageList",{add: false, edittext: "修改状态", del:false, search:false},

            {
            //修改
        },
        {
            //添加
        },

        {
            //删除
        }


        );

    });

// 近七天注册人员统计
        function Test1() {
                $("#myModa2").modal("show");
            }

            //全国注册人员分布图

    function Test2() {
        $("#myModa").modal("show");
    }
    //按月份统计走势图
    function Test3() {
        $("#myModa3").modal("show");
    }

</script>
<h3>用户管理</h3>

<!--标签页-->
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active">
            <a href="#home" aria-controls="home" role="tab" data-toggle="tab">工具箱</a>
        </li>
        <li ><a href="Javascript:void(0)" onclick="outUser()" ><b>导出</b></a></li>
       <li ><a href="${pageContext.request.contextPath}/TestEcharts04.jsp " ><b>近七天注册人员统计hhh</b></a></li>
      <li ><a href="${pageContext.request.contextPath}/TestEcharts05.jsp " ><b>全国注册人员分布图hhh</b></a></li>
        <li ><a href="${pageContext.request.contextPath}/TestEcharts06.jsp" ><b>按月份统计走势图hhh</b></a></li>

        <li ><a href="Javascript:void(0)" onclick="Test1()" ><b>近七天注册人员统计</b></a></li>
        <li ><a href="Javascript:void(0)" onclick="Test2()" ><b>全国注册人员分布图</b></a></li>
        <li ><a href="Javascript:void(0)" onclick="Test3()" ><b>按月份统计走势图</b></a></li>
    </ul>

</div>
<script>
    // 添加模态框 添加一条文章信息
    function outUser() {
        $.ajax({
            url: "${pageContext.request.contextPath}/User/EasyPoiOutUser",
            type: "post",
            datatype: "json",
            //data: $("#addArticleFrom").serialize(),
            success: function () {
            }
        });
    }
</script>

<table id="userList"></table>
<div id="pageList"></div>
<!--隐藏   style="display: none" -->

<div id="userMassId" class="alert alert-warning alert-dismissible" role="alert" style="display: none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Warning!</strong>
</div>




<!--全国注册人员分布图  默认不展示 需要触发事件才可展示-->

<div class="modal fade" id="myModa">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px">
            <!--模态框标题-->
            <div class="modal-header">
                <!-- 用来关闭模态框的属性:data-dismiss="modal"   -->
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>

            </div>

            <!--模态框内容体-->
            <div class="modal-body">

                <!-- 为Echarts 装备一个具备大小（高度）的DOM-->
                <div id="main5" style="width:600px; height: 400px"></div>
                <script type="text/javascript">
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main5'));
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


                          //  var fromObject = JSONObject.fromObject(data);

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
            </div>

            <!--模态页脚-->
            <div class="modal-footer" id="modal_footer2">
                <!--  data-dismiss="modal"  作用：点击提交之后 发送请求到后台  并 关闭 模态框-->
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>

            </div>
        </div>
    </div>
</div>



<!--近七天注册人员统计   默认不展示 需要触发事件才可展示-->
<div class="modal fade" id="myModa2">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px">
            <!--模态框标题-->
            <div class="modal-header">
                <!-- 用来关闭模态框的属性:data-dismiss="modal"   -->
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>

            </div>

            <!--模态框内容体-->
            <div class="modal-body">
                <!-- 为Echarts 装备一个具备大小（高度）的DOM-->
                <div id="main2" style="width:600px; height: 400px"></div>

                <script type="text/javascript">
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main2'));
                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '近七天注册用户走势图' //鼠标移入时的提示
                            //subtext:'副标题'
                        },
                        tooltip: {},
                        legend: { //选项卡
                            data:['注册用户量']
                        },
                        xAxis: {  // x轴
                            data: ["第一天","第二天","第三天","第四天","第五天","第六天","第七天"]
                        },
                        yAxis: {},  // y轴
                        series: [{
                            name: '注册用户量',
                            type: 'bar' //形状  bar 表示柱状统计;   pie 表示 扇形统计（饼状）; line 线壮统计
                            //data: [5, 20, 36, 10, 10, 200] //y的具体值
                        }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);

                    //发送ajax异步请求 从数据库中查询 数据
                    $.ajax({
                        url:"${pageContext.request.contextPath}/User/getTest2",
                        type:"get",
                        datatype:"json",
                        success:function (data1) {
                            //alert(data1);
                            //var homeObj = JSON.stringify(data1);
                            myChart.setOption({
                                series: [{  //需要统计的具体内容
                                    name: '注册用户量',
                                    type: 'line', //形状  bar 表示柱状统计;   pie 表示 扇形统计（饼状）; line 线壮统计
                                    data: data1      //y的具体值  需要处理
                                }
                                ]
                            })
                        }
                    });
                </script>
            </div>

            <!--模态页脚-->
            <div class="modal-footer" id="modal_footer1">
                <!--  data-dismiss="modal"  作用：点击提交之后 发送请求到后台  并 关闭 模态框-->
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>

            </div>
        </div>
    </div>
</div>


<!--按月份统计走势图   默认不展示 需要触发事件才可展示-->
<div class="modal fade" id="myModa3">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px">
            <!--模态框标题-->
            <div class="modal-header">
                <!-- 用来关闭模态框的属性:data-dismiss="modal"   -->
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>

            </div>

            <!--模态框内容体-->
            <div class="modal-body">
                <!-- 为Echarts 装备一个具备大小（高度）的DOM-->
                <div id="main3" style="width:600px; height: 400px"></div>
                <script type="text/javascript">
                    // 基于准备好的dom，初始化echarts实例
                    var myChart = echarts.init(document.getElementById('main3'));

                    // 指定图表的配置项和数据
                    var option = {
                        title: {
                            text: '按月份统计用户走势图' //鼠标移入时的提示
                            //subtext:'副标题'
                        },
                        tooltip: {},
                        legend: { //选项卡
                            data:['按月份统计用户走势图']
                        },
                        xAxis: {  // x轴
                            // data: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
                            data: ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"]
                        },
                        yAxis: {},  // y轴
                        series: [{
                            name: '按月份统计用户走势图',
                            type: 'line' //形状  bar 表示柱状统计;   pie 表示 扇形统计（饼状）; line 线壮统计
                            //data: [5, 20, 36, 10, 10, 200] //y的具体值
                        }
                        ]
                    };

                    // 使用刚指定的配置项和数据显示图表。
                    myChart.setOption(option);

                    //发送ajax异步请求 从数据库中查询 数据
                    $.ajax({

                        url:"${pageContext.request.contextPath}/User/getTest3",
                        type:"post",
                        datatype:"json",
                        success:function (data) {
                            myChart.setOption({
                                series: [{  //需要统计的具体内容
                                    name: '按月份统计用户走势图',
                                    type: 'line', //形状  bar 表示柱状统计;   pie 表示 扇形统计（饼状）; line 线壮统计
                                    data: data//y的具体值  需要处理
                                }
                                ]
                            })
                        }
                    });
                </script>
            </div>

            <!--模态页脚-->
            <div class="modal-footer" id="modal_footer">
                <!--  data-dismiss="modal"  作用：点击提交之后 发送请求到后台  并 关闭 模态框-->
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>

            </div>
        </div>
    </div>
</div>









<script>
    function updateStatus(rowObject) {

        $.ajax({
            url:"${pageContext.request.contextPath}/User/edit.do",
            type:"post",
            datatype:"json",
            data:"user="+rowObject,
             success:function (data) {
             }
        });
    }
</script>


