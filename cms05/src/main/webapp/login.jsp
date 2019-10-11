<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.request.contextPath }/"/>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>login</title>
<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/form-elements.css">
<link rel="stylesheet" href="assets/css/style.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
	<script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

<!-- Favicon and touch icons -->
<link rel="shortcut icon" href="assets/ico/favicon.png">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png">
<style type="text/css">
.form-bottom form button.btn {
	width: 20%;
	float: right;
	margin: 2px;
}

.form-bottom {
	padding: 25px 25px 30px 25px;
	background: #444;
	background: rgba(0, 0, 0, 0.3);
	-moz-border-radius: 0 0 4px 4px;
	-webkit-border-radius: 0 0 4px 4px;
	border-radius: 0 0 4px 4px;
	text-align: left;
	height: 330px;
}
</style>
</head>

<body>

	<!-- Top content -->
	<div class="top-content">
		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>欢迎登陆本系统</h1>
						<div class="description">
							<p>
								<!-- 如您还没有加入我们,点击下方注册链接加入我们,祝你身体健康，路途愉快！ -->
							</p>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>登陆</h3>
								<p>
									${loginError!=null?loginError:"输入您的账号密码" }
								</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-lock"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form" action="system/admin/login" method="post" id="form"
								class="login-form">
								<div class="form-group">
									<label class="sr-only" for="form-username">用户名</label> <input
										type="text" name="username" placeholder="用户名"
										class="form-username form-control" id="form-username">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">密码</label> <input
										type="password" name="password" placeholder="密码"
										class="form-password form-control" id="form-password">
								</div>
								<div class="form-group">
									<div class="row">
										 <div class="col-md-9">
											<label class="sr-only" for="form-code">验证码</label> <input
												type="text" name="inputCode" placeholder="验证码"
												class="form-code form-control" id="form-code">
										 </div>
										 <div class="col-md-3">
											 <img alt="" src="code" style="border-radius:8px" onclick="this.src='code?'+Math.random()" >
										 </div>
									</div>
								</div>
								<div>
									<span><button type="submit" class="btn">登陆</button></span> <span><a
										href="register.jsp ">
											<button type="button" class="btn">注册</button>
									</a></span>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Javascript -->
	<script src="assets/js/jquery-1.11.1.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/scripts.js"></script>

	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
</body>

</html>