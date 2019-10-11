<html>
	<head>
		<meta charset="UTF-8">
		<title>
			第二个模板
		</title>
	</head>
	<body> <!-- >gt <lt <=lte >=gte -->
		<#list list as e>
			<h2>
				城市名称:${e.cname}
				城市级别:
				<#if e.id lt 10>
					一线城市
				<#elseif e.id lte 20> 
					二线城市
				<#else>
					普通城市
				</#if>
			</h2>
		</#list>
	</body>
</html>