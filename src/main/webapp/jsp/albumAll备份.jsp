<%@ page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>

<script>

    $(function () {


        $("#albumList").jqGrid({


            url: "${pageContext.request.contextPath}/Alaum/showPageAlaum.do",
            datatype: "json",//以json形式返回
            editurl: "${pageContext.request.contextPath}/Alaum/edit.do",
            //定义列名
            colNames: ["ID", "标题", "分数", "作者", "播音员", "章节数", "专辑简介", "状态", "发布时间", "插图"],
            ////  colModel 填充 数据库 查出 的具体数据
            colModel: [
                {name: "id"},
                {name: "title", editable: true},
                {name: "score"},
                {name: "author", editable: true},
                {name: "brodecast", editable: true},
                {name: "count", editable: true},
                {name: "brief", editable: true},
                {
                    name: "status", editable: true, edittype: "select",
                    editoptions: {value: "1:1;2:2"}

                },
                {name: "publictime"},

                {
                    name: "coverimg", editable: true, edittype: "file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img style='height:50px;width:150' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'/>"
                    }

                },
            ],
            //分页相关
            pager: "#albumPage",
            rowNum: "3",
            rowList: [3, 4, 5],
            viewrecords: true,
            rownumbers: true, //自动编号
            // Bootstrap 风格
            styleUI: "Bootstrap",
            multiselect: true,//多选
            autowidth: true,//自适应宽度
            subGrid: true,//展示子类
            // alaumId 表示专辑对应的id     subGridId 表示点击本专辑 的id

            subGridRowExpanded: function (subGridId, alaumId) {
                //调用addSubGrid方法添加子表  添加章节表
                addSubGrid(subGridId, alaumId);


            },


            caption: "专辑信息列表展示",//指定表格名称
            height: '200px',

        }).jqGrid("navGrid", "#albumPage", {addtext: "添加", edittext: "编辑", deltext: "删除", search: false},

            {
                //修改
                closeAfterEdit: true,
                //修改完成之后触发
                afterSubmit: function (response) {
                    //获取修改成功的id
                    var alaumId = response.responseJSON.AlaumId;
                    //获取添加成功后的提示信息
                    var mess = response.responseJSON.message;
                    //异步请求 上传图片 根据添加成功的id修改图片路径
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/Alaum/uplode.do",
                        type: "post",
                        fileElementId: "coverimg",
                        data: {alaumId: alaumId},
                        success: function () {
                            //成功之后刷新，保证图片实时显示
                            $("#albumList").trigger("reloadGrid");
                            //添加成功之后 触发提示框 成功 并3秒之后自动关闭
                            $("#albumMassId").show().html(mess);
                            // 3秒之后自动 自动隐藏
                            setTimeout(function () {
                                $("#albumMassId").hide()
                            }, 3000)
                        }
                    });
                    return response;
                }
            },
            {
                //添加
                //修改
                closeAfterAdd: true,
                //修改完成之后触发
                afterSubmit: function (response) {
                    //获取修改成功的id
                    var alaumId = response.responseJSON.AlaumId;
                    alert("id===" + alaumId)
                    //获取添加成功后的提示信息
                    var mess = response.responseJSON.message;
                    // alert(mess);

                    //异步请求 上传图片 根据添加成功的id修改图片路径
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/Alaum/uplode.do",
                        type: "post",
                        fileElementId: "coverimg",
                        data: {alaumId: alaumId},
                        success: function () {
                            // alert("ggggg");
                            //成功之后刷新，保证图片实时显示
                            $("#albumList").trigger("reloadGrid");
                            //添加成功之后 触发提示框 成功 并3秒之后自动关闭
                            $("#albumMassId").show().html(mess);

                            // 3秒之后自动 自动隐藏
                            setTimeout(function () {
                                $("#albumMassId").hide()
                            }, 3000)

                        }
                    });
                    return response;
                }
            },
            {
                //删除

            }
        );

        function addSubGrid() {

        }


     function addSubGrid(subGridId, alaumId) {
         alert(subGridId)
         alert(alaumId)
         var subGridTableTd = subGridId + "chapterList";
         var subGridPagerId = subGridId + "chapterPage";
         //根据所选的不同专辑id动态的创建 子表
         $("#" + subGridId).html("<table id='" + subGridTableTd + " ' ></table>" +
             "<div id='" + subGridPagerId + "' style=\"height: 50px\"></div>")










     }

    });

</script>


<script>






</script>
<h3>专辑管理</h3>

<table id="albumList" class="table table-striped"></table>
<div id="albumPage" style="height: 50px"></div>
<%--
//章节表
<table id="chapterList" class="table able-striped"></table>
//章节表 分页div
<div id="chapterPage" style="height: 50px"></div>
--%>

<!--隐藏 style="display: none" -->

<div id="albumMassId" class="alert alert-warning alert-dismissible" role="alert" style="display: none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span>
    </button>
    <strong>Warning!</strong>
</div>


<%--


url: "${pageContext.request.contextPath}/Chapter/showPageChapter.do",
datatype: "json",//以json形式返回
editurl: "${pageContext.request.contextPath}/Chapter/edit.do",
//定义列名
colNames: ["ID", "标题", "分数", "作者", "播音员", "章节数", "专辑简介", "状态", "发布时间", "插图"],
////  colModel 填充 数据库 查出 的具体数据
colModel: [
{name: "id"},
{name: "title", editable: true},
{name: "score"},
{name: "author", editable: true},
{name: "brodecast", editable: true},
{name: "count", editable: true},
{name: "brief", editable: true},
{
name: "status", editable: true, edittype: "select",
editoptions: {value: "1:1;2:2"}

},
{name: "publictime"},

{
name: "coverimg", editable: true, edittype: "file",
formatter: function (cellvalue, options, rowObject) {
return "<img style='height:50px;width:150' src='${pageContext.request.contextPath}/upload/img/" + cellvalue + "'/>"
}

},
],
//分页相关
pager: "#" + subGridPagerId,
rowNum: "3",
rowList: [3, 4, 5],
viewrecords: true,
rownumbers: true, //自动编号
// Bootstrap 风格
styleUI: "Bootstrap",
multiselect: true,//多选
autowidth: true,//自适应宽度
caption: "章节信息列表展示",//指定表格名称
height: '200px',

}).jqGrid("navGrid", "#albumPage", {addtext: "添加", edittext: "编辑", deltext: "删除", search: false},

{
//修改
},
{
//添加
},
{
//删除
}
)
--%>
