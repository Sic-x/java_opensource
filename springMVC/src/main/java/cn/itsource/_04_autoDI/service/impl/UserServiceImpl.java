package cn.itsource._04_autoDI.service.impl;

import cn.itsource._04_autoDI.dao.IUserDao;
import cn.itsource._04_autoDI.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{

    @Autowired
    private IUserDao dao;

    @Override
    public void save() {
        System.out.println("service");
        dao.save();
    }
}
