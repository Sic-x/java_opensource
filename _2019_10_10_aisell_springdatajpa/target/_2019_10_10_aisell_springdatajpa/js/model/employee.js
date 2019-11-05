//所有 employee 的 js 代码抽取到 employee.js
//入口函数：读取所有页面元素再执行
$(function () {
    //频繁使用id edatagrid将其提取为edatagrid 对象
    var edatagrid = $("#edatagrid");
    var searchForm = $("#searchForm");
    var editDialog = $("#editDialog"); //添加弹出框
    var editForm = $("#editForm"); //添加表单
    var ecombox =$("#ecombox");

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
    //验证方法重载
    $.extend($.fn.validatebox.defaults.rules,{
        age:{
            validator : function(value){
                return /^[0-9]+$/.test(value);
                },
            message : "只能填写数字"
        },
    });
})





    //准备了相应的方法功能
    itsource = {
        //es6写法 es5为: add:function()
        //添加方法
        add(){
            $('*[data-update]').show();
            $("*[data-update] input").validatebox("enable");
            //打开面板前把里面的数据清除
            editForm.form("clear");
            //把添加框(editDialog)打开
            editDialog.dialog("center").dialog("open");
        },
        //更新方法
        update() {
            $('*[data-update]').hide();
            $("*[data-update] input").validatebox("disable");
            // 判断是否选中:
            var row = edatagrid.datagrid("getSelected");
            //选中的情况
            if(row){
                //弹出对话框
                //alert会阻塞进程，不利于ajax异步
                $.messager.confirm('确认对话框', '确定要修改吗?', function(r){
                    //对话框选择是的情况
                    if (r){
                        //打开面板前把里面的数据清除
                        editForm.form("clear");

                        if(row.department){
                            //ecombox.combobox("select",row.department.id);
                            row["department.id"] = row.department.id;
                        }
                        editForm.form("load",row);
                        //把添加框(editDialog)打开
                        editDialog.dialog("center").dialog("open");


                    }else{
                        // 取消修改,如有修改后功能再添加
                    }
                });

            }
            else {
                //不使用原始的alert 太丑了,不同浏览器有区别
                $.messager.show({
                    title:'提示',
                    msg:'请先选择,再修改!!',
                    showType:'slide',
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    },
                    draggable:false,
                    resizable:false,
                    modal:true,
                });
            };
        },
        //保存功能(调用后台保存)
        save(){
            /**
             * 1.把表单数据传到后台
             * 2.如果后台保存成功,返回{success:true},刷新页面
             *      失败:返回{success:false,msg:xxx}
             * 3.关闭弹出框
             */
            /**
             * 1.把表单数据传到后台
             */
            var url = "/employee/save";
            var employeeId = $("#employeeId").val();
            if(employeeId){
                url = "/employee/update?cmd=update";
            }


            editForm.form('submit', {

                url:url, //提交的路径
                //提交前做点什么 如果返回false,它就不提交数据
                onSubmit: function(){

                    // do some check
                    // return false to prevent submit;
                    return $(this).form('validate');
                },
                //data是一个json字符串
                success:function(data){
                    // {"success":true}
                    //把一个Json字符串转成JSON对象
                    //eval("("+data+")")
                    console.debug(data);
                    var result = JSON.parse(data);
                    console.debug(result);
                    if(result.success){
                        if(result.add){
                            var options = edatagrid.datagrid('getPager').data("pagination").options;
                            var total = options.total;
                            var max = Math.ceil(total/options.pageSize);
                            edatagrid.datagrid('gotoPage', max);
                        }
                        else{
                            //成功更新就刷新页面
                            edatagrid.datagrid("reload");
                        }

                    }else{
                        $.messager.show('信息',`添加失败,原因是:${result.msg}`,"info");
                    }
                    //关闭面板
                    itsource.close();
                }
            });
        },
        //删除方法
        delete() {
            // 判断是否选中:
            var row = edatagrid.datagrid("getSelected");
            //选中的情况
            if(row){
                //弹出对话框
                //alert会阻塞进程，不利于ajax异步
                $.messager.confirm('确认对话框', '确定要删除吗?', function(r){
                    //对话框选择是的情况
                    if (r){
                        // 删除 向后台发送请求绑定id值 并Ajax异步获取后台返回信息
                        $.post("/employee/delete",{id:row.id},function (result) {
                            //判断返回信息
                            //if(msg == 'success'){
                            if(result.success){

                                $.messager.show({
                                    title:'提示',
                                    msg:'删除完成!',
                                    showType:'slide',
                                    style:{
                                        right:'',
                                        top:document.body.scrollTop+document.documentElement.scrollTop,
                                        bottom:''
                                    },
                                    draggable:false,
                                    resizable:false,
                                    modal:true,
                                });
                                //删除成功刷新datagrid
                                edatagrid.datagrid('reload');
                            }
                            //else if(msg == 'failure'){
                            else{
                                $.messager.alert('提示','删除失败!原因是:'+result.msg,'info');
                            }
                        });

                    }else{
                        // 取消删除,如有删除后功能再添加
                    }
                });

            }
            else {
                //不使用原始的alert 太丑了,不同浏览器有区别
                $.messager.show({
                    title:'提示',
                    msg:'请先选择,再删除!',
                    showType:'slide',
                    style:{
                        right:'',
                        top:document.body.scrollTop+document.documentElement.scrollTop,
                        bottom:''
                    },
                    draggable:false,
                    resizable:false,
                    modal:true,
                });

            };
        },
        reset(){
            editForm.form('reset');
        },
        search() {
            var params = searchForm.serializeObject();
            console.debug(params);
            edatagrid.datagrid('load',params);
        },
        //关闭面板
        close(){
            editDialog.dialog("close");
        },
    };
})

/**
 *
 * @param value :当前的数据
 * @param row : 当前行的数据
 * @param index :索引
 * @returns {string}
 */
function imageFormat(value, row, index){
    // 展示图片
    return `<img src= '${value}' alt="没有头像" style="width: 60px;height:60px"/>`;

};

function deptFormat(value){

    return value?value.name:"没有部门";

};