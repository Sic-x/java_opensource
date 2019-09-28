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
		<style>
			.tableA {
				margin-right: 15px;
			}
		</style>
	</head>

	<body>
		<div class="breadcrumbs" id="breadcrumbs">
			<!-- 面包屑导航 -->
			<ul class="breadcrumb">
				<li>
					<a href="javascript:void(0);">Home</a>
				</li>
				<li class="active">职位管理&emsp;<a href="system/jobs/addPage" class="btn-default tableA"><span class="glyphicon glyphicon-plus" aria-hidden="true">添加职位</span></a></li>
			</ul>
		</div>
		<!--职位列表-->
		<div class="container job-table">
			<table class="table table-hover">
				<tr>
					<th class="hidden-sm">编号</th>
					<th>工作职位</th>
					<th>地点</th>
					<th>人数</th>
					<th>薪资待遇</th>
					<th>是否启用</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr>
				<tr>
					<c:forEach items="${jobs}" var="u">
						<tr>
							<th>${u.id}</th>
							<th>${u.title}</th>
							<th>${u.address}</th>
							<th>${u.jobnum}</th>
							<th>${u.treatment}</th>
							<th>
								<c:if test="${u.isenabled}" var="s">
									<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
								</c:if>
								<c:if test="${!s}">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
								</c:if>
							</th>
							
							<th>${u.inputdate}</th>
						<th>
							<a href="jobs_edit.html" class="btn-default tableA"><span class="glyphicon glyphicon-pencil" aria-hidden="true">修改</span></a>
							<a href="system/jobs/delete?id=${u.id}" class="btn-default tableA"><span class="glyphicon glyphicon-trash" aria-hidden="true">删除</span></a>
						</th>
						</tr>
					</c:forEach>
				</tr>
			</table>
			<!--分页-->
			<nav class="navbar-right">
				<ul class="pagination" id="paging">
					<li>
						<span>当前第1页</span>
					</li>
					<li>
						<a href="#">
							<span aria-hidden="true">首页</span>
						</a>
					</li>
					<li>
						<a href="#" aria-label="上一页">
							<span aria-hidden="true">上一页</span>
						</a>
					</li>
					<li>

					</li>
					<li>
						<a href="#" aria-label="下一页">
							<span aria-hidden="true">下一页</span>
						</a>
					</li>
					<li>
						<a href="#" aria-label="尾页">
							<span aria-hidden="true">尾页</span>
						</a>
					</li>
					<li>
						<span>总页数：共10页</span>
						<span>总数据：共50条</span>
					</li>
				</ul>
			</nav>
		</div>
	</body>

</html>