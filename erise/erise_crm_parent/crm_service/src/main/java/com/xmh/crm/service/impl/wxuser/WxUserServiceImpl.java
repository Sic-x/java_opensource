package com.xmh.crm.service.impl.wxuser;

import com.xmh.basic.service.impl.BaseServiceImpl;
import com.xmh.crm.domain.wxuser.WxUser;
import com.xmh.crm.mapper.wxusermapper.WxUserMapper;
import com.xmh.crm.service.wxuser.IWxUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WxUserServiceImpl extends BaseServiceImpl<WxUser> implements IWxUserService {

    @Autowired
    private WxUserMapper wxUserMapper;

    @Override
    public WxUser findWxUserByOpenid(String openid) {
        return wxUserMapper.findWxUserByOpenid(openid);
    }
}
