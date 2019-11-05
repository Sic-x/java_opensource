package com.xmh.aisell.service;

import com.xmh.aisell.domain.Menu;

import java.util.List;

public interface IMenuService extends IBaseService<Menu,Long>{

    List<Menu> findParentMenus();

    List<Menu> findByUser();
}
