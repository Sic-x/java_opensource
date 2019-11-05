<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/10/12
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <%--引入公共的jsp--%>
    <%@ include file="/WEB-INF/views/head.jsp" %>
    <%--引入当前模板对应的js--%>
    <script src="/js/model/employee.js"></script>
</head>
<body>
<div>
    我的头像:
    </div>
    <form action="/employee/headImage" method="post" enctype="multipart/form-data">
        <input class="easyui-filebox" name="headImage" style="width:200px"
               data-options="prompt:'选择一个文件...',buttonText: '选择本地头像'"/>
        <button class="easyui-linkbutton">上传</button>
    </form>
</body>
</html>
