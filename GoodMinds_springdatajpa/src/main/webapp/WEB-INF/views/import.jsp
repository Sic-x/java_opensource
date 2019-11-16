<%--
  Created by IntelliJ IDEA.
  User: 谢茂涵
  Date: 2019/10/22
  Time: 12:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
</head>
<body>

    <%--
        上传只能是post请求
        上传必需设置 : enctype="multipart/form-data"
    --%>
    <!-- 上传必需是:post，enctype="multipart/form-data"-->
    <form action="/import/employeeXlsx" method="post" enctype="multipart/form-data">
        <input class="easyui-filebox" name="empFile" style="width:200px"
           data-options="prompt:'选择一个文件...',buttonText: '选择文件'"/>
        <button class="easyui-linkbutton">导入</button>
        <a href="/import/template" class="easyui-linkbutton" >下载模板</a>
    </form>
    <label>请按照格式上传文件，上传失败的员工数据会生成错误文件。</label>
</body>
</html>
