package com.xmh.crm.mapper.systemMenu;

import com.xmh.basic.mapper.BaseMapper;
import com.xmh.crm.domain.systemMenu.SystemMenu;

import java.util.List;

public interface SystemMenuMapper extends BaseMapper<SystemMenu> {

    List<SystemMenu> tenantFindAll(Long id);
}
