<%@ page contentType="text/html; UTF-8" isELIgnored="false" pageEncoding="UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <!--引入 kindeditor js 复选框文本编辑器-->
    <script charset="UTF-8" src="kindeditor-all-min.js"></script>
    <script charset="UTF-8" src="lang/zh-CN.js"></script>

    <!--使用kindeditor-->
    <script>
        <!-- 初始化 -->
        KindEditor.ready(function (k) {
            k.create('#editor_id',{
                uploadJson:"${pageContext.request.contextPath}/Article/uploadImg.do",//本地上传路径
                filePostName:"img",//传值  后台以 img进行接收所传值
                fileManagerJson:"${pageContext.request.contextPath}/Article/getAll.do",   //指定浏览远程图片的服务器端程序
                allowFileManager:true,// 显示浏览远程服务器按钮
            });
        })
    </script>
</head>
<body>

<form action="${pageContext.request.contextPath}/Article/addKindeditor">
    <!--定义一个文本域-->
    <textarea  id="editor_id"  name="content" style="height: 300px ;width: 700px"  cols="30" rows="10">
     </textarea>
           <input type="submit" value="点我提交">
</form>

     <%--   <input type="text" placeholder="请输入----"/>--%>

</body>
</html>