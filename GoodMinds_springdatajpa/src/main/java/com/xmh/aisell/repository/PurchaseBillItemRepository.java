package com.xmh.aisell.repository;

import com.xmh.aisell.domain.PurchaseBillItem;
import com.xmh.aisell.domain.Supplier;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;


public interface PurchaseBillItemRepository extends BaseRepository<PurchaseBillItem,Long> {

    @Query("select distinct s.name from PurchaseBillItem p join p.bill b join b.supplier s")
    List<String> findSupplier();

    @Query("select sum(p.amount)  from PurchaseBillItem p join p.bill b join b.supplier s where s.name=?1")
    BigDecimal findAmountBySupplier(String name);
}
