package com.bug1024.seckill.service;

import com.bug1024.seckill.entity.SeckillOrder;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public SeckillOrder selectOrderByItemIdAndUserId(Integer seckillItemId,Integer userId);


    public SeckillOrder selectOrderByOrderId(Integer id);

    public Integer insert(SeckillOrder order);

    public Integer updateOrderState(Integer orderId,Integer state);
    public List<SeckillOrder> selectAllNoPayOrder();
}
