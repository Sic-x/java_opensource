package com.xmh.crm.mapper.parts;

import com.xmh.basic.mapper.BaseMapper;
import com.xmh.crm.domain.parts.Parts;

public interface PartsMapper extends BaseMapper<Parts> {
   long findIdByPartsName(String partsname);

}
