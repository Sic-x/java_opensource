//入口函数：页面读取所有元素再执行
$(function () {

    //把常用的一些组件获取
    var  supplierDataGrid = $("#supplierDataGrid"); //页面显示表格
    var  searchForm = $("#searchForm"); //查询表单
    var  editDialog = $("#editDialog"); //添加弹出框
    var  editForm = $("#editForm"); //添加表单

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
        //添加(只是弹出form)
        add(){
            //密码显示并启用
            $("*[data-update]").show();
            $("*[data-update] input").validatebox("enable");
            //打开面板前把里面的数据清除
            editForm.form("clear");
            //把添加框(editDialog)打开
            editDialog.dialog("center").dialog("open");
        },
        //修改(只是弹出form)
        update () {
            //密码隐藏并禁用
            $("*[data-update]").hide();
            $("*[data-update] input").validatebox("disable");
            //1.拿到选中的那一条数据
            var row = supplierDataGrid.datagrid("getSelected");
            //2.如果没有选中，给出提示
            if(row==null){
                $.messager.alert('信息','请先选择,再更新',"info");
                return;
            }
            //3.清空表单并弹出数据
            editForm.form("clear");
            editDialog.dialog("center").dialog("open");
            //4.完成咱们的数据回显
            editForm.form("load",row);
        },
        //保存修改功能(调用后台保存)
        save(){
            //添加功能的路径
            var url ="/supplier/save";
            //拿到表单中的员工id,如果id存在，代表修改
            var supplierId = $("#supplierId").val();
            if(supplierId){
                url = "/supplier/update?cmd=update";
            }
            /**
             * 1.把表单数据传到后台
             */
            editForm.form('submit', {
                url:url, //提交的路径
                //提交前做点什么 如果返回false,它就不提交数据
                onSubmit: function(){
                    return $(this).form('validate');
                },
                //data是一个json字符串
                success:function(data){
                    // {"success":true}
                    //把一个Json字符串转成JSON对象
                    //eval("("+data+")")
                    var result = JSON.parse(data);
                    if(result.success){
                        //成功就刷新页面
                        supplierDataGrid.datagrid("reload");
                    }else{
                        $.messager.alert('信息',`添加失败,原因是:${result.msg}`,"info");
                    }
                    //关闭面板
                    itsource.close();
                }
            });
        },
        delete () {
            //1.拿到你选择的那一条数据 (easyui的datagrid中有这个功能)
            //  row就是你选择的这一行的数据
            var row = supplierDataGrid.datagrid("getSelected");
            //2.如果没有拿到数据,给一个提示，让他选中再执行(后面的代码就不运行) Messager alert
            if(row==null){
                //alert("没有货，滚蛋！"); //不建议使用 1.不同浏览器效果不一样 2.它会阻塞进程
                $.messager.alert('信息','请先选择,再删除',"info");
                return;
            }
            //3.如果拿到数据，给一个提示，是否要删除？ Messager confirm
            $.messager.confirm('信息','确定要删除吗?',function(r){
                if (r){
                    //4.如果要删除 -> 发送Ajax请求到后台进行数据删除 $.get/post("/supplier/delete",{id:2},function(){....})
                    //  后台会返回相应的数据 ：{success:true/false,msg:xxx}
                    //  如果成功，刷新datagrid
                    $.get("/supplier/delete",{id:row.id},function (result) {
                        if(result.success){
                            //删除成功，刷新datagrid
                            supplierDataGrid.datagrid("reload");
                        }else{
                            //删除失败，给出失败提示
                            $.messager.alert('信息','删除没有成功，原因是:'+result.msg,"info");
                        }
                    })
                }
            });
        },
        reset(){
            editForm.form('reset');
        },
        //高级查询
        search(){
            //JQuery没有提供拿到json格式的数据
            //直接拿到要查询的值  {username:xx,...}
            var params =searchForm.serializeObject();
            // //grid刷新
            supplierDataGrid.datagrid('load',params);
        },
        //关闭面板
        close(){
            editDialog.dialog("close");
        }
    };
})