<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>

	<head>
		<base href="${pageContext.request.contextPath}/"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户管理</title>
		<link rel="stylesheet" href="css/main.css" />
	</head>
	<body>
		<div>
			<div id="head">
				<marquee>沙雕页面，过时语句，垃圾美工，迟早要倒闭</marquee>
				<table border="1" width="100%" cellspacing="0">
					<tr>
						<th><img alt="" src="img/one/1.jpg"></th>
						<th><img alt="" src="img/one/2.jpg"></th>
						<th><img alt="" src="img/one/3.jpg"></th>
						<th><img alt="" src="img/one/4.jpg"></th>
						<th><img alt="" src="img/one/5.jpg"><br /> 欢迎${user.username}登录&emsp;
							<a href="index.jsp">注销登录</a>
						</th>
					</tr>
				</table>
			</div>
			<div class="clfloat" id="left">
				<a href="user.jsp">
					<div class="active">用户管理</div>
				</a>
				<a href="category.html">
					<div>商品分类管理</div>
				</a>
				<a href="#">
					<div>商品管理</div>
				</a>
				<a href="file.html">
					<div>文件上传下载</div>
				</a>
				<a href="cart.html">
					<div>购物车功能</div>
				</a>
			</div>
			<div class="clfloat" id="right">
				<div id="nav">
					首页>>>>用户管理
				</div>
				<div>
					<form action="#">
						<input type="text" name="likeQuery" style="width: 300px; height: 20px;"> 
						<input type="submit" value="模糊查询" style="width: 80px; height: 30px;">
					</form>
				</div>
					<table border="1" width="100%" cellspacing="0">
						<tr>
							<th>编号</th>
							<th>用户名</th>
							<th>密码</th>
							<th>昵称</th>
							<th>操作</th>
						</tr>
						<c:forEach items="${userList }" var="e" varStatus="s">
							<tr>
								<td>${s.count }</td>
								<td>${e.username }</td>
								<td>${e.password }</td>
								<td>${e.nickname }</td>
								<td>
									<a href="System/user_add.jsp">新增</a>&emsp;|&emsp;
									<a href="System/user_edit.jsp">编辑</a>&emsp;|&emsp;
									<a href="System/user_delete.jsp">删除</a>
								</td>
							</tr>
						</c:forEach>
					</table>
			</div>
		</div>
	</body>
</html>