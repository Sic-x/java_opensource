package cn.itsource._05_xmlaop.service.impl;

import cn.itsource._05_xmlaop.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {


    @Override
    public void save() {
        System.out.println("service");
    }
}
