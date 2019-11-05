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
    <script src="/js/model/menu.js"></script>
</head>
<body>
<div id="toolbar">
    <%--iconCls:图标  plain:简洁--%>
        <a href="javascript:;" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
        <a href="javascript:;" data-method="update" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
        <a href="javascript:;" data-method="delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <%--
        高级查询表单
        思路: 当你点击查询按键的时候，应该通过Ajax请求把用户名与邮件传到后台
              后台接收数据进行查询,刷新datagrid页面 【刷新datagrid的时候把数据传过来】
    --%>
    <form id="searchForm" method="post">
        权限: <input name="name" class="easyui-textbox" style="width:80px">
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    </form>
</div>
<%--展示数据使用datagrid组件
    url:请求路径(数据)  fit:自适应父容器
    fitColumns:列的自适应 singleSelect:是否单选
    pagination:分页工具栏
    ctrl+shift+delete
--%>

<table class="easyui-datagrid" id="menuDataGrid"
       data-options="url:'/menu/page',fitColumns:true,singleSelect:true,fit:true,toolbar:'#toolbar',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'name',width:100">名称</th>
        <th data-options="field:'icon',width:100">图标</th>
        <th data-options="field:'url',width:100">路径</th>
    </tr>
    </thead>
</table>

<%--添加或者修改的弹出框--%>
<div id="editDialog" class="easyui-dialog" title="数据操作" style="padding: 25px; "
     data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#btns',closed:true">
    <form id="editForm" method="post">
        <input id="menuId" type="hidden" name="id" />
        <table>
            <tr>
                <td>名称</td>
                <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
            </tr>
            <tr>
                <td>图标</td>
                <td><input class="easyui-validatebox" type="text" name="icon" data-options="required:true" /></td>
            </tr>
            <tr>
                <td>路径</td>
                <td><input class="easyui-validatebox" type="text" name="url" data-options="required:true" /></td>
            </tr>
        </table>
    </form>
    <div id="btns">
        <a href="javascript:;" data-method="save" class="easyui-linkbutton " data-options="iconCls:'icon-ok',plain:true">确定</a>
        <a href="javascript:;" data-method="reset" class="easyui-linkbutton " data-options="iconCls:'icon-reload',plain:true">重置</a>
        <a href="javascript:;" data-method="close" class="easyui-linkbutton " data-options="iconCls:'icon-cancel',plain:true">取消</a>
    </div>
</div>


</body>
</html>
