package com.xmh.crm.service.impl.repairOrderItem;

import com.xmh.basic.service.impl.BaseServiceImpl;
import com.xmh.crm.domain.repairOrderItem.RepairOrderItem;
import com.xmh.crm.mapper.repairOrderItem.RepairOrderItemMapper;
import com.xmh.crm.service.repairOrderItem.IRepairOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairOrderItemServiceImpl extends BaseServiceImpl<RepairOrderItem> implements IRepairOrderItemService {
    @Autowired
    private RepairOrderItemMapper itemMapper;

    @Override
    public RepairOrderItem findOne1(Long id) {
        return itemMapper.findOne1(id);
    }
}
