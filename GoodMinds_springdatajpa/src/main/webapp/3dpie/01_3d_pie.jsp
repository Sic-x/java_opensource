<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>3d饼图测试</title>
		<%@ include file="/WEB-INF/views/head.jsp" %>
		<style type="text/css">

		</style>
	</head>
	<body>

<script src="../js/Highcharts/code/highcharts.js"></script>
<script src="../js/Highcharts/code/highcharts-3d.js"></script>
<script src="../js/Highcharts/code/modules/exporting.js"></script>
<script src="../js/Highcharts/code/modules/export-data.js"></script>

<div id="container" style="height: 400px"></div>


		<script type="text/javascript">

				
			$(function () {

                var pieData = [];
                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: "/purchaseBillItem/pie",
                    dataType: "json",
					async:false,
                    success: function(data){
                        pieData = data;
                        console.debug(pieData);
                        console.debug(pieData[0].amount);

                    }
                });

			Highcharts.chart('container', {
				chart: {
					type: 'pie',
					options3d: {
						enabled: true,
						alpha: 45,
						beta: 0
					}
				},
				title: {
					text: '各仓库采购情况汇总'
				},
				plotOptions: {
					pie: {
						allowPointSelect: true,
						cursor: 'pointer',
						depth: 35,
						dataLabels: {
							enabled: true,
							format: '{point.name}'
						}
					}
				},
				series: [{
					type: 'pie',
					name: '采购商品总金额',
					data: [
						[pieData[1].name,pieData[1].amount],
						{
							name: pieData[0].name,
							y: pieData[0].amount,
							sliced: true,
							selected: true
						},
                        [pieData[2].name,pieData[2].amount],
                        [pieData[3].name,pieData[3].amount],
                        [pieData[4].name,pieData[4].amount],
					],
					tooltip:{
					    valueSuffix:'元',
					},
				}]
			});
            })
		</script>
	</body>
</html>
