package com.xmh.crm.web.controller.repairOrderItem;


import com.xmh.crm.domain.repairOrderItem.RepairOrderItem;
import com.xmh.crm.service.repairOrderItem.IRepairOrderItemService;
import com.xmh.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/repairOrderItem")
@CrossOrigin
public class RepairOrderItemController {

    @Autowired
    private IRepairOrderItemService repairOrderItemService;

    /**
     *  restful 风格 -- http的风格（get/post）
     *   put -- 新增
     *   post -- 修改
     *   delete -- 删除
     *   get --查询
     *   patch --查询
     *  (1)查询所有
     */

    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public List<RepairOrderItem> findAll(){
        return repairOrderItemService.findAll();
    }
    //  get departement/12 查询一条
    @RequestMapping(value = "/findOne/{id}",method = RequestMethod.GET)
    @ResponseBody
    public RepairOrderItem findOne1(@PathVariable("id") Long id){
        return repairOrderItemService.findOne1(id);
    }


    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public RepairOrderItem findOne(@PathVariable("id") Long id){
        return repairOrderItemService.findOne(id);
    }

    //新增  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult saveOrUpdate(@RequestBody  RepairOrderItem repairOrderItem){
        try {
            if (repairOrderItem.getId() != null){
                repairOrderItemService.update(repairOrderItem);
            }else{
                repairOrderItemService.save(repairOrderItem);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败!"+e.getMessage());
        }
    }

    /*//修改  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestBody RepairOrderItem repairOrderItem){
        repairOrderItemService.update(repairOrderItem);
        return new AjaxResult();
    }*/

    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id){
        repairOrderItemService.remove(id);
        return new AjaxResult();
    }
}
