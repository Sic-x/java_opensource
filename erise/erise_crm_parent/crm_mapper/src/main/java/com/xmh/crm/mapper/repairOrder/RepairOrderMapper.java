package com.xmh.crm.mapper.repairOrder;

import com.xmh.basic.mapper.BaseMapper;
import com.xmh.crm.domain.repairOrder.RepairOrder;
import com.xmh.crm.domain.setted.Setted;

public interface RepairOrderMapper extends BaseMapper<RepairOrder> {

    Long save1(RepairOrder repairOrder);

    /**
     *根据id修改客戶姓名
     */
    int updateNameAndAddress(RepairOrder order);

    /**
     * 修改状态
     * @param id
     */
    void updatestatus(Long id);
}
