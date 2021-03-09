package com.bug1024.seckill.dao;

import com.bug1024.seckill.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface UserDao {

    public User getUser(Integer id);
    public User getUserByIdAndName(Map map);
}
