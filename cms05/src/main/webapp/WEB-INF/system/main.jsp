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
				<li class="active">轮播管理&emsp;<a href="system/imgs/addPage?totalPage=${pageBean.totalPage}" class="btn-default tableA"><span class="glyphicon glyphicon-plus" aria-hidden="true">添加轮播图</span></a></li>
			</ul>
		</div>
		<!--职位列表-->
		<div class="container job-table">
			<table class="table table-hover">
				<tr>
					<th>编号</th>
					<th>存储地址</th>
					<th>简述</th>
					<th>是否启用</th>
					<th>发布时间</th>
					<th>操作</th>
				</tr>
				<tr>
					<c:forEach items="${pageBean.list}" var="i">
						<tr>
						<th>&nbsp;&nbsp;#${i.imgid}&nbsp;&nbsp;</th>
						<th>${i.storepath}<span>\</span>${i.storename}</th>
						<th>${i.intro}</th>
						<th>
							<c:if test="${i.isenabled}" var="s">
								&emsp;&emsp;<span class="glyphicon glyphicon-ok" aria-hidden="true">
								</span>&emsp;&emsp;&emsp;
							</c:if>
							<c:if test="${!s}">
								&emsp;&emsp;<span class="glyphicon glyphicon-remove" aria-hidden="true">
								</span>&emsp;&emsp;&emsp;
							</c:if>
						<th>[${i.inputdate}]</th>
						
						<th>
							<a href="system/imgs/updatePage?imgid=${i.imgid}&localPage=${pageBean.localPage}" class="btn-default tableA"><span class="glyphicon glyphicon-plus" aria-hidden="true">修改</span></a>
							<a href="system/imgs/delete?imgid=${i.imgid}&localPage=${pageBean.localPage}&storename=${i.storename}" class="btn-default tableA"><span class="glyphicon glyphicon-pencil" aria-hidden="true">删除</span></a>
						</th>
						</tr>
					</c:forEach>
				</tr>
			</table>
			<!--分页-->
			<nav class="navbar-left">
				<ul class="pagination" id="paging">
					<li>
						<span>当前第${pageBean.localPage}页</span>
					</li>
					<li>
						<a href="system/imgs/list?localPage=1">
							<span aria-hidden="true">首页</span>
						</a>
					</li>
					<li>
						<a href="system/imgs/list?localPage=${pageBean.prePage}" aria-label="上一页">
							<span aria-hidden="true">上一页</span>
						</a>
					</li>
					<li>

					</li>
					<li>
						<a href="system/imgs/list?localPage=${pageBean.nextPage}" aria-label="下一页">
							<span aria-hidden="true">下一页</span>
						</a>
					</li>
					<li>
						<a href="system/imgs/list?localPage=${pageBean.lastPage }" aria-label="尾页">
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
		<div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
			<a class="btn btn-large" data-edit="bold"><i class="icon-bold"></i></a>
		</div>
	</body>
	<script>
		function add() {
			window.location.href = "http://www.baidu.com";
		}
	</script>

</html>