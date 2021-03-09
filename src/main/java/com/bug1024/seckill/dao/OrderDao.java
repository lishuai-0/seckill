package com.bug1024.seckill.dao;

import com.bug1024.seckill.entity.SeckillOrder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface OrderDao {

    public Integer insert(SeckillOrder order);
    public SeckillOrder selectOrderByItemIdAndUserId(Map map);
    public SeckillOrder selectOrderByOrderId(Integer id);

    public List<SeckillOrder> selectAllNoPayOrder();

    public Integer updateOrderState(Map map);
}
