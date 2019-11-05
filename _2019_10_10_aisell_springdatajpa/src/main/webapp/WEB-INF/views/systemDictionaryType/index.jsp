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
    <script src="/js/model/systemdictionarytype.js"></script>
</head>
<body>
<div id="toolbar">
    <%--iconCls:图标  plain:简洁--%>
    <%--
        高级查询表单
        思路: 当你点击查询按键的时候，应该通过Ajax请求把用户名与邮件传到后台
              后台接收数据进行查询,刷新datagrid页面 【刷新datagrid的时候把数据传过来】
    --%>
    <form id="searchForm" method="post">
        名称: <input name="name" class="easyui-textbox" style="width:80px">
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>
<%--展示数据使用datagrid组件
    url:请求路径(数据)  fit:自适应父容器
    fitColumns:列的自适应 singleSelect:是否单选
    pagination:分页工具栏
    ctrl+shift+delete
--%>

<table class="easyui-datagrid" id="systemdictionarytypeDataGrid"
       data-options="url:'/systemDictionaryType/page',fitColumns:true,singleSelect:true,fit:true,toolbar:'#toolbar',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'name',width:100">名称</th>
        <th data-options="field:'sn',width:100">编号</th>

    </tr>
    </thead>
</table>



</body>
</html>
