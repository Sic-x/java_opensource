<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>源码物流校招</title>
		<link rel="stylesheet" href="../css/bootstrap-theme.min.css" />
		<!--引入bootstrap样式文档-->
		<link rel="stylesheet" href="../css/bootstrap.min.css" />

		<script type="text/javascript" src="../js/jquery.min.js"></script>
		<script type="text/javascript" src="../js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="../css/commons.css" />
	</head>

	<body>
		<!--导航条-->
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="container">
				<!-- 导航上Logo和目录显示 -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#index-navbar" aria-expanded="false">
       						<span class="sr-only">导航目录</span>
        					<span class="icon-bar"></span>
        					<span class="icon-bar"></span>
        					<span class="icon-bar"></span>
      					</button>
					<a class="navbar-brand" href="javascript:void(0);">源码物流校园招聘网</a>
				</div>

				<!-- 导航上其他按钮-->
				<div class="collapse navbar-collapse navbar-right" id="index-navbar">
					<ul class="nav navbar-nav">
						<li>
							<a href="../index">首页</a>
						</li>
						<li>
							<a href="../about.jsp">走进源码</a>
						</li>
						<li>
							<a href="../talents.jsp">人才发展</a>
						</li>
						<li>
							<a href="../join_us_info.jsp">职位列表</a>
						</li>
						<li>
							<a href="../qa.jsp">Q&A</a>
						</li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<!--职位列表-->
		<div class="container nav-next-element" style="border: 1px solid #dcdcdc;padding-top: 30px;padding-bottom: 30px;">
			<span>
				<img src="../imgs/join_us_title.jpg" alt="">
			</span>
			<!--职位名称-->
			<div class="col-md-offset-1" style="padding-top: 30px;">
				<img src="../imgs/join_us_icon.jpg" style="float: left;margin-right: 30px;" />
				<h3>${title}</h3>
			</div>
			<!--职位描述-->
			<div class="col-md-offset-1" style="padding-top: 36px;">
				<h4 style="float: left;margin-right: 30px;">职位描述：</h4>
				<p style="float: left;font-size: 12px;color: #575656;line-height: 40px;">
					${describes}
				</p>
				<div style="clear: both;"></div>
			</div>
			<!--任职要求-->
			<div class="col-md-offset-1" style="padding-top: 36px;">
				<h4 style="float: left;margin-right: 30px;">任职要求：</h4>
				<p style="float: left;font-size: 12px;color: #575656;line-height: 40px;">
					${requires}
				</p>
				<div style="clear: both;"></div>
			</div>
		</div>
		<!--底部-->
		<div class="container-fluid footer-common">
			<p>
				<a href="index.html" class="out-border-left">招聘首页</a>
				<a href="about.html" class="out-border-left">走进源码</a>
				<a href="talents.html" class="out-border-left">人才发展</a>
				<a href="join_us_info.html" class="out-border-left">职位列表</a>
				<a href="qa.html" class="out-border-left">Q&A</a>
			</p>
			<p>企业邮箱：test@test688.com </p>
			<p>电话热线：4000-888-888 传真：020-3333-3333</p>
			<p>公司地址:四川省成都市高新区府城大道西段399号天府新谷1号楼6F</p>
			<p>源码物流版权所有 Copyright © 2018 jobs.digitalchina.ourats.com All rights reserved.蜀ICP备18080118号-1</p>
		</div>
	</body>
</html>