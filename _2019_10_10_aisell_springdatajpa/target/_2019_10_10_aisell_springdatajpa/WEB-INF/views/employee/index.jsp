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
<div id="toolbar">
    <%--iconCls:图标  plain:简洁--%>
    <shiro:hasPermission name="employee:save">
        <a href="javascript:;" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="employee:update">
        <a href="javascript:;" data-method="update" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="employee:delete">
        <a href="javascript:;" data-method="delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    </shiro:hasPermission>
        <div>
            <form id="searchForm" action="/employee/export" method="post">
            用户名: <input name="username" class="easyui-textbox" style="width:80px">
            邮箱: <input  name="email" class="easyui-textbox" style="width:80px">
            部门: <input class="easyui-combobox" name="departmentId"
                           data-options="valueField:'id',textField:'name',url:'/department/findAll',panelHeight:'auto'" />
            <a href="javascript:;" data-method="search" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">搜索</a>
            <button  class="easyui-linkbutton " data-options="iconCls:'icon-redo',plain:true">导出</button>
            </form>
        </div>
</div>
<%--展示数据使用datagrid组件
    url:请求路径(数据)  fit:自适应父容器
    fitColumns:列的自适应 singleSelect:是否单选
    pagination:分页工具栏
--%>
<table id="edatagrid" class="easyui-datagrid"
       data-options="url:'/employee/page',fitColumns:true,singleSelect:true,fit:true,toolbar:'#toolbar',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'username',width:100">用户名</th>
        <th data-options="field:'headImage',width:100,formatter:imageFormat">头像</th>
        <th data-options="field:'password',width:100">密码</th>
        <th data-options="field:'age',width:100">年龄</th>
        <th data-options="field:'email',width:100">邮箱</th>
        <th data-options="field:'department',width:100,formatter:deptFormat">部门</th>
    </tr>
    </thead>
</table>

<%--添加或者修改的弹出框--%>
<div id="editDialog" class="easyui-dialog" title="数据操作" style="padding: 25px; "
     data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#btns',closed:true,draggable:false,resizable:false">
    <form id="editForm" method="post">
        <input id="employeeId" type="hidden" name = "id" data-options="required:true">


        <table>
            <tr>
                <td>名称</td>
                <td><input class="easyui-validatebox" type="text" name="username" data-options="required:true" /></td>
            </tr>
            <tr data-update="true">
                <td>密码</td>
                <td><input class="easyui-validatebox" type="password" name="password" data-options="required:true" /></td>
            </tr>
            <tr>
                <td> 邮箱</td>
                <td><input class="easyui-validatebox" type="text" name="email" validType='email' data-options="required:true" /></td>
            </tr>
            <tr>
                <td> 年龄</td>
                <td><input class="easyui-validatebox" type="text" name="age" validType='age' data-options="required:true" /></td>
            </tr>
            <tr>
                <td> 部门</td>
                <td>
                    <input id="ecombox" class="easyui-combobox" name="department.id"
                           data-options="valueField:'id',textField:'name',url:'/department/findAll',panelHeight:'auto',required:true" />
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
