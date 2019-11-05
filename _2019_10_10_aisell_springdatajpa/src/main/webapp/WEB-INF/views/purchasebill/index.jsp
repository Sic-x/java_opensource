<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/10/12
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--要启用</shiro:hasPermission>必须添加此头--%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
    <%--引入公共的jsp--%>
    <%@ include file="/WEB-INF/views/head.jsp" %>

    <%--引入拓展--%>
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.getColumnInfo.js"></script>
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.editors.js"></script>
    <script src="/easyui/plugin/cellEdit/jeasyui.extensions.datagrid.edit.cellEdit.js"></script>

    <%--引入当前模板对应的js--%>
    <script src="/js/model/purchasebill.js"></script>

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
            交易时间: <input name="beginDate" class="easyui-datebox" style="width:120px"> -
            <input name="endDate" class="easyui-datebox" style="width:120px">
            状态:<select  class="easyui-combobox" name="status" style="width:100px;" panelHeight='auto'>
            <option ></option>
            <option value="0">待审</option>
            <option value="1">已审</option>
            <option value="-1">作废</option>
        </select>
            <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        </form>
</div>



<%--展示数据使用datagrid组件
    url:请求路径(数据)  fit:自适应父容器
    fitColumns:列的自适应 singleSelect:是否单选
    pagination:分页工具栏
    ctrl+shift+delete
--%>

<table class="easyui-datagrid" id="purchasebillDataGrid"
       data-options="url:'/purchaseBill/page',fitColumns:true,singleSelect:true,fit:true,toolbar:'#toolbar',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'vdate',width:100">交易时间</th>
        <th data-options="field:'totalAmount',width:100">总金额</th>
        <th data-options="field:'totalNum',width:100">总数量</th>
        <th data-options="field:'status',width:100,formatter:statusFormat">订单状态</th>
        <th data-options="field:'supplier',width:100,formatter:supplierFormat">供应商</th>
        <th data-options="field:'buyer',width:100,formatter:buyerFormat">采购员</th>

    </tr>
    </thead>
</table>

<%--添加或者修改的弹出框--%>
<div id="editDialog" class="easyui-dialog" title="数据操作" style="padding: 25px; "
     data-options="iconCls:'icon-save',height:500,resizable:true,modal:true,buttons:'#btns',closed:true">
    <form id="editForm" method="post">
        <input id="purchasebillId" type="hidden" name="id" />
        <table>
            <tr>
                <td>交易日期</td>
                <td><input class="easyui-datebox" type="text" name="vdate" data-options="required:true" /></td>


                <td> 供应商</td>
                <td>
                    <input  class="easyui-combobox" name="supplier.id"
                           data-options="valueField:'id',textField:'name',url:'/supplier/findAll',panelHeight:'auto',required:true" />
                </td>


                <td> 采购员</td>
                <td>
                    <input  class="easyui-combobox" name="buyer.id"
                           data-options="valueField:'id',textField:'username',url:'/employee/findBuyers',required:true" />
                </td>
            </tr>
            <table id="dg1"></table>
            <%--拓展toolbar--%>
            <div id="toolbarDG">
                <a href="javascript:;" id="btnInsert" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
                <a href="javascript:;" id="btnRemove" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
            </div>
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
