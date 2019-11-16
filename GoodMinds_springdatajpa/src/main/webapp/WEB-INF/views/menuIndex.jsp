<%--
  Created by IntelliJ IDEA.
  User: 谢茂涵
  Date: 2019/10/15
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title>Title</title>
    <%--引入公共的jsp--%>
    <%@ include file="/WEB-INF/views/head.jsp" %>

    <%--拓展--%>
    <link href="/css/jeasyui.extensions.tabs.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="/js/model/jeasyui.extensions.tabs.dndTab.js"></script>
    <%--引入当前模板对应的js--%>
    <script src="/js/model/index.js"></script>
</head>
    <body class="easyui-layout">
        <!-- region
            north(上) south(下)	 east(右)	west(左)  center(中)
            split 拖拽边框
        -->
        <div data-options="region:'north'" style="height:100px;">
            <!--上方的内容-->
            <h1>Xxx 管理系统 </h1>
            <div style="text-align: right;padding-right: 50px">
                欢迎您,<shiro:principal property="username"/>
                <a href="/logout">注销</a>
            </div>
        </div>
        <div data-options="region:'west',title:'管理菜单',split:true" style="width:250px;">
            <!--左边的菜单 -->
            <ul id="mtree" class="easyui-tree"></ul>

        </div>
        <div data-options="region:'center'" id="etabs" class="easyui-tabs" style="padding:0px;background:#eee;">
            <!--
                padding: 外边距
                closable:true  x 关闭按钮
                iconCls: 图标
            -->
            <!--主题内容-->
            <div title="主页" data-options="closable:true" style="padding:20px;display:none;">
                <h2>欢迎来到  Xxx 管理系统 </h2>
            </div>
        </div>
        <div data-options="region:'south'" style="height:40px; text-align: center">
            <span>®2006-2019XXX有限公司 版权所有</span>
        </div>

        <%--右键菜单--%>
        <div id="rcmenu" class="easyui-menu" style="width:150px;">
            <div id="closeAll">全部关闭</div>
            <div id="closeOther">关闭其他</div>
            <div id="closeRight">关闭右侧</div>
            <div id="closeLeft">关闭左侧</div>
        </div>
    </body>
</html>
