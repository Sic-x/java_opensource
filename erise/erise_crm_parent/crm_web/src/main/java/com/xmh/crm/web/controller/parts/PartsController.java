package com.xmh.crm.web.controller.parts;


import com.xmh.crm.domain.Department;
import com.xmh.crm.domain.parts.Parts;
import com.xmh.crm.query.DepartmentQuery;
import com.xmh.crm.query.parts.PartsQuery;
import com.xmh.crm.service.parts.IPartsService;
import com.xmh.util.AjaxResult;
import com.xmh.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/parts")
@CrossOrigin
public class PartsController {

    @Autowired
    private IPartsService partsService;

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
    @RequestMapping(value = "/findAll",method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin
    public List<Parts> findAll() {
        return partsService.findAll();
    }


    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin
    public PageList<Parts> query(@RequestBody PartsQuery partsQuery) {
        return partsService.query(partsQuery);
    }

    //  get departement/12 查询一条
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Parts findOne(@PathVariable("id") Long id){
        return partsService.findOne(id);
    }

    //新增  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult saveOrUpdate(@RequestBody  Parts parts){
        try {
            if (parts.getId() != null){
                partsService.update(parts);
            }else{
                partsService.save(parts);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败!"+e.getMessage());
        }
    }

    //修改  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestBody Parts parts){
        partsService.update(parts);
        return new AjaxResult();
    }

    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id){
        partsService.remove(id);
        return new AjaxResult();
    }

    @RequestMapping(value = "/batchRemove",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult batchRemove(String ids){
        Arrays.asList(ids.split(",")).forEach(e->
                partsService.remove(new Long(e))
                );
        return new AjaxResult();
    }

}
