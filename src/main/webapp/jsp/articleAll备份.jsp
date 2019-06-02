<%@ page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<script>
    $(function () {
        $("#articleList").jqGrid({
            //获取数据的地址
            url:"${pageContext.request.contextPath}/Article/showArticlePager.do",
            //增删改 发送 请求
            editurl:"${pageContext.request.contextPath}/Article/edit.do",
             // 定义服务器返回的类型  datatype 固定格式
            datatype:"json",
            //定义列名
            //  colNames:["编号","id","标题","状态","描述","创建时间","图片"],
            colNames:["编号","标题","内容","日期","上师id","作者","操作"],
            //启用单元格编辑模式
            //  colModel 填充 数据库 查出 的具体数据
            //postData:"ids",
            colModel:[
                {name:"id",hidden:true},
                {name:"title",editable:true},
                {name:"content",hidden:true},
                {name:"publictime"},
                {name:"guruid",hidden: true},
                {name:"author",editable:true},
                {name:"",editable:true,
                        formatter:function (cellValue, opo, value) {
                            return"<a href='#' onclick=\"lookInfor('"+value.id+"')\">查看详情</a>" +
                                 " <a href='#' onclick=\"updataModel('"+value.id+"')\"> 更新文章</a>";
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
           // caption:"文章信息列表展示",//指定表格名称
            height:'200px',

        }).jqGrid("navGrid","#pageList",{add: false, edit: false, del:"删除", search:false},
            {},
            {},
            {
                //删除

                 }


        )


    });
    //查看详情事件
    function lookInfor(id) {
        //通过 getRowData 方法 传id 返回对应的对象
       var value = $("#articleList").jqGrid("getRowData",id);
       //给对应的form表单复制
        //展示表单
        $("#myModal1").modal("show");
        //给 title 赋值
        $("#title1").val(value.title);
        //对状态进行判断赋值
        if(value.status=='展示'){
            $("#status1").val('展示');
        }else {
            $("#status1").val('不展示');
        }
        //给作者赋值
         $("#author1").val(value.author);
      //动态添加两个按钮触发事件
       /* $("#addArticleFrom").html(" <button type=\"button\" class=\"btn btn-primary\" onclick=\"saveModal()\">确定</button>\n" +
            "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>");
*/
        //初始化
        //触发 kindeditor 初始化
        KindEditor.create('#editor1',{
            uploadJson:"${pageContext.request.contextPath}/Article/uploadImg.do",
            filePostName:"img", //默认是 imgFile
            fileManagerJson:"${pageContext.request.contextPath}/Article/getAll.do",
            allowFileManager:true,
            resizeType:1, //只能改变高度  不能改变宽度
            afterBlur:function (url) {
                //同步KindEditor的值到textarea文本框中
                this.sync();
            }
        });

        //获取文本框内容进行展示
      //  $("#editor").val(value.content);
        //获取文本框内容进行展示  加图片
        KindEditor.html("#editor1",value.content);
    }




    //修改文章信息  事件
    function updataModel(id) {
        //通过 getRowData 方法 传id 返回对应的对象
        var value = $("#articleList").jqGrid("getRowData",id);
        //给对应的form表单复制
        //展示表单
        $("#myModal2").modal("show");
        //给 id 赋值
        $("#id2").val(value.id);
        //给 title 赋值
        $("#title2").val(value.title);
        //对状态进行判断赋值
        if(value.status=='展示'){
            $("#status2").val('展示');
        }else {
            $("#status2").val('不展示');
        }
        //给作者赋值
        $("#author2").val(value.author);
        //动态添加两个按钮触发事件
        /* $("#addArticleFrom").html(" <button type=\"button\" class=\"btn btn-primary\" onclick=\"saveModal()\">确定</button>\n" +
             "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>");
        */
        //初始化
        //触发 kindeditor 初始化
        KindEditor.create('#editor2',{
            uploadJson:"${pageContext.request.contextPath}/Article/uploadImg.do",
            filePostName:"img", //默认是 imgFile
            fileManagerJson:"${pageContext.request.contextPath}/Article/getAll.do",
            allowFileManager:true,
            resizeType:1, //只能改变高度  不能改变宽度
            afterBlur:function (url) {
                //同步KindEditor的值到textarea文本框中
                this.sync();
            }
        });
        //获取文本框内容进行展示
        //  $("#editor").val(value.content);
        //获取文本框内容进行展示  加图片
        KindEditor.html("#editor2",value.content);
    }



    //模态框事件 展示模态框
    function showModal() {
       $("#myModal").modal("show");
       //每次展示之前 清空以前的数据   $("#addArticleFrom")[0] 表示拿到原生的dom对象
        $("#addArticleFrom")[0].reset();
        //清空 textarea 内容
        KindEditor.html("#editor","");
        //动态添加两个按钮触发事件
       /* $("#addArticleFrom").html(" <button type=\"button\" class=\"btn btn-primary\" onclick=\"addModal()\">提交</button>\n" +
                                   "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>");*/

       //触发 kindeditor 初始化
        KindEditor.create('#editor',{
            uploadJson:"${pageContext.request.contextPath}/Article/uploadImg.do",
            filePostName:"img", //默认是 imgFile
            fileManagerJson:"${pageContext.request.contextPath}/Article/getAll.do",
             allowFileManager:true,
            resizeType:1, //只能改变高度  不能改变宽度
            afterBlur:function (url) {
                //同步KindEditor的值到textarea文本框中
            this.sync();
        },
            show:false,

        });



        function saveModal() {
            alert("g");
            $.ajax({
                url:"xxxx",
            });
        }
    }

</script>
<h3>文章管理</h3>

<!--标签页-->
<div>
    <!-- Nav tabs -->
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active">
            <a href="#home" aria-controls="home" role="tab" data-toggle="tab">文章信息</a>
        </li>
        <li ><a href="Javascript:void(0)" onclick="showModal()" ><b>添加文章</b></a></li>
     <%--<li ><a href="Javascript:void(0)" onclick="updateModal()" ><b>更新文章</b></a></li>--%>
    </ul>

</div>


<table id="articleList"></table>
<div id="pageList"></div>


<!--隐藏   style="display: none" -->
<div id="articleMassId" class="alert alert-warning alert-dismissible" role="alert" style="display: none">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
    <strong>Warning!</strong>
</div>


<!--添加的模态框 默认不展示 需要触发事件才可展示-->
<div class="modal fade" id="myModal">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px">
            <!--模态框标题-->
            <div class="modal-header">
                <!-- 用来关闭模态框的属性:data-dismiss="modal"   -->

                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title">编辑文章信息</h4>
            </div>

            <!--模态框内容体-->
            <div class="modal-body">
                <form action="" class="form-horizontal"
                      id="addArticleFrom">
                    <div class="form-group">
                        <label class="col-sm-1 control-label">标题</label>
                        <div class="col-sm-5">
                            <input type="text" name="title" id="title" placeholder="请输入标题" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="status" id="status">
                                <option value="展示">展示</option>
                                <option value="不展示">不展示</option>
                            </select>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">作者</label>
                        <div class="col-sm-5">
                            <input type="text" name="author" id="author" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <!--文本框-->
                        <div class="col-sm-12">
                            <textarea id="editor" name="content" style="width:700px;height:300px;"></textarea>
                        </div>
                    </div>
                    <input id="addInsertImg" name="insertImg" hidden>
                </form>
            </div>


            <!--模态页脚-->
            <div class="modal-footer" id="modal_footer">
                <!--  data-dismiss="modal"  作用：点击提交之后 发送请求到后台  并 关闭 模态框-->
                <button type="button" class="btn btn-primary" onclick="addModal()" data-dismiss="modal">提交</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>
                <script>
                    // 添加模态框 添加一条文章信息
                    function addModal() {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/Article/addArticle.do",
                            type: "post",
                            datatype: "json",
                            data: $("#addArticleFrom").serialize(),
                            success: function () {
                            }
                        });
                    }
                </script>
            </div>
        </div>
    </div>
</div>





<!--查看详情的模态框 默认不展示 需要触发事件才可展示-->
<div class="modal fade" id="myModal1">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px">
            <!--模态框标题-->
            <div class="modal-header">
                <!--
                    用来关闭模态框的属性:data-dismiss="modal"
                -->
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title">文章详情展示</h4>
            </div>

            <!--模态框内容体-->
            <div class="modal-body">
                <form action="" class="form-horizontal"
                      id="addArticleFrom1">
                    <div class="form-group">
                        <label class="col-sm-1 control-label">id</label>
                        <div class="col-sm-5">
                            <input type="text" name="id" id="id1" placeholder="文章id" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">标题</label>
                        <div class="col-sm-5">
                            <input type="text" name="title" id="title1" placeholder="请输入标题" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="status" id="status1">
                                <option value="展示">展示</option>
                                <option value="不展示">不展示</option>
                            </select>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">作者</label>
                        <div class="col-sm-5">
                            <input type="text" name="author" id="author1" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <!--文本框-->
                        <div class="col-sm-12">
                            <textarea id="editor1" name="content" style="width:700px;height:300px;"></textarea>
                        </div>
                    </div>
                    <input id="addInsertImg1" name="insertImg" hidden>
                </form>
            </div>
            <!--模态页脚-->
            <div class="modal-footer" id="modal_footer1">
                <!--  data-dismiss="modal"  作用：点击提交之后 发送请求到后台  并 关闭 模态框-->
       <%-- <button type="button" class="btn btn-primary" data-dismiss="modal">提交</button>--%>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>

            </div>
        </div>
    </div>
</div>


<!--修改更新文章的模态框 默认不展示 需要触发事件才可展示-->
<div class="modal fade" id="myModal2">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px">
            <!--模态框标题-->
            <div class="modal-header">
                <!--
                    用来关闭模态框的属性:data-dismiss="modal"
                -->
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title">更新文章信息</h4>
            </div>

            <!--模态框内容体-->
            <div class="modal-body">
                <form action="" class="form-horizontal"
                      id="addArticleFrom2">
                    <div class="form-group">
                        <label class="col-sm-1 control-label">id</label>
                        <div class="col-sm-5">
                            <input type="text" name="id" id="id2" placeholder="文章id" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">标题</label>
                        <div class="col-sm-5">
                            <input type="text" name="title" id="title2" placeholder="请输入标题" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="status" id="status2">
                                <option value="展示">展示</option>
                                <option value="不展示">不展示</option>
                            </select>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">作者</label>
                        <div class="col-sm-5">
                            <input type="text" name="author" id="author2" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <!--文本框-->
                        <div class="col-sm-12">
                            <textarea id="editor2" name="content" style="width:700px;height:300px;"></textarea>
                        </div>
                    </div>
                    <input id="addInsertImg2" name="insertImg" hidden>
                </form>
            </div>
            <!--模态页脚-->
            <div class="modal-footer" id="modal_footer2">
                <!--  data-dismiss="modal"  作用：点击提交之后 发送请求到后台  并 关闭 模态框-->
                <button type="button" class="btn btn-primary" onclick="updateModal()" data-dismiss="modal">确认更新</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">取消</button>

          <script>
                    // 添加模态框 添加一条文章信息
                    function updateModal() {
                        $.ajax({
                            url: "${pageContext.request.contextPath}/Article/updateArticle.do",
                            type: "post",
                            datatype: "json",
                            data: $("#addArticleFrom").serialize(),
                            success: function () {

                            }
                        });
                    }
                </script>
            </div>
        </div>
    </div>
</div>