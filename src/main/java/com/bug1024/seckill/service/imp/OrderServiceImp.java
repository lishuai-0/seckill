package com.bug1024.seckill.service.imp;

import com.bug1024.seckill.dao.OrderDao;
import com.bug1024.seckill.dao.UserDao;
import com.bug1024.seckill.entity.SeckillOrder;
import com.bug1024.seckill.entity.User;
import com.bug1024.seckill.service.OrderService;
import com.bug1024.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    @Autowired
    OrderDao orderDao;

    public SeckillOrder selectOrderByItemIdAndUserId(Integer seckillItemId,Integer userId) {
        HashMap map = new HashMap();
        map.put("seckillItemId",seckillItemId);
        map.put("userId",userId);
        return orderDao.selectOrderByItemIdAndUserId(map);
    }

    public SeckillOrder selectOrderByOrderId(Integer id) {
        return orderDao.selectOrderByOrderId(id);
    }

    public Integer insert(SeckillOrder order) {
        return orderDao.insert(order);
    }

    public Integer updateOrderState(Integer orderId, Integer state) {
        HashMap map = new HashMap();
        map.put("orderId",orderId);
        map.put("state",state);
        return orderDao.updateOrderState(map);
    }

    public List<SeckillOrder> selectAllNoPayOrder() {
        return orderDao.selectAllNoPayOrder();
    }
}


