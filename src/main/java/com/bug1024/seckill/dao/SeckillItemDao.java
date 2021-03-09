package com.bug1024.seckill.dao;


import com.bug1024.seckill.entity.SeckillItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public interface SeckillItemDao {
    public List<SeckillItem> getSeckillItems(Map map);
    public Integer getItemsNum();
    public SeckillItem getSeckillItem(Integer id);
    public Integer stockSub(Integer seckillItemId);
}
