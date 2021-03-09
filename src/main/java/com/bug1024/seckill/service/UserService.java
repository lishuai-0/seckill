package com.bug1024.seckill.service;

import com.bug1024.seckill.entity.User;

public interface UserService {
    public User getUser(Integer id);
    public User login(Integer id,String password);
}
