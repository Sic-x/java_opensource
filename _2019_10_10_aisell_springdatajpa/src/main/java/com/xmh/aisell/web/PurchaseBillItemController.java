package com.xmh.aisell.web;

import com.xmh.aisell.common.JsonResult;
import com.xmh.aisell.common.UIPage;
import com.xmh.aisell.domain.PurchaseBillItem;
import com.xmh.aisell.domain.Supplier;
import com.xmh.aisell.domain.pie.PurchaseBillItemPie;
import com.xmh.aisell.domain.vo.PurchaseBillItemVO;
import com.xmh.aisell.query.PurchaseBillItemQuery;
import com.xmh.aisell.service.IPurchaseBillItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/purchaseBillItem")
public class PurchaseBillItemController extends BaseController {

    @Autowired
    private IPurchaseBillItemService purchaseBillItemService;

    @RequestMapping("/index")
    public String index(){
        return "purchasebillitem/index";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<PurchaseBillItem> findAll(){
        return purchaseBillItemService.findAll();
    }

    /**
     * 准备分页方法
     * @param query
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage page(PurchaseBillItemQuery query){
        //return purchaseBillItemService.queryPage(query);
        return new UIPage(purchaseBillItemService.queryPage(query));
    }

    /**
     * @ModelAttribute:只要你通过前台调用我的方法，我就要执行这个方法中的内容
     *
     */
    @ModelAttribute("editPurchaseBillItem")
    public PurchaseBillItem beforeUpdate(Long id, String cmd){
        //我想要的是，只有修改执行相应功能
        if(id!=null && "update".equals(cmd)) {
            //根据id拿到相应的对象
            PurchaseBillItem purchaseBillItem = purchaseBillItemService.findOne(id);
            //把关连对应的值设置为空,就可以解决n-to-n的问题
            return purchaseBillItem;
        }
        return null;
    }

    /**
     * 前台会传相应的数据(PurchaseBillItem)过来
     *  需要返回：JsonResult{success:true/false,msg:"xx"}
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(PurchaseBillItem purchaseBillItem){
        return this.saveOrUpdate(purchaseBillItem);
    }

    //修改方法
    /**
     *我们的这个purchaseBillItem对象中从 beforeUpdate中获取的
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editPurchaseBillItem") PurchaseBillItem purchaseBillItem){
        return this.saveOrUpdate(purchaseBillItem);
    }

    //添加或者修改
    public JsonResult saveOrUpdate(PurchaseBillItem purchaseBillItem){
        try {
            purchaseBillItemService.save(purchaseBillItem);
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
            purchaseBillItemService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            //失败后返回的结果
            return new JsonResult(false,e.getMessage());
        }
    }

    @RequestMapping("/findVos")
    @ResponseBody
    public List<PurchaseBillItemVO> findVos(PurchaseBillItemQuery query){
        return purchaseBillItemService.findVos(query);
    }

    @RequestMapping("/pie")
    @ResponseBody
    public List<PurchaseBillItemPie> pie() {
        List<PurchaseBillItemPie> pieList = new ArrayList<>();
        List<String> list = purchaseBillItemService.findSupplier();
        list.forEach(e->{
            PurchaseBillItemPie pie = new PurchaseBillItemPie();
            pie.setName(e);
            pie.setAmount(purchaseBillItemService.findAmountBySupplier(e));
            pieList.add(pie);
        });
        return pieList;
    }
}

