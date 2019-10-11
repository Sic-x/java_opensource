<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>刀塔霸业用户登录页面</title>
		<link rel="stylesheet" href="css/login.css" />
	</head>

	<body>
		<div>
			<table border="1" width="100%" cellspacing="0">
				<tr>
					<th colspan="2">综合练习</th>
				</tr>
				<tr>
					<th>强势登录</th>
					<th>
						<a href="signup.jsp">莫得账号</a>
					</th>
				</tr>
			</table>
			<form action="/jsp822/userServlet/login" method="post">
				<table border="1" width="100%" cellspacing="0">
					<tr>
						<th>用户账号：</th>
						<td colspan="2"><input type="text" name="username" value="${user.username}"></td>
					</tr>
					<tr>
						<th>密码输入：</th>
						<td colspan="2"><input type="text" name="password"></td>
					</tr>
					<tr>
						<th>验证输入：</th>
						<td><input type="text" name="code"></td>
						<td><img src="${pageContext.request.contextPath }/randomCode" onclick="this.src='${pageContext.request.contextPath }/randomCode?'+Math.random()"></td>
					</tr>
					<tr>
						<th>登陆信息：</th>
						<td colspan="2" style="color: red;font-weight: bold;">
							${loginFailed}
						</td>
					</tr>
					<tr>
						<td colspan="3" style="text-align: center;"><input style="width: 100px;" type="submit" value="登陆"></td>
					</tr>
				</table>
			</form>
		</div>
	</body>

</html>