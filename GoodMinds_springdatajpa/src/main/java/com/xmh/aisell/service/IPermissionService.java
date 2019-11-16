package com.xmh.aisell.service;

import com.xmh.aisell.domain.Permission;

import java.util.Set;

public interface IPermissionService extends IBaseService<Permission,Long> {

    Set<String> findSnByUser(Long userId);

}

