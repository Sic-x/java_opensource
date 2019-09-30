<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="">
		<input/>
		<input/>
		<input type="submit"/>
	</form>
	<input type="button" onclick="getRandomData()" value="获取随机数"/>
	<script type="text/javascript">
		function getXhr() {
			var xhr = null;
			if(window.XMLHttpRequest){	//高版本的ie和其他浏览器
				xhr = new XMLHttpRequest();
			}
			else{	//低版本的ie浏览器
				xhr = new ActivateXObject('Microsoft.XMLHTTP');
			}
			return xhr;
		}
		alert(getXhr());
		
		/* function getXhr() {
			var xhr = getXhr();
			xhr.open("get","ajaxGet?name=tom");
			xhr.send(null);
			xhr.onreadystatechange = function(){
			if(xhr.readyState==4 && xhr.status==200)
				alert(xhr.responseText);
			}
		}
		 */
		function getRandomData() {
			var xhr = getXhr();	//获取ajax对象
			var name = "tom";
			xhr.open("post","ajaxPost");	//准备请求	
			//如果有数据,需要设置请求头
			xhr.setRequestHeader("content-type","application/x-www-form-urlencoded")
			xhr.send("username="+name);//发送请求
			xhr.onreadystatechange = function(){//设置回调函数
				if(xhr.readyState==4 && xhr.status==200)
					alert(xhr.responseText);//接收请求参数
			}
		}
		
		
	</script>
</body>
</html>