package com.bug1024.seckill.service.imp;

import com.bug1024.seckill.entity.User;
import com.bug1024.seckill.dao.UserDao;
import com.bug1024.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDao;
    public User getUser(Integer id) {
        return userDao.getUser(id);
    }

    public User login(Integer id, String password) {
        HashMap map = new HashMap();
        map.put("id",id);
        map.put("password",password);
        return userDao.getUserByIdAndName(map);
    }
}
