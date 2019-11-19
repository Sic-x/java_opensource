package com.xmh.crm.service.wxuser;

import com.xmh.basic.service.BaseService;
import com.xmh.crm.domain.wxuser.WxUser;

public interface IWxUserService extends BaseService<WxUser> {
    //根据openid查询用户
    WxUser findWxUserByOpenid(String openid);
}
