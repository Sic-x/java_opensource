<html>
	<head>
		<meta charset="UTF-8">
		<title>
			第二个模板
		</title>
	</head>
	<body>
		城市名称:${cname}
		城市级别:
		<#if id lt 10>
			一线城市
		<#elseif id lte 20> <!-- >gt <lt <=lte >=gte -->
			二线城市
		<#else>
			普通城市
		</#if>
	</body>
</html>