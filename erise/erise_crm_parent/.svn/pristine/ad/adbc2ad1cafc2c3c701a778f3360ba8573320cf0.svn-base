package com.xmh.crm.web.controller.repairOrder;



import com.xmh.crm.domain.parts.Parts;
import com.xmh.crm.domain.repairAll.RepairAll;
import com.xmh.crm.domain.repairOrder.RepairOrder;
import com.xmh.crm.domain.repairOrderItem.RepairOrderItem;
import com.xmh.crm.domain.setted.Setted;
import com.xmh.crm.query.repairOrder.RepairOrderQuery;
import com.xmh.crm.service.parts.IPartsService;
import com.xmh.crm.service.repairOrder.IRepairOrderService;
import com.xmh.crm.service.repairOrderItem.IRepairOrderItemService;
import com.xmh.crm.service.setted.ISettedService;
import com.xmh.util.AjaxResult;
import com.xmh.util.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/repairOrder")
@CrossOrigin
public class RepairOrderController {

    @Autowired
    private IRepairOrderService repairOrderService;
    @Autowired
    private IRepairOrderItemService repairOrderItemService;

    @Autowired
    private ISettedService settedService;

    @Autowired
    private IPartsService partsService;

    /**
     * restful 风格 -- http的风格（get/post）
     * <p>
     * put -- 新增
     * post -- 修改
     * delete -- 删除
     * get --查询
     * patch --查询
     * <p>
     * (1)查询所有
     */

    /*@RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    public List<RepairOrder> findAll(){
        return repairOrderService.findAll();
    }*/
    @RequestMapping(method = RequestMethod.PATCH)
    @ResponseBody
    @CrossOrigin
    public PageList<RepairOrder> query(@RequestBody RepairOrderQuery repairOrderQuery) {
        return repairOrderService.query(repairOrderQuery);
    }

    //  get departement/12 查询一条
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public RepairOrder findOne(@PathVariable("id") Long id) {
        return repairOrderService.findOne(id);
    }

    //新增  {"id":1,"name":"xxxx"}
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public AjaxResult saveOrUpdate(@RequestBody RepairAll repairAll) {
        RepairOrder repairOrder = null;
        RepairOrderItem repairOrderItem = null;
        try {
            if (repairAll.getId()!= null) {
                RepairOrderItem one = repairOrderItemService.findOne(repairAll.getId());
                RepairOrder repairOrder1 = one.getRepairOrder();
                Long id = repairOrder1.getId();
                repairOrder = RepairAll.getRepairOrder(repairAll);
                repairOrder.setId(id);
                repairOrderService.update(repairOrder);


                repairOrderItem = RepairAll.getRepairOrderItem(repairAll);
                repairOrderItem.setId(one.getId());
                repairOrderItem.setOpt(repairOrder.getOpt());
                repairOrderItem.setRepairOrder(repairOrder);
                if(repairAll.getAmt1()!=null){
                    repairOrderItem.setAmt1(repairAll.getAmt1());
                }else {
                    Parts one1 = partsService.findOne(repairOrderItem.getParts().getId());
                    repairOrderItem.setAmt1(one1.getPrice());
                }
                /*结算单*/
                List<Setted> setteds = settedService.findAll();
                for(Setted setted:setteds){
                    if(setted.getRepairOrder().getId().equals(repairOrderItem.getRepairOrder().getId())){
                        Setted one1 = settedService.findOne(setted.getId());
                        one1.setRe_amount(repairOrderItem.getTotalamt());
                        one1.setCustormer(repairAll.getCustormer());
                        settedService.update(one1);
                    }
                }
                repairOrderItemService.update(repairOrderItem);
            } else {
                repairOrder = RepairAll.getRepairOrder(repairAll);
                repairOrderItem = RepairAll.getRepairOrderItem(repairAll);
                repairOrderService.save1(repairOrder);
                Long id = repairOrder.getId();
                RepairOrder one = repairOrderService.findOne(id);
                /*结算单*/
                Setted setted = new Setted();
                setted.setCustormer(repairAll.getCustormer());
                setted.setRepairOrder(one);
                setted.setRe_amount(repairOrderItem.getTotalamt());
                settedService.save(setted);
                /*------------------*/
                repairOrderItem.setAmt1(repairOrderItem.getParts().getPrice());
                repairOrderItem.setRepairOrder(one);
                repairOrderItemService.save(repairOrderItem);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("操作失败!" + e.getMessage());
        }
    }



    //删除 {"id":1,"name":"xxxx"}
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id) {
        RepairOrder one = repairOrderService.findOne(id);
        List<RepairOrderItem> all = repairOrderItemService.findAll();
        for(RepairOrderItem repairOrderItem:all){
            if(repairOrderItem.getRepairOrder().getId().equals(one.getId())){
                repairOrderItemService.remove(repairOrderItem.getId());
            }
        }
        List<Setted> setteds = settedService.findAll();
        for(Setted setted:setteds){
            if(setted.getRepairOrder().getId().equals(one.getId())){
                settedService.remove(setted.getId());
            }
        }
        repairOrderService.remove(id);
        return new AjaxResult();
    }


    @RequestMapping(value = "/batchRemove",method = RequestMethod.DELETE)
    @ResponseBody
    public AjaxResult batchRemove(String id){
        List<String> ids = Arrays.asList(id.split(","));
        List<Long> ids2 = new ArrayList<>();
        for(String id1:ids){
            ids2.add(new Long(id1));
        }
        for(Long id3:ids2){
            RepairOrder one = repairOrderService.findOne(id3);
            List<RepairOrderItem> all = repairOrderItemService.findAll();
            for(RepairOrderItem repairOrderItem:all){
                if(repairOrderItem.getRepairOrder().getId().equals(one.getId())){
                    repairOrderItemService.remove(repairOrderItem.getId());
                }
            }
            List<Setted> setteds = settedService.findAll();
            for(Setted setted:setteds){
                if(setted.getRepairOrder().getId().equals(one.getId())){
                    settedService.remove(setted.getId());
                }
            }
            repairOrderService.remove(id3);
        }

        return new AjaxResult();
    }
}
