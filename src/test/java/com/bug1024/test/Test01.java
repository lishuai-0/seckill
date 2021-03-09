package com.bug1024.test;


import com.bug1024.seckill.dao.OrderDao;
import com.bug1024.seckill.entity.SeckillOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

// 让单元测试在运行的时候，基于spring容器运行，先加载spring容器，基于spring环境进行单元测试
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"}) //加载配置文件
public class Test01 {

    @Autowired
    OrderDao orderDao;

    @Test
    public void test(){
       orderDao.insert(new SeckillOrder(null,1,123,0,new Date()));
    }
}
