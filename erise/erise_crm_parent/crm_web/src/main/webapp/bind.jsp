<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>绑定</title>
    <script src="/js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        //绑定方法 获取openid  怎么获取? "redirect:/bind.jsp?openid=adfasdfasdfdf
        function binder() {

            ///binder.jsp?openid=xxx
            var url =  window.location.href;
            //截取
            var openid = url.split("=")[1]; //xxx
            var params = {
                "username":$("#username").val(),
                "password":$("#password").val(),
                "openid": openid
            };
//415错误
            // $.post("/binder", params,
            //     function(data){
            //        console.log(data)
            //     }, "json");

            $.ajax({
                type: "post",
                dataType: "json",
                url: "/binder",
                contentType: "application/json;charset=UTF-8",//指定消息请求类型
                data: JSON.stringify(params),//将js对象转成json字符串
                success: function (data) {
                    //data =  $.parseJSON(data);
                    console.log(data);
                    if (data.success) {
                        location.href = "/index.jsp"
                    }else{
                        location.href = "/login"
                    }
                }
            })
        }
    </script>

</head>
<body>
<%--有两种场景:--%>
<%--1)如果允许注册,后台处理,通过用户名查询员工,如果有直接使用,否则注册一个新的.--%>
<%--2)如果不允许注册,后台处理,,通过用户名查询员工,如果有直接使用,否则报错员工不存在.--%>
<h3 style="color: red">为了以后无需登录请进行绑定,如果没有员工账号请求你们公司管理员!</h3>
<form method="post" action="#">
    用户名:<input type="text" name="username" id="username"><br/>
    密&emsp;码:<input type="text" name="password" id="password"><br/>
    <input type="button" value="绑定" onclick="binder()">
</form>

</body>
</html>
