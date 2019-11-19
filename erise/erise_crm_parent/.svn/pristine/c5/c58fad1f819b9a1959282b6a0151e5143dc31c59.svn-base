package com.xmh.crm.service.impl.repairOrder;

import com.xmh.basic.service.impl.BaseServiceImpl;
import com.xmh.crm.domain.repairOrder.RepairOrder;
import com.xmh.crm.domain.setted.Setted;
import com.xmh.crm.mapper.repairOrder.RepairOrderMapper;
import com.xmh.crm.service.repairOrder.IRepairOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairOrderServiceImpl extends BaseServiceImpl<RepairOrder> implements IRepairOrderService {
    @Autowired
    private RepairOrderMapper repairOrderMapper;
    @Override
    public Long save1(RepairOrder repairOrder) {
        return repairOrderMapper.save1(repairOrder);
    }


    @Override
    public void update1(RepairOrder repairOrder) {
        repairOrderMapper.updateNameAndAddress(repairOrder);
    }
    /**
     * 修改状态
     * @param id
     */
    @Override
    public void updatestatus(Long id) {
        repairOrderMapper.updatestatus(id);
    }
}
