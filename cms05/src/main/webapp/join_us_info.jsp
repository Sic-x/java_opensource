<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

	<head>
		<base href="${pageContext.request.contextPath}/">
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
		<title>源码物流校招</title>
		<link rel="stylesheet" href="css/bootstrap-theme.min.css" />
		<!--引入bootstrap样式文档-->
		<link rel="stylesheet" href="css/bootstrap.min.css" />

		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="css/commons.css" />
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
							<a href="index">首页</a>
						</li>
						<li>
							<a href="about.html">走进源码</a>
						</li>
						<li>
							<a href="talents.html">人才发展</a>
						</li>
						<li>
							<a href="javascript:void(0);">职位列表</a>
						</li>
						<li>
							<a href="qa.html">Q&A</a>
						</li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</nav>
		<!--职位搜索-->
		<div class="container" style="border: 1px solid #dcdcdc;padding-top: 30px;padding-bottom: 30px;margin-top: 60px;">
			<span>
				<img src="imgs/join_us_search.jpg" alt="">
			</span>
			<div class="row" style="padding-top: 30px;">
				<form class="form-inline" action="jobs/list">
					<div class="form-group col-md-3">
						<label for="jobTitle">职位名称</label>
						<input type="text" class="form-control" name="title" id="jobTitle" 
						placeholder="职位名称" value="${condition.title}">
					</div>
					<div class="form-group col-md-3" style="padding-top: 5px;">
						<label for="workingTime">工作时间:</label>
						<label class="radio-inline">
							<input type="radio" name="positiontype" id="workingTime" value="" 
							<c:if test="${condition.positiontype==null}">checked="checked"</c:if>
							> 全部
						</label>
						<label class="radio-inline">
							<input type="radio" name="positiontype" id="workingTime" value="1"
							<c:if test="${condition.positiontype==1}">checked="checked"</c:if>
							> 全职
								
						</label>
						<label class="radio-inline">
							<input type="radio" name="positiontype" id="workingTime" value="0"
							<c:if test="${condition.positiontype==0}">checked="checked"</c:if>
							> 兼职
							
						</label>
					</div>
					<button type="submit" class="btn btn-default">搜索职位</button>
				</form>
			</div>
		</div>
		<!--职位列表-->
		<div class="container job-table">
			<span>
				<img src="imgs/index_title_zw.jpg" alt="">
				<img src="imgs/index_title_more.jpg" alt="">
			</span>
			<table class="table table-hover">
				<tr>
					<th class="hidden-sm">编号</th>
					<th>工作职位</th>
					<th>地点</th>
					<th>人数</th>
					<th>薪资待遇</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr>
				<tr><c:forEach items="${pageBean.list}" var="j">
					<tr>
						<th>#${j.id}</th>
						<th>${j.title}</th>
						<th>${j.cname}</th>
						<th>${j.jobnum}</th>
						<th>${j.treatment}</th>
						<th>${j.inputdate}</th>
						<th>
							<a href="templates/${j.htmlurl}">职位详情</a>
						</th>
					</tr>	
				</c:forEach>
			</table>
			<!--分页-->
			<nav class="navbar-right">
				<ul class="pagination" id="paging">
					<li>
						<span>当前第${pageBean.localPage}页</span>
					</li>
					<li>
						<a href="jobs/list?localPage=1&title=${condition.title}&positiontype=${condition.positiontype}">
							<span aria-hidden="true">首页</span>
						</a>
					</li>
					<li>
						<a href="jobs/list?localPage=${pageBean.prePage}&title=${condition.title}&positiontype=${condition.positiontype}" aria-label="上一页">
							<span aria-hidden="true">上一页</span>
						</a>
					</li>
					<li>

					</li>
					<li>
						<a href="jobs/list?localPage=${pageBean.nextPage}&title=${condition.title}&positiontype=${condition.positiontype}" aria-label="下一页">
							<span aria-hidden="true">下一页</span>
						</a>
					</li>
					<li>
						<a href="jobs/list?localPage=${pageBean.lastPage}&title=${condition.title}&positiontype=${condition.positiontype}" aria-label="尾页">
							<span aria-hidden="true">尾页</span>
						</a>
					</li>
					<li>
						<span>总页数：共${pageBean.totalPage}页</span>
						<span>总数据：共${pageBean.totalNum}条</span>
					</li>
				</ul>
			</nav>
		</div>
		<!--友情链接  手机端的时候，就隐藏掉-->
		<div class="container hidden-xs hidden-sm" id="footer-link">
			<img src="imgs/index_link_img.jpg" alt="" class="out-border-left">
			<a href="index" class="out-border-left">源码时代官网</a>
			<a href="www.bootcss.com" class="out-border-left">BootStrap官网</a>
		</div>
		<!--底部-->
		<div class="container-fluid footer-common">
			<p>
				<a href="javascript:void(0);" class="out-border-left">招聘首页</a>
				<a href="about.html" class="out-border-left">走进源码</a>
				<a href="talents.html" class="out-border-left">人才发展</a>
				<a href="jobs/list" class="out-border-left">职位列表</a>
				<a href="qa.html" class="out-border-left">Q&A</a>
			</p>
			<p>企业邮箱：test@test688.com </p>
			<p>电话热线：4000-888-888 传真：020-3333-3333</p>
			<p>公司地址:四川省成都市高新区府城大道西段399号天府新谷1号楼6F</p>
			<p>源码物流版权所有 Copyright © 2018 jobs.digitalchina.ourats.com All rights reserved.蜀ICP备18080118号-1</p>
		</div>
	</body>
</html>