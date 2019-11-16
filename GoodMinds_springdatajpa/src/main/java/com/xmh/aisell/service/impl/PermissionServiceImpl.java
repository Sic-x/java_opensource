package com.xmh.aisell.service.impl;


import com.xmh.aisell.domain.Employee;
import com.xmh.aisell.domain.Permission;
import com.xmh.aisell.repository.PermissionRepository;
import com.xmh.aisell.service.IPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl extends BaseServiceImpl<Permission,Long>
        implements IPermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Set<String> findSnByUser(Long userId) {
       /* Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee)subject.getPrincipal();*/
        return permissionRepository.findSnByUser(userId);
    }
}