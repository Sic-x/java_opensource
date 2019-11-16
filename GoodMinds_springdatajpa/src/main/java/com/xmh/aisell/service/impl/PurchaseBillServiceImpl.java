package com.xmh.aisell.service.impl;


import com.xmh.aisell.domain.PurchaseBill;
import com.xmh.aisell.repository.PurchaseBillRepository;
import com.xmh.aisell.service.IPurchaseBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PurchaseBillServiceImpl extends BaseServiceImpl<PurchaseBill,Long>
        implements IPurchaseBillService {

    @Autowired
    private PurchaseBillRepository permissionRepository;


}