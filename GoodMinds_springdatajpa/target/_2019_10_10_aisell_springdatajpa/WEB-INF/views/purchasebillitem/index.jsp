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

    <!-- 引入datagrid-view的支持 -->
    <script type="text/javascript" src="/easyui/plugins/datagrid-groupview.js"></script>
    <%--引入当前模板对应的js--%>
    <script src="/js/model/purchasebillitem.js"></script>
</head>
<body>
<div id="toolbar">

    <%--
        高级查询表单
        思路: 当你点击查询按键的时候，应该通过Ajax请求把用户名与邮件传到后台
              后台接收数据进行查询,刷新datagrid页面 【刷新datagrid的时候把数据传过来】
    --%>
    <form id="searchForm" method="post">
        交易时间: <input name="beginDate" class="easyui-datebox" style="width:120px"> -
        <input name="endDate" class="easyui-datebox" style="width:120px">
        状态:
        <select  class="easyui-combobox" name="status" style="width:100px;" panelHeight='auto'>
            <option value="">--请选择--</option>
            <option value="0">未审核</option>
            <option value="1">已审核</option>
            <option value="-1">已作废</option>
        </select>
        <select  class="easyui-combobox" name="groupBy" style="width:100px;" panelHeight='auto'>
            <option value="1">供应商分组</option>
            <option value="2">采购员分组</option>
            <option value="3">月份分组</option>
        </select>
        <a href="#" data-method="search" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a href="#" data-method="pie" class="easyui-linkbutton" iconCls="icon-undo">生成报表</a>
    </form>
</div>
<%--展示数据使用datagrid组件
    url:请求路径(数据)  fit:自适应父容器
    fitColumns:列的自适应 singleSelect:是否单选
    pagination:分页工具栏
    ctrl+shift+delete
--%>
<table id="purchaseBillItemGrid"></table>

    <div id="btns">
        <a href="javascript:;" data-method="close" class="easyui-linkbutton " data-options="iconCls:'icon-cancel',plain:true">关闭</a>
    </div>
</div>


<div id="pieDialog" class="easyui-dialog" title="报表" style="padding: 25px;width: 500px;height: 560px"
     data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#btns',closed:true,draggable:false">
    <iframe src="/3dpie/01_3d_pie.jsp" frameborder="0" width="100%" height="100%"></iframe>
</div>



</body>
</html>
