package cn.itsource._06_auto_aop.service.impl;

import cn.itsource._06_auto_aop.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {


    @Override
    public void save() {
        System.out.println("service");
    }
}
