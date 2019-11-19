package com.xmh.crm.mapper.setted;

import com.xmh.basic.mapper.BaseMapper;
import com.xmh.crm.domain.setted.Setted;

import java.math.BigDecimal;

public interface SettedMapper extends BaseMapper<Setted> {
// BigDecimal amount,
    void updateNameAndPayName(String name, Long payId,BigDecimal i, Long settedId);
    Long findMainIdByid(Long id);
}
