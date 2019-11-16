package com.xmh.aisell.service;

import com.xmh.aisell.domain.PurchaseBillItem;
import com.xmh.aisell.domain.vo.PurchaseBillItemVO;
import com.xmh.aisell.query.PurchaseBillItemQuery;

import java.math.BigDecimal;
import java.util.List;

public interface IPurchaseBillItemService extends IBaseService<PurchaseBillItem,Long> {


    List<String> findSupplier();

    List<PurchaseBillItemVO> findVos(PurchaseBillItemQuery query);

    BigDecimal findAmountBySupplier(String name);
}

