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
    <script src="/js/model/product.js"></script>
</head>
<body>
<div id="toolbar">
    <%--iconCls:图标  plain:简洁--%>
        <a href="javascript:;" data-method="add" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true">添加</a>
        <a href="javascript:;" data-method="update" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true">修改</a>
        <a href="javascript:;" data-method="delete" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true">删除</a>
    <div>
        <form id="searchForm">
            产品名: <input name="name" class="easyui-textbox" style="width:80px">
            产品类型: <input class="easyui-combobox" name="productTypeId"
                       data-options="valueField:'id',textField:'name',url:'/productType/findChildren',panelHeight:'auto'" />
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
       data-options="url:'/product/page',fitColumns:true,singleSelect:true,fit:true,toolbar:'#toolbar',pagination:true">
    <thead>
    <tr>
        <th data-options="field:'name',width:100">产品名</th>
        <th data-options="field:'color',width:100,formatter:colorFormat">颜色</th>
        <th data-options="field:'pic',width:100,formatter:imageFormat">图片</th>
        <th data-options="field:'smallPic',width:100">小图片</th>
        <th data-options="field:'costPrice',width:100">成本价</th>
        <th data-options="field:'salePrice',width:100">销售价</th>
        <th data-options="field:'productType',width:100,formatter:productTypeFormat">产品类型</th>
        <th data-options="field:'unit',width:'100',align:'center',formatter:unitFormat">单位</th>
        <th data-options="field:'brand',width:'100',align:'center',formatter:brandFormat">品牌</th>
    </tr>
    </thead>
</table>

<%--添加或者修改的弹出框--%>
<div id="editDialog" class="easyui-dialog" title="数据操作" style="padding: 25px; "
     data-options="iconCls:'icon-save',resizable:true,modal:true,buttons:'#btns',closed:true,draggable:false,resizable:false">
    <form id="editForm" method="post">
        <input id="productId" type="hidden" name = "id" data-options="required:true">


        <table>
            <tr>
                <td>产品名</td>
                <td><input class="easyui-validatebox" type="text" name="name" data-options="required:true" /></td>
            </tr>
            <tr>
                <td>颜色</td>
                <td><input class="easyui-validatebox" type="text" name="color" data-options="required:true" /></td>
            </tr>
            <tr>
                <td> 成本价</td>
                <td><input class="easyui-validatebox" type="text" name="costPrice" validType='costPrice' data-options="required:true" /></td>
            </tr>
            <tr>
                <td> 销售价</td>
                <td><input class="easyui-validatebox" type="text" name="salePrice" validType='salePrice' data-options="required:true" /></td>
            </tr>
            <tr>
                <td> 产品类型</td>
                <td>
                    <input id="ecombox" class="easyui-combobox" name="productType.id"
                           data-options="valueField:'id',textField:'name',url:'/productType/findAll',panelHeight:'auto',required:true" />
                </td>
            </tr>
            <tr>
                <td> 单位</td>
                <td>
                    <input  id="ecombox2" class="easyui-combobox" name="brand.id"
                           data-options="valueField:'id',textField:'name',url:'/systemDictionaryDetail/findBrand',panelHeight:'auto',required:true" />
                </td>
            </tr>
            <tr>
                <td> 品牌</td>
                <td>
                    <input  id="ecombox3" class="easyui-combobox" name="unit.id"
                           data-options="valueField:'id',textField:'name',url:'/systemDictionaryDetail/findUnit',panelHeight:'auto',required:true" />
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
