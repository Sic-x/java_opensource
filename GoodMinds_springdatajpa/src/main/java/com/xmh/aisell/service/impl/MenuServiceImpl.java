package com.xmh.aisell.service.impl;

import com.xmh.aisell.common.UserContext;
import com.xmh.aisell.domain.Menu;
import com.xmh.aisell.repository.MenuRepository;
import com.xmh.aisell.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu,Long> implements IMenuService{

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> findParentMenus() {
        List<Menu> parentMenus = menuRepository.findParentMenus();
        parentMenus.forEach(e->{
            e.setState("closed");
            System.out.println(e);
        });
        return parentMenus;
    }

    @Override
    public List<Menu> findByUser() {

        //获取未处理的子菜单集合
        List<Menu> menuList = menuRepository.findByUser(UserContext.getUser().getId());
        //创建需要遍历添加的父菜单集合
        List<Menu> parents = new ArrayList<>();
        //遍历
        menuList.forEach(menu -> {

            //获取它的父集合
            Menu parent = menu.getParent();
            //空集合或没有这个父菜单的时候进行添加
            if(parents==null||!parents.contains(parent)){
                parent.setState("closed");
                parents.add(parent);
            }
            //添加好了再把当前子菜单添加到当前父菜单子菜单集合中
            parent.getChildren().add(menu);
        });
        return parents;
    }
}

