<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>XXX管理系统</title>
    <link href="css/dmaku.css" rel="stylesheet"/>
    <script>

        //判断当前页面是否是顶层页面
        if(top != window){
            //window.top.location.href = "/login";
            window.top.location.href = window.location.href;
        }
    </script>

</head>
<body>
<div class="htmleaf-container">
    <div id="wrapper" class="login-page">
        <div id="login_form" class="form">
            <form class="register-form">
                <input type="text" placeholder="用户名" name="username" id="r_user_name"/>
                <input type="password" placeholder="密码" name="password" id="r_password" />
                <button id="create">创建账户</button>
                <p class="message">已经有了一个账户? <a href="#">立刻登录</a></p>
            </form>
            <form class="login-form" id="loginform">
                <input type="text" placeholder="用户名" name="username" id="user_name"/>
                <input type="password" placeholder="密码" name="password" id="password"/>
                <button id="login">登　录</button>
                <p class="message">还没有账户? <a href="#">立刻创建</a></p>
            </form>
        </div>
    </div>
</div>

<script src="js/jquery-2.1.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
    function check_login()
    {
        $.ajax({
            type:"POST",
            url:"/login",
            data:$("#loginform").serialize(),
            success: function(data){
                console.debug(data);
                if(!data.success){

                    $("#login_form").removeClass('shake_effect');
                    setTimeout(function()
                    {
                        $("#login_form").addClass('shake_effect')
                    },1);
                }else {
                    //访问成功 -> 跳到主页面(Js怎么跳转)
                    // location:有很多当前页面的位置信息
                    // JS的BOM【浏览器对象模型】部分
                    window.location.href ="/menuIndex";
                }
            }
        });

        //添加回车事件
        $(function() {
            $(document).keydown(function (event) {
                if (event.keyCode == 13) {
                    check_login();
                    check_register();
                }
            })
        })

       /* $("#login_form").removeClass('shake_effect');
            setTimeout(function()
            {
                $("#login_form").addClass('shake_effect')
            },1);*/

    }
    function check_register(){
        var name = $("#r_user_name").val();
        console.debug(name);
        var pass = $("#r_password").val();
        console.debug(pass);
        if(name!="" && pass!="")
        {
            alert("注册成功！");
        }
        else
        {
            $("#login_form").removeClass('shake_effect');
            setTimeout(function()
            {
                $("#login_form").addClass('shake_effect')
            },1);
        }
    }
    $(function(){
        $("#create").click(function(){
            check_register();
            return false;
        });
        $("#login").click(function(){
            console.debug("check_login")
            check_login();
            return false;
        });
        $('.message a').click(function () {
            $('form').animate({
                height: 'toggle',
                opacity: 'toggle'
            }, 'slow');
        });
    })
</script>


</body>
</html>