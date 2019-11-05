package com.xmh.aisell.repository;

import com.xmh.aisell.domain.Menu;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 菜单持久层
 */
public interface MenuRepository extends BaseRepository<Menu,Long> {

    /**
     * 获取到所有父菜单
     * @return
     */
    @Query("select o from Menu o where o.url is null ")
    List<Menu> findParentMenus();

    /**
     * 从员工拿到角色集合，从角色拿到权限的集合，根据相应的权限拿到相应的菜单集合，去重
     * 这样只子菜单
     * @param userId
     * @return
     */
    @Query("select distinct m from Employee e join e.roles r join r.permissions p join p.menu m where e.id =?1")
    List<Menu> findByUser(Long userId);
}
