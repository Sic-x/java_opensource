package cn.itsource._04_autoDI.dao.impl;

import cn.itsource._04_autoDI.dao.IUserDao;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements IUserDao{
    @Override
    public void save() {
        System.out.println("dao");
    }
}
