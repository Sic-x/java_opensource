package com.xmh.crm.mapper.repairOrderItem;

import com.xmh.basic.mapper.BaseMapper;
import com.xmh.crm.domain.repairOrderItem.RepairOrderItem;

public interface RepairOrderItemMapper extends BaseMapper<RepairOrderItem> {

    RepairOrderItem findOne1(Long id);

    Long findOitemByMainId(Long id);

    void updatePidByoitemId(Long partsId, Long oitemId);
}
