package com.xmh.crm.service.impl.setted;

import com.xmh.basic.service.impl.BaseServiceImpl;
import com.xmh.crm.domain.repairOrder.RepairOrder;
import com.xmh.crm.domain.setted.Setted;
import com.xmh.crm.mapper.parts.PartsMapper;
import com.xmh.crm.mapper.repairOrder.RepairOrderMapper;
import com.xmh.crm.mapper.repairOrderItem.RepairOrderItemMapper;
import com.xmh.crm.mapper.setted.SettedMapper;
import com.xmh.crm.service.repairOrder.IRepairOrderService;
import com.xmh.crm.service.setted.ISettedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class SettedServiceImpl extends BaseServiceImpl<Setted> implements ISettedService {
//    @Autowired
//    private IRepairOrderService service;
        @Autowired
        private RepairOrderMapper mapper;
        @Autowired
        private SettedMapper smapper;
        @Autowired
        private PartsMapper pmapper;
        @Autowired
        private RepairOrderItemMapper opmapper;
    /**
     * 修改客戶姓名和地址
     * @param setted
     */
    @Override
    public void updateNameById(Setted setted) {
        Long id = setted.getRepairOrder().getId();
        String name = setted.getCustormer();
        String address = setted.getRepairOrder().getAddress();
        RepairOrder repairOrder = new RepairOrder();
        repairOrder.setId(id);
        repairOrder.setCustormer(name);
        repairOrder.setAddress(address);
        int i = mapper.updateNameAndAddress(repairOrder);
    }

    @Override
    public void updateNameAndPayName(Setted setted) {
        String name = setted.getCustormer();
        Long payId = setted.getPay().getId();
        Long settId = setted.getId();
        BigDecimal amount = setted.getPay_amount();
        smapper.updateNameAndPayName(name,payId,amount,settId);
    }
    @Override
    public void updateRepairOrderItemService(Setted setted) {
        String partsName = setted.getPartsname();
        //获取配件id
        Long  partsId = pmapper.findIdByPartsName(partsName);
        //获取oitemId
        Long oitemId = opmapper.findOitemByMainId(setted.getRepairOrder().getId());
        opmapper.updatePidByoitemId(partsId,oitemId);
    }
    @Override
    public void removeOrderAndOitem(Long id) {
          System.out.println(id);
        Long smid = smapper.findMainIdByid(id);
        Long oitemByMainId = opmapper.findOitemByMainId(smid);
          smapper.remove(id);
          mapper.remove(smid);
          opmapper.remove(oitemByMainId);
    }
}
