package com.xmh.crm.service.repairOrder;


import com.xmh.basic.service.BaseService;
import com.xmh.crm.domain.repairOrder.RepairOrder;

public interface IRepairOrderService extends BaseService<RepairOrder> {
    Long save1(RepairOrder repairOrder);

    void update1(RepairOrder repairOrder);
    /**
     * 修改状态
     * @param id
     */
    void updatestatus(Long id);
}
