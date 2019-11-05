package com.xmh.aisell.web;

import com.xmh.aisell.common.JsonResult;
import com.xmh.aisell.common.UIPage;
import com.xmh.aisell.domain.Menu;
import com.xmh.aisell.query.MenuQuery;
import com.xmh.aisell.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController{

    @Autowired
    private IMenuService menuService;

    @RequestMapping("/index")
    public String index(){
        return "menu/index";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Menu> findAll(){
        return menuService.findAll();
    }

    /**
     * 准备分页方法
     * @param query
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage page(MenuQuery query){
        //return menuService.queryPage(query);
        return new UIPage(menuService.queryPage(query));
    }

    /**
     * @ModelAttribute:只要你通过前台调用我的方法，我就要执行这个方法中的内容
     *
     */
    @ModelAttribute("editMenu")
    public Menu beforeUpdate(Long id,String cmd){
        //我想要的是，只有修改执行相应功能
        if(id!=null && "update".equals(cmd)) {
            //根据id拿到相应的对象
            Menu menu = menuService.findOne(id);
            //把关连对应的值设置为空,就可以解决n-to-n的问题
            return menu;
        }
        return null;
    }

    /**
     * 前台会传相应的数据(Menu)过来
     *  需要返回：JsonResult{success:true/false,msg:"xx"}
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Menu menu){
        return this.saveOrUpdate(menu);
    }

    //修改方法
    /**
     *我们的这个menu对象中从 beforeUpdate中获取的
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editMenu") Menu menu){
        return this.saveOrUpdate(menu);
    }

    //添加或者修改
    public JsonResult saveOrUpdate(Menu menu){
        try {
            menuService.save(menu);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }

    /**
     * 前台会传一个id过来
     *  需要返回：{success:true/false,msg:"xx"}
     *     返回这种结构有两种方法:1种是写map, 1种是直接返回对象
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        try {
            menuService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            //失败后返回的结果
            return new JsonResult(false,e.getMessage());
        }
    }



    /**
     * 返回所有的父菜单
     * @return
     */
    @RequestMapping("/findParentMenus")
    @ResponseBody
    public List<Menu> findParentMenus() {
        return menuService.findByUser();
    }

}