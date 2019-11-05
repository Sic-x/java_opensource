

/*
*   用于刷新树菜单
* */
function refreshTree() {
    $("#mtree").tree('reload');
}

$(function(){
    var etab = $("#etabs");
    // 树形菜单
    console.debug($("#mtree"));
    $("#mtree").tree({
        url:'/menu/findParentMenus',
        //url:'/treedata.json',
        animate:true,
        method:'get',
        onClick:function(node){
            var tabName = node.text;
            // 是否是子节点
            //if(!node.children.length){
            if(node.url){
                // 添加一个新的选项卡
                // 先判断  exists
                if(etab.tabs("exists",tabName)){
                    // 存在 -- 切换 select
                    etab.tabs("select",tabName);
                }else{
                    // 不存在 -- 创建 add
                    $("#etabs").tabs("add",{
                        'title':node.text,
                        closable:true,
                        content:'<iframe src="'+ node.url+'" frameborder="0" width="100%" height="100%"></iframe>'
                    });
                    $("#etabs").tabs("enableDnd");
                }
            }else{
                var s_node = $('#mtree').tree('getSelected');
                if(s_node.state=="open"){
                    $('#mtree').tree('collapse', s_node.target);
                }else{
                    $('#mtree').tree('expand', s_node.target);
                }
            }
        },
    })


    //绑定右键菜单事件
    $(".easyui-tabs").bind('contextmenu',function(e){
        e.preventDefault();
        $('#rcmenu').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
    });
    //关闭所有标签页
    $("#closeAll").bind("click",function(){
        var tablist =$('#etabs').tabs('tabs');
        console.log(tablist);
        //  return;
        for(var i=tablist.length-1;i>=1;i--){
            $('#etabs').tabs('close',i);
        }
    });
    //关闭其他页面（先关闭右侧，再关闭左侧）
    $("#closeOther").bind("click",function(){
        var tablist = $('#etabs').tabs('tabs');
        var tab = $('#etabs').tabs('getSelected');
        var index = $('#etabs').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#etabs').tabs('close',i);
        }
        var num = index-1;
        if(num < 1){
            return;
        }else{
            for(var i=num;i>=1;i--){
                $('#etabs').tabs('close',i);
            }
            $("#etabs").tabs("select", 1);
        }
    });
    //关闭右边的选项卡
    $("#closeRight").bind("click",function(){
        var tablist = $('#etabs').tabs('tabs');
        var tab = $('#etabs').tabs('getSelected');
        var index = $('#etabs').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#etabs').tabs('close',i);
        }
    });
    //关闭右边的选项卡
    $("#closeLeft").bind("click",function(){
        var tablist = $('#etabs').tabs('tabs');
        var tab = $('#etabs').tabs('getSelected');
        var index = $('#etabs').tabs('getTabIndex',tab);
        var num = index-1;
        if(num < 1){
            return;
        }else{
            for(var i=num;i>=1;i--){
                $('#etabs').tabs('close',i);
            }
            $("#etabs").tabs("select", 1);
        }
    });
})
