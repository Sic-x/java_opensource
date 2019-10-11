<%@ page pageEncoding="UTF-8"%>
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
</head>
<body>
	<!-- Top content -->
	<div class="top-content">
		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>欢迎加入</h1>
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
								<h3>注册</h3>
								<p>
									请注册您的账号密码&emsp;&emsp;
									<span id="message"></span>
								</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-lock"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form" action="system/admin/regsiter" method="post" class="login-form"
							onsubmit="return checkFrom()">
								<div class="form-group">
									<label class="sr-only" for="form-username">用户名</label> <input
										type="text" name="username" placeholder="用户名"
										class="form-username form-control" id="form-username" 
										onblur="checkName(this.value)" onfocus="clearMessage()">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">密码</label> <input
										type="password" name="password" placeholder="密码"
										class="form-password form-control" id="form-password">
								</div>
								<button type="submit" class="btn">注册</button>
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
	<script type="text/javascript">
		var checkUsername = false;
		function checkName(t){
			var obj = document.getElementById("message");//获取信息的节点
			//获取ajax对象
			var xhr = window.XMLHttpRequest?new XMLHttpRequest():new 
			ActiveXObject('Microsoft.XMLHTTP');
			//准备发送请求
			xhr.open("post","system/admin/checkName?username="+t,true);//true -- 发送异步请求，默认的
			//绑定回调函数
			xhr.onreadystatechange = function(){
				if(xhr.readyState==4&&xhr.status==200){//数据接收成功，数据是正确的数据
					var message = xhr.responseText;//获取后台返回的数据
					if(message.indexOf("ok")!=-1){
						obj.style.color="green";
						obj.innerHTML="用户名可以使用";
						checkUsername = true;
					}else if(message.indexOf("null")!=-1){
						obj.style.color="red";
						obj.innerHTML="用户名不能为空";
						checkUsername = false;
					}else{
						obj.style.color="red";
						obj.innerHTML="用户名被占用";
						checkUsername = false;
					}
				}
			}
			xhr.send();//发送请求
		}
		
			function clearMessage(){
				document.getElementById("message").innerHTML="";
			}
			
			//表单提交时验证用户名，验证不通过无法提交表单
			function checkFrom(){
				return checkUsername;
			}
	</script>

	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->
</body>

</html>