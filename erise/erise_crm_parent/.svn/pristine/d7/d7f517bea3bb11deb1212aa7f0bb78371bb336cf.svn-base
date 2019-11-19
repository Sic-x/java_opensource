package com.xmh.crm.web.controller.setted;


import com.xmh.crm.domain.Department;
import com.xmh.crm.domain.setted.Setted;
import com.xmh.crm.query.DepartmentQuery;
import com.xmh.crm.query.setted.SettedQuery;
import com.xmh.crm.service.parts.IPartsService;
import com.xmh.crm.service.repairOrder.IRepairOrderService;
import com.xmh.crm.service.setted.ISettedService;
import com.xmh.util.AjaxResult;
import com.xmh.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/setted")
@CrossOrigin
public class SettedController {
    @Autowired
    private ISettedService settedService;
    @Autowired
    private IPartsService partsService;
    @Autowired
    private IRepairOrderService orderService;
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
    @RequestMapping(value ="/findAll",method = RequestMethod.PATCH)
    @ResponseBody
    public List<Setted> findAll(){
        return settedService.findAll();
    }
    /**
     * 分页方法
     * @param settedQuery
     * @return
     */
    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin
    public PageList<Setted> query(@RequestBody SettedQuery settedQuery) {
        PageList<Setted> query = settedService.query(settedQuery);
        return query;
    }
    //  get departement/12 查询一条
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Setted findOne(@PathVariable("id") Long id){
        return settedService.findOne(id);
    }
    //修改  {"id":1,"name":"xxxx"}
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult update(@RequestBody Setted setted){
        settedService.updateNameById(setted);
        settedService.updateNameAndPayName(setted);
        settedService.updateRepairOrderItemService(setted);
        return new AjaxResult();
    }
    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id){
        settedService.removeOrderAndOitem(id);
        return new AjaxResult();
    }
    //批量删除
    @RequestMapping(value = "/batchRemove",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult batchRemove( String ids){
        Arrays.asList(ids.split(",")).forEach(e->settedService.remove(new Long(e)));
        return new AjaxResult();
    }
    //结算
    @RequestMapping(value = "/updatestatus",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updatestatus(@RequestBody Setted setted){
        orderService.updatestatus(setted.getRepairOrder().getId());
        return new AjaxResult();
    }
    //批量结算
    @RequestMapping(value = "/plupdatestatus",method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult plupdatestatus( String ids){
        Arrays.asList(ids.split(",")).forEach(e->orderService.updatestatus(new Long(e)));
        return new AjaxResult();
    }
}
