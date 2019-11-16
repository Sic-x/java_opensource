//入口函数：页面读取所有元素再执行
$(function () {

    //把常用的一些组件获取
    var  systemdictionarytypeDataGrid = $("#systemdictionarytypeDataGrid"); //页面显示表格
    var  searchForm = $("#searchForm"); //查询表单

   //只要有data-method属性的元素我都要为它注册事件
    $("*[data-method]").on("click",function () {
        //谁调用，this就指向谁(这个this是普通dom对象)
        //$(dom对象) -> 变成jQuery对象，有很多jQuery特有的功能(更加强大)
        //console.debug($(this));
        //1.拿到当前按键的属性 add,update
        //var methodName = $(this).attr("data-method");
        //var methodName = $(this).data("method");
        //2.执行对应的方法(动态调用)
        //  itsource.say() == itsource["say"]();
        itsource[$(this).data("method")]();
    })

    //准备了相应的方法功能
    //js ES5,ES6
    itsource = {

        //高级查询
        search(){
            //JQuery没有提供拿到json格式的数据
            //直接拿到要查询的值  {username:xx,...}
            var params =searchForm.serializeObject();
            // //grid刷新
            systemdictionarytypeDataGrid.datagrid('load',params);
        },
    };
})
