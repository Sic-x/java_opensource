package com.xmh.aisell.web;

import com.xmh.aisell.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private IMenuService menuService;

    /**
     * 前台请求url匹配
     * @return 通过视图解析器跳转index.jsp
     */
    @RequestMapping("/menuIndex")
    public String index(){
        return "menuIndex";
    }


}
