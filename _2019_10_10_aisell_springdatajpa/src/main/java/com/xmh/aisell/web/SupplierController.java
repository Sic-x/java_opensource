package com.xmh.aisell.web;

import com.xmh.aisell.common.JsonResult;
import com.xmh.aisell.common.UIPage;
import com.xmh.aisell.domain.Supplier;
import com.xmh.aisell.query.SupplierQuery;
import com.xmh.aisell.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController extends BaseController {

    @Autowired
    private ISupplierService supplierService;

    @RequestMapping("/index")
    public String index(){
        return "supplier/index";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public List<Supplier> findAll(){
        return supplierService.findAll();
    }

    /**
     * 准备分页方法
     * @param query
     * @return
     */
    @RequestMapping("/page")
    @ResponseBody
    public UIPage page(SupplierQuery query){
        //return supplierService.queryPage(query);
        return new UIPage(supplierService.queryPage(query));
    }

    /**
     * @ModelAttribute:只要你通过前台调用我的方法，我就要执行这个方法中的内容
     *
     */
    @ModelAttribute("editSupplier")
    public Supplier beforeUpdate(Long id, String cmd){
        //我想要的是，只有修改执行相应功能
        if(id!=null && "update".equals(cmd)) {
            //根据id拿到相应的对象
            Supplier supplier = supplierService.findOne(id);
            //把关连对应的值设置为空,就可以解决n-to-n的问题
            return supplier;
        }
        return null;
    }

    /**
     * 前台会传相应的数据(Supplier)过来
     *  需要返回：JsonResult{success:true/false,msg:"xx"}
     */
    @RequestMapping("/save")
    @ResponseBody
    public JsonResult save(Supplier supplier){
        return this.saveOrUpdate(supplier);
    }

    //修改方法
    /**
     *我们的这个supplier对象中从 beforeUpdate中获取的
     */
    @RequestMapping("/update")
    @ResponseBody
    public JsonResult update(@ModelAttribute("editSupplier") Supplier supplier){
        return this.saveOrUpdate(supplier);
    }

    //添加或者修改
    public JsonResult saveOrUpdate(Supplier supplier){
        try {
            supplierService.save(supplier);
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
            supplierService.delete(id);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            //失败后返回的结果
            return new JsonResult(false,e.getMessage());
        }
    }


}

