package com.xmh.crm.service.setted;


import com.sun.xml.internal.bind.v2.model.core.ID;
import com.xmh.basic.service.BaseService;
import com.xmh.crm.domain.repairOrder.RepairOrder;
import com.xmh.crm.domain.setted.Setted;

public interface ISettedService extends BaseService<Setted> {
    /**
     * 根据id修改客户姓名
     */
   void updateNameById(Setted setted);

    /**
     * 修改结算单的客户姓名和支付方式
     * @param setted
     */
    void updateNameAndPayName(Setted setted);

    /**
     * 修改明细
     * @param setted
     */
    void updateRepairOrderItemService(Setted setted);

    void removeOrderAndOitem(Long id);
}
