<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>新增用户</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css" />
	</head>

	<body>
		<div>
			<div id="head">
				<marquee>沙雕页面，过时语句，垃圾美工，迟早要倒闭</marquee>
				<table border="1" width="100%" cellspacing="0">
					<tr>
						<th><img alt="" src="${pageContext.request.contextPath}/img/one/1.jpg"></th>
						<th><img alt="" src="${pageContext.request.contextPath}/img/one/2.jpg"></th>
						<th><img alt="" src="${pageContext.request.contextPath}/img/one/3.jpg"></th>
						<th><img alt="" src="${pageContext.request.contextPath}/img/one/4.jpg"></th>
						<th><img alt="" src="${pageContext.request.contextPath}/img/one/5.jpg"><br /> 欢迎${user.username }登录&emsp;
							<a href="index.html">注销登录</a>
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
					首页>>>>用户管理>>>>新增数据
				</div>
				<form action="/jsp822/userServlet/add">
					<table border="1" cellspacing="0" width="100%">
						<tr>
							<th>用户名</th>
							<th><input type="text" name="username"></th>
						</tr>
						<tr>
							<th>密码</th>
							<th><input type="text" name="password"></th>
						</tr>
						<tr>
							<th>昵称</th>
							<th><input type="text" name="nickname"></th>
						</tr>
						<tr>
							<th>添加信息</th>
							<th>
								<span class="info">${addError}</span>
							</th>
						</tr>
						<tr>
							<td colspan="2">
								<input type="checkbox"> 添加完成之后，是否跳转主页
							</td>
						</tr>
						<tr>
							<th colspan="2">
								<input type="submit" value="新增数据">
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>

</html>