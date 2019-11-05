<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/10/16
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/WEB-INF/views/head.jsp"%>
    <script>

        //判断当前页面是否是顶层页面
        if(top != window){
            //window.top.location.href = "/login";
            window.top.location.href = window.location.href;
        }

        function submitForm() {
            //直接提交
            // 对咱们来说，这是一个Ajax请求
            $('#loginForm').form('submit', {
                url:"/login",
                onSubmit: function(){
                    return $(this).form('validate');
                },
                //访问成功后的功能
                success:function(data){
                    //{"success":false,"msg":"用户名错误！"}
                    //Object { success: false, msg: "用户名或者密码错误！" }
                    var result = JSON.parse(data);
                    if(result.success){
                        //访问成功 -> 跳到主页面(Js怎么跳转)
                        // location:有很多当前页面的位置信息
                        // JS的BOM【浏览器对象模型】部分
                        window.location.href ="/menuIndex";
                    }else{
                        //访问失败 -> 给出提示
                        $.messager.alert('错误',result.msg);
                    }
                }
            });
        };

        //添加回车事件
        $(function() {
            $(document).keydown(function (event) {
                if (event.keyCode == 13) {
                    submitForm();
                }
            })
        })
    </script>
</head>
<body>
    <div align="center" style="margin-top: 100px;">
        <div class="easyui-panel" title="智销系统用户登陆" style="width: 350px; height: 240px;">
            <form id="loginForm" class="easyui-form" method="post">
                <table align="center" style="margin-top: 15px;">
                    <tr height="20">
                        <td>用户名:</td>
                    </tr>
                    <tr height="10">
                        <td><input name="username" class="easyui-validatebox" required="true" value="" /></td>
                    </tr>
                    <tr height="20">
                        <td>密&emsp;码:</td>
                    </tr>
                    <tr height="10">
                        <td><input name="password" type="password" class="easyui-validatebox" required="true" value="" /></td>
                    </tr>
                    <tr height="40">
                        <td align="center"><a href="javascript:;" class="easyui-linkbutton" onclick="submitForm();">登陆</a> <a
                                href="javascript:;" class="easyui-linkbutton" onclick="resetForm();">重置</a></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</form>
</body>
</html>
