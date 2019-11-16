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
    <script src="/js/model/productType.js"></script>
</head>
<body>
<div id="toolbar">
    <%--iconCls:图标  plain:简洁--%>
    <a href="javascript:;" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加产品</a>
    <a href="javascript:;" data-method="update" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    <a href="javascript:;" data-method="delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <div>
        <form id="searchForm">
            用户名: <input name="name" class="easyui-textbox" style="width:80px">
            产品类型: <input class="easyui-combobox" name="parentId"
                         data-options="valueField:'id',textField:'name',url:'/productType/findParent',panelHeight:'auto'" />
            <a href="javascript:;" data-method="search" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
        </form>
    </div>
</div>
<%--展示数据使用datagrid组件
    url:请求路径(数据)  fit:自适应父容器
    fitColumns:列的自适应 singleSelect:是否单选
    pagination:分页工具栏
--%>
<table id="edatagrid" class="easyui-datagrid"
       data-options="url:'/productType/page',fitColumns:true,singleSelect:true,fit:true,toolbar:'#toolbar',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'name',width:100">产品名称</th>
        <th data-options="field:'descs',width:100">描述</th>
        <th data-options="field:'parent',width:100,formatter:parentFormat">产品类型</th>
    </tr>
    </thead>
</table>

<%--添加或者修改的弹出框--%>
<div id="editDialog" class="easyui-dialog" title="数据操作" style="padding: 25px; "
     data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#btns',closed:true,draggable:false,resizable:false">
    <form id="editForm" method="post">
        <input id="productTypeId" type="hidden" name = "id" data-options="required:true">


        <table>
            <tr>
                <td>产品名称</td>
                <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
            </tr>
            <tr>
                <td>描述</td>
                <td><input class="easyui-validatebox" type="text" name="descs" data-options="required:true" /></td>
            </tr>
            <tr>
                <td> 产品类型</td>
                <td>
                    <input id="ecombox" class="easyui-combobox" name="parent.id"
                           data-options="valueField:'id',textField:'name',url:'/productType/findParent',panelHeight:'auto'" />
                </td>
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
