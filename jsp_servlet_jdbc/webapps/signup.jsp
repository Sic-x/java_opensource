<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>刀塔霸业注册页</title>
		<link rel="stylesheet" href="css/login.css" />
	</head>

	<body>
		<div>
			<table border="1" width="100%" cellspacing="0">
				<tr>
					<th colspan="2">综合练习</th>
				</tr>
				<tr>
					<th>
						<a href="index.jsp">强势登录</a>
					</th>
					<th>莫得账号</th>
				</tr>
			</table>
			<form action="/jsp822/userServlet/regist" method="post"  enctype="multipart/form-data" >
				<table border="1" width="100%" cellspacing="0">
					<tr>
						<th>用户账号：</th>
						<td colspan="2"><input type="text" name="username"></td>
					</tr>
					<tr>
						<th>密码输入：</th>
						<td colspan="2"><input type="text" name="password"></td>
					</tr>
					<tr>
						<th>确认密码：</th>
						<td colspan="2"><input type="text" name="password2"></td>
					</tr>
					<tr>
						<th>用户昵称：</th>
						<td colspan="2"><input type="text" name="nickname"></td>
					</tr>
					<tr>
						<th>验证输入：</th>
						<td><input type="text" name="code"></td>
						<td><img src="img/code.jpg"></td>
					</tr>
					<tr>
						<th>用户头像：</th>
						<td colspan="2"><input type="file" name="filename"></td>
					</tr>
					<tr>
						<th>注册信息：</th>
						<td colspan="2" style="color: red;font-weight: bold;">
							${flag}
						</td>
					</tr>
					<tr>
						<td colspan="3" style="text-align: center;"><input style="width: 100px;" type="submit" value="注册"></td>
					</tr>
				</table>
			</form>
		</div>
	</body>

</html>