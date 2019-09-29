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
				<li>
					<a href="jobs.html">职位管理</a>
				</li>
				<li class="active">发布职位</li>
			</ul>
		</div>
		<!--添加-->
		<form action="system/jobs/update" method="post">
			<input type="hidden" name="id" value="${newJobs.id}">
			<div class="form-group">
				<label for="title">工作职位</label>
				<input type="text" class="form-control" id="title" placeholder="工作职位" name="title" value="${newJobs.title}">
			</div>
			<div class="form-group">
				<label for="address">工作地点</label>
				<select id="city" class="form-control" name="cid">
					<c:forEach items="${city}" var="c">
						<option value="${c.id}">${c.cname}</option>
					</c:forEach>
					
				</select>
			</div>
			<div class="form-group">
				<label for="jobnum">招聘人数</label>
				<input type="number" class="form-control" id="jobnum" placeholder="招聘人数" name="jobnum" value="${newJobs.jobnum}"/>
			</div>
			<div class="form-group">
				<label for="treatment">薪资待遇</label>
				<input type="text" class="form-control" id="treatment" placeholder="薪资待遇" name="treatment" value="${newJobs.treatment}">
			</div>
			<div class="form-group">
				<label for="describe">职位描述</label>
				<div id="describe">
				</div>
				<input type="hidden" id="txtDescribe" name="describes" />
			</div>
			<div class="form-group">
				<label for="require">任职要求</label>
				<div id="require">
				</div>
				<input type="hidden" id="txtRequire" name="requires" />
			</div>
			<div class="form-group">
				<label for="positiontype">职位类型:</label>
				<label class="radio-inline">
					<input type="radio" name="positiontype" id="positiontype" value="1" 
						<c:if test="${newJobs.positiontype==1}">checked="checked"</c:if>
					>全职
				</label>
				<label class="radio-inline">
					<input type="radio" name="positiontype" id="positiontype" value="0"
						<c:if test="${newJobs.positiontype==0}">checked="checked"</c:if>
					>兼职
				</label>
			</div>
			<div class="form-group">
				<label for="isenabled">是否启用:</label>
				<label class="radio-inline">
					<input type="radio" name="isenabled" id="isenabled" value="1" 
						<c:if test="${newJobs.isenabled==true}">checked="checked"</c:if>
					>是
				</label>
				<label class="radio-inline">
					<input type="radio" name="isenabled" id="isenabled" value="0"
						<c:if test="${newJobs.isenabled==false}">checked="checked"</c:if>
					>否
				</label>
			</div>
			<div class="btn-toolbar" data-role="editor-toolbar" data-target="#editor">
				<a class="btn btn-large" data-edit="bold"><i class="icon-bold"></i></a>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary">修改职位</button>
			</div>
		</form>
	</body>
	<script type="text/javascript" src="js/wangEditor.min.js"></script>
	<script type="text/javascript">
		//初始化富文本框
		var E = window.wangEditor;
		//职位描述
		var editor = new E('#describe');
		
		//职位描述
		var $txtDescribe = $('#txtDescribe');
		//职位描述中的信息同步到隐藏域
		editor.customConfig.onchange = function(html) {
			$txtDescribe.val(html);
		}
		editor.create();
		// 初始化 textarea 的值
		editor.txt.html('${newJobs.describes}');
		$txtDescribe.val(editor.txt.html());
		
		//任职要求
		var editor2 = new E('#require');
		//任职要求
		var $txtRequire = $('#txtRequire');
		//职位描述中的信息同步到隐藏域
		editor2.customConfig.onchange = function(html) {
			$txtRequire.val(html);
		}
		editor2.create();
		// 初始化 textarea 的值
		editor2.txt.html('${newJobs.requires}');
		$txtRequire.val(editor2.txt.html());
			
		document.getElementById("city").value='${newJobs.cid}';
	</script>

</html>