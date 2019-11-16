package com.xmh.aisell.service.impl;


import com.xmh.aisell.domain.PurchaseBillItem;
import com.xmh.aisell.domain.Supplier;
import com.xmh.aisell.domain.vo.PurchaseBillItemVO;
import com.xmh.aisell.query.PurchaseBillItemQuery;
import com.xmh.aisell.repository.PurchaseBillItemRepository;
import com.xmh.aisell.service.IPurchaseBillItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PurchaseBillItemServiceImpl extends BaseServiceImpl<PurchaseBillItem,Long>
        implements IPurchaseBillItemService {

    @Autowired
    private PurchaseBillItemRepository permissionRepository;


    @Override
    public List<String> findSupplier() {
        return permissionRepository.findSupplier();
    }

    @Override
    public List<PurchaseBillItemVO> findVos(PurchaseBillItemQuery query) {
        //1.准备装vo的容器
        List<PurchaseBillItemVO> vos = new ArrayList<>();
        //2.获取到所有的明细(根据条件查询)
        List<PurchaseBillItem> items = super.queryAll(query);
        //3.循环明细，把里面的item变成vo,再放到容器中
        for (PurchaseBillItem item : items) {
            PurchaseBillItemVO vo = new PurchaseBillItemVO(item,query.getGroupBy());
            vos.add(vo);
        }
        return vos;
    }

    @Override
    public BigDecimal findAmountBySupplier(String name) {
        return permissionRepository.findAmountBySupplier(name);
    }
}