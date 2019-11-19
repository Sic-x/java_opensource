package com.xmh.crm.mapper.wxusermapper;

import com.xmh.basic.mapper.BaseMapper;
import com.xmh.crm.domain.wxuser.WxUser;

public interface WxUserMapper extends BaseMapper<WxUser> {
    //根据openid查询用户
    WxUser findWxUserByOpenid(String openid);
}
