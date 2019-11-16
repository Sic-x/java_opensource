package com.xmh.aisell.service.impl;

import com.xmh.aisell.domain.SystemDictionaryDetail;
import com.xmh.aisell.repository.SystemDictionaryDetailRepository;
import com.xmh.aisell.service.ISystemDictionaryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryDetailServiceImpl extends BaseServiceImpl<SystemDictionaryDetail,Long>
        implements ISystemDictionaryDetailService {

    @Autowired
    private SystemDictionaryDetailRepository systemDictionaryDetailRepository;

    @Override
    public List<SystemDictionaryDetail> findDetailsBySn(String sn) {
        return systemDictionaryDetailRepository.findDetailsBySn(sn);
    }
}
