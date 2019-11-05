package com.xmh.aisell.service;


import com.xmh.aisell.domain.SystemDictionaryDetail;

import java.util.List;

public interface ISystemDictionaryDetailService extends IBaseService<SystemDictionaryDetail, Long> {

    List<SystemDictionaryDetail> findDetailsBySn(String sn);

}


