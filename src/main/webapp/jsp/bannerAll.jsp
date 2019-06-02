<%@ page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#bannerList").jqGrid({
            //获取数据的地址
            url:"${pageContext.request.contextPath}/Banner/showPageBannner.do",
            //增删改 发送 请求
            editurl:"${pageContext.request.contextPath}/Banner/edit.do",
             // 定义服务器返回的类型  datatype 固定格式
            datatype:"json",
            //定义列名
            //  colNames:["编号","id","标题","状态","描述","创建时间","图片"],
            colNames:["编号","标题","状态","描述","创建时间","图片"],
            //启用单元格编辑模式
            //  colModel 填充 数据库 查出 的具体数据
            //postData:"ids",
            colModel:[
                {name:"id",hidden:true},
                {name:"picname",editable:true},
                {name:"status",editable:true,edittype:"select",
                    editoptions:{value:"展示:展示;不展示:不展示"}

                },
                {name:"description",editable:true},
                {name:"createtime"},
                {name:"picpath",editable:true,edittype:"file",
                    formatter:function (cellvalue, options, rowObject) {
                        return "<img style='height:50px;width:150' src='${pageContext.request.contextPath}/upload/img/"+cellvalue+"'/>"
                    }
                }
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

            caption:"轮播图信息列表展示",//指定表格名称
            height:'200px',
           // subGrid:true,
            subGridRowExpanded:function (subId,bannerId) {
                addSubGrid(subId,bannerId);
            }



        }).jqGrid("navGrid","#pageList",{addtext:"添加",edittext:"编辑",deltext:"删除",search:false},
        {
            //修改

            closeAfterEdit:true,
            //修改完成之后触发
            afterSubmit:function (response) {
                //获取修改成功的id
                var bannerId = response.responseJSON.bannerId;
                //获取添加成功后的提示信息
                var mess = response.responseJSON.message;
                // alert(mess);

                //异步请求 上传图片 根据添加成功的id修改图片路径
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/Banner/uplode.do",
                    type:"post",
                    fileElementId:"picpath",
                    data:{bannerId:bannerId},
                    success:function () {
                        //成功之后刷新，保证图片实时显示
                        $("#bannerList").trigger("reloadGrid");
                        //添加成功之后 触发提示框 成功 并3秒之后自动关闭
                        $("#bannerMassId").show().html(mess);

                        // 3秒之后自动 自动隐藏
                        setTimeout(function () {
                            $("#bannerMassId").hide()
                        },3000)

                    }
                });
                return response;

            }
        },
            {

            //添加
                closeAfterAdd:true,
                //添加 添加完成之后触发
                afterSubmit:function (response) {
                    //获取添加成功的id
                    var bannerId = response.responseJSON.bannerId;
                    //获取添加成功后的提示信息
                    var mess = response.responseJSON.message;
                   // alert(mess);

                    //异步请求 上传图片 根据添加成功的id修改图片路径
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/Banner/uplode.do",
                        type:"post",
                        fileElementId:"picpath",
                        data:{bannerId:bannerId},
                        success:function () {
                            //alert("hhhhhhhh");
                            //成功之后刷新，保证图片实时显示
                            $("#bannerList").trigger("reloadGrid");

                            //添加成功之后 触发提示框 成功 并3秒之后自动关闭
                            $("#bannerMassId").show().html(mess);
                            setTimeout(function () {
                                $("#bannerMassId").hide()
                            },3000)
                            /*setTimeout({$("#bannerMassId").hide()},3000)
                                // 3秒之后自动 自动隐藏*/
                        }
                    });
                    return response;

                }

            },
        {
            //删除
        }

        );
    });




function  addSubGrid(subId,bannerId){
    var subGridTableTd = subId + "chapterList";
    var subGridPagerId = subId + "chapterPage";
    $("#"+subId).html(
        "<table id='"+subGridTableTd+"'></table>" +
        "<div id='"+subGridPagerId+"'></div>")

    $("#"+subGridTableTd).jqGrid({
        url:"asdsds",
    })




}



    
</script>
<h3>轮播图管理</h3>
<table id="bannerList"></table>
<div id="pageList"></div>
<!--隐藏   style="display: none" -->

<div id="bannerMassId" class="alert alert-warning alert-dismissible" role="alert" style="display: none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Warning!</strong>
</div>