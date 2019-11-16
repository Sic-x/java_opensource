package com.xmh.aisell.web;


import com.xmh.aisell.common.JsonResult;
import com.xmh.aisell.common.UIPage;
import com.xmh.aisell.domain.SystemDictionaryDetail;
import com.xmh.aisell.domain.SystemDictionaryType;
import com.xmh.aisell.query.SystemDictionaryDetailQuery;
import com.xmh.aisell.service.ISystemDictionaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 员工控制层
 */
@Controller
@RequestMapping("/systemDictionaryDetail")
public class SystemDictionaryDetailController {

    /**
     * 自动注入IBaseService实现对象
     */
    @Autowired
    private ISystemDictionaryDetailService service;




    @RequestMapping("/index")
    public String systemDictionaryDetailIndex(){
        return "systemDictionaryDetail/index";
    }


    @RequestMapping("/findBrand")
    @ResponseBody
    public List findBrand(){
        return service.findDetailsBySn(SystemDictionaryType.BRAND);
    }

    @RequestMapping("/findUnit")
    @ResponseBody
    public List findUnit(){
        return service.findDetailsBySn(SystemDictionaryType.UNIT);
    }



    /**
     * 请求url匹配
     * 返回json字符串
     * @return 查询所有员工数据
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public List FindAll(){
        return service.findAll();
    }

    /**
     * 准备分页方法
     * @param query 前台传入的query对象
     * //@param page  前台传过来的page字符串与query对象CurrentPage不同
     * @return      返回UIPage对象,由queryPage查询封装
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage<SystemDictionaryDetail> page(SystemDictionaryDetailQuery query){
        //String page
        //query.setCurrentPage(new Integer(page));
        //List content = service.queryPage(query).getContent();
        return new UIPage(service.queryPage(query));
    }

    /**
     * @param id 前台传入的id字符串
     *        调用service.delete
     * @return 返回相应的json字符串到前台
     * 返回结构 1.写map
     *         2.直接返回对象
     */

    /**
     * 前台会传相应的数据(SystemDictionaryDetail)过来
     *  需要返回：JsonResult{success:true/false,msg:"xx"}
     *  后面要做用户权限，需要把save和update分开，有些用户只有添加没有修改权限
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(SystemDictionaryDetail systemDictionaryDetail){
        return saveOrUpdate(systemDictionaryDetail);
    }


    /**
     * 更新前方法，前台发送请求/systemDictionaryDetail/update?cmd=update
     * 只匹配更新请求通过发送过来的id从数据库获取相对应的SystemDictionaryDetail持久化对象
     * （这里有个n to n的问题 事务期间关联持久化对象的创建造成脏数据更新报错）
     * @param id
     * @param cmd
     * @return
     */
    @ModelAttribute("editSystemDictionaryDetail")
    public SystemDictionaryDetail beforeUpdate(Long id,String cmd){
        if("update".equals(cmd)){
            SystemDictionaryDetail systemDictionaryDetail = service.findOne(id);
            //把关连对应的值设置为空,就可以解决n-to-n的问题
            systemDictionaryDetail.setSystemDictionaryType(null);
            return systemDictionaryDetail;
        }
        return null;
    }

    /**
     *
     * @param systemDictionaryDetail 传入参数systemDictionaryDetail现在是从beforeUpdate方法拿的
     *        前台依旧会传过来与数据库中持久化对象systemDictionaryDetail不同的修改数据
     *        spring会根据不同的修改，‘.’前get ‘.’后set
     * @return JsonResult
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editSystemDictionaryDetail")SystemDictionaryDetail systemDictionaryDetail){
        return saveOrUpdate(systemDictionaryDetail);
    }

    /**
     * @param systemDictionaryDetail
     *  执行service.save(systemDictionaryDetail);方法
     *
     * @return 成功返回 new JsonResult();
     *         失败返回 new JsonResult(false,e.getMessage());
     */
    public JsonResult saveOrUpdate(SystemDictionaryDetail systemDictionaryDetail){
        try {
            JsonResult jsonResult = new JsonResult();
            //判断是添加还是修改
            if(systemDictionaryDetail.getId() == null){
                jsonResult.setAdd(true);
            }
            service.save(systemDictionaryDetail);
            return jsonResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,e.getMessage());
        }
    }


    /**
     * 删除方法
     * @param id 根据 SystemDictionaryDetail id 删除员工
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public JsonResult delete(Long id){
        try {
            service.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            // return "failure";
            return new JsonResult(false,e.getMessage());
        }
        // return "success";
    }


}
