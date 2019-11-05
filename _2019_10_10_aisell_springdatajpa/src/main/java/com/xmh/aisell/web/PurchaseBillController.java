package com.xmh.aisell.web;

import com.xmh.aisell.common.JsonResult;
import com.xmh.aisell.common.UIPage;
import com.xmh.aisell.common.UserContext;
import com.xmh.aisell.domain.Employee;
import com.xmh.aisell.domain.PurchaseBill;
import com.xmh.aisell.domain.PurchaseBillItem;
import com.xmh.aisell.query.PurchaseBillQuery;
import com.xmh.aisell.service.IPurchaseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/purchaseBill")
public class PurchaseBillController extends BaseController {

    @Autowired
    private IPurchaseBillService purchaseBillService;

    @RequestMapping("/index")
    public String index(){
        return "purchasebill/index";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<PurchaseBill> findAll(){
        return purchaseBillService.findAll();
    }

    /**
     * 准备分页方法
     * @param query
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage page(PurchaseBillQuery query){
        //return purchaseBillService.queryPage(query);
        return new UIPage(purchaseBillService.queryPage(query));
    }

    /**
     * @ModelAttribute:只要你通过前台调用我的方法，我就要执行这个方法中的内容
     *
     */
    @ModelAttribute("editPurchaseBill")
    public PurchaseBill beforeUpdate(Long id, String cmd){
        //我想要的是，只有修改执行相应功能
        if(id!=null && "update".equals(cmd)) {
            //根据id拿到相应的对象
            PurchaseBill purchaseBill = purchaseBillService.findOne(id);
            //把关连对应的值设置为空,就可以解决n-to-n的问题
            purchaseBill.getItems().clear();
            purchaseBill.setSupplier(null);
            purchaseBill.setBuyer(null);
            return purchaseBill;
        }
        return null;
    }

    /**
     * 前台会传相应的数据(PurchaseBill)过来
     *  需要返回：JsonResult{success:true/false,msg:"xx"}
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(PurchaseBill purchaseBill){
        Employee user = UserContext.getUser();
        purchaseBill.setInputUser(user);
        return this.saveOrUpdate(purchaseBill);
    }

    //修改方法
    /**
     *我们的这个purchaseBill对象中从 beforeUpdate中获取的
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editPurchaseBill") PurchaseBill purchaseBill){
        return this.saveOrUpdate(purchaseBill);
    }

    //添加或者修改
    //一方:采购订单  多方:明细
    public JsonResult saveOrUpdate(PurchaseBill purchaseBill){

        //一.准备总金额与总数量
        BigDecimal totalAmount = new BigDecimal(0);
        BigDecimal totalNum = new BigDecimal(0);
        try {
            List<PurchaseBillItem> items = purchaseBill.getItems();

            for (PurchaseBillItem item : items) {
                item.setBill(purchaseBill);
                //计算小计(价格*数量)
                BigDecimal amount =item.getPrice().multiply(item.getNum());
                item.setAmount(amount);
                //二.计算总金额与总数量（注意:不改变原来的值）
                totalAmount = totalAmount.add(amount);
                totalNum = totalNum.add(item.getNum());
            }
                //三.把金额与数量添加进去
            purchaseBill.setTotalAmount(totalAmount);
            purchaseBill.setTotalNum(totalNum);
            purchaseBillService.save(purchaseBill);
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
            purchaseBillService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            //失败后返回的结果
            return new JsonResult(false,e.getMessage());
        }
    }


}

