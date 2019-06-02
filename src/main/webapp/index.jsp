<%@page pageEncoding="UTF-8" isELIgnored="false" contentType="text/html; UTF-8" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<br>
<form action="${pageContext.request.contextPath}/User/EasyPoiOutUser" >
<!--导出-->
<input type="submit" value="导出">
</form>
</br>
<form action="${pageContext.request.contextPath}/User/poiInUser" method="post" enctype="multipart/form-data">
    <!--导入-->
        <input type="file"  name="file" value="导入">
    <input type="submit" value="提交">

</form>

</body>
</html>