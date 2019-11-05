package com.xmh.aisell.service.impl;

import com.xmh.aisell.domain.SystemDictionaryType;
import com.xmh.aisell.repository.SystemDictionaryTypeRepository;
import com.xmh.aisell.service.ISystemDictionaryTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemDictionaryTypeServiceImpl extends BaseServiceImpl<SystemDictionaryType,Long>
        implements ISystemDictionaryTypeService {

    @Autowired
    private SystemDictionaryTypeRepository systemDictionaryDetailRepository;

}
