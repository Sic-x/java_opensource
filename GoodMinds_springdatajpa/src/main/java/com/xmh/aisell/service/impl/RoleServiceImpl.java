package com.xmh.aisell.service.impl;

import com.xmh.aisell.domain.Role;
import com.xmh.aisell.repository.RoleRepository;
import com.xmh.aisell.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role,Long>
        implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

}
