<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>

	<head>
		<base href="${pageContext.request.contextPath}/"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改用户信息</title>
		<link rel="stylesheet" href="css/main.css" />
		<link rel="stylesheet" href="css/content.css" />
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
						<th><img alt="" src="img/one/5.jpg"><br /> 欢迎${user.username }登录&emsp;
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
					首页>>>>用户管理>>>>修改数据
				</div>
				<form action="/jsp822/userServlet/update">
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
							<th>编辑信息</th>
							<th>
								<span class="info">修改失败</span>
							</th>
						</tr>
						<tr>
							<th colspan="2">
								<input type="submit" value="修改数据">
							</th>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</body>

</html>