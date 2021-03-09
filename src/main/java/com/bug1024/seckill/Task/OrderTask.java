package com.bug1024.seckill.Task;

import com.bug1024.seckill.dao.RedisDao;
import com.bug1024.seckill.entity.SeckillOrder;
import com.bug1024.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@EnableScheduling()
@Component
public class OrderTask {

    @Autowired
    OrderService orderService;
    @Autowired
    RedisDao redisDao;


    @Scheduled(fixedRate = 2000)
    public void orderPayCountDown(){
        System.out.println("fdsafds");
        List<SeckillOrder> orders = orderService.selectAllNoPayOrder();
        for (SeckillOrder order:orders){
            SeckillOrder order1 = (SeckillOrder) redisDao.get(String.valueOf(order.getId()));
            if (ObjectUtils.isEmpty(order1)){
                orderService.updateOrderState(order.getId(),2);
            }
        }
    }
}
