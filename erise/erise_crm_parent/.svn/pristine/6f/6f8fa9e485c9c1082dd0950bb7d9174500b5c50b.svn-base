package com.xmh.crm.web.controller.systemlog;



import com.xmh.crm.domain.systemlog.Systemlog;
import com.xmh.crm.service.systemlog.ISystemlogService;
import com.xmh.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/systemlog")
@CrossOrigin
public class SystemlogController {

    @Autowired
    private ISystemlogService systemlogService;

    /**
     *  restful 风格 -- http的风格（get/post）
     *
     *   put -- 新增
     *   post -- 修改
     *   delete -- 删除
     *   get --查询
     *   patch --查询
     *
     *  (1)查询所有
     */

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public List<Systemlog> findAll(){
        return systemlogService.findAll();
    }

    //  get departement/12 查询一条
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Systemlog findOne(@PathVariable("id") Long id){
        return systemlogService.findOne(id);
    }

    //新增  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult saveOrUpdate(@RequestBody  Systemlog systemlog){
        try {
            if (systemlog.getId() != null){
                systemlogService.update(systemlog);
            }else{
                systemlogService.save(systemlog);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败!"+e.getMessage());
        }
    }

  /*  //修改  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestBody Systemlog systemlog){
        systemlogService.update(systemlog);
        return new AjaxResult();
    }*/

    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id){
        systemlogService.remove(id);
        return new AjaxResult();
    }
}
