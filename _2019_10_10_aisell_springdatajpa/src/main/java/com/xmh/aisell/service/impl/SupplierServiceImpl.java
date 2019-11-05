package com.xmh.aisell.service.impl;


import com.xmh.aisell.domain.Supplier;
import com.xmh.aisell.repository.SupplierRepository;
import com.xmh.aisell.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SupplierServiceImpl extends BaseServiceImpl<Supplier,Long>
        implements ISupplierService {

    @Autowired
    private SupplierRepository permissionRepository;


}