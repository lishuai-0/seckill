package com.bug1024.seckill.service;



import com.bug1024.seckill.domain.SeckillURL;
import com.bug1024.seckill.entity.SeckillItem;
import com.bug1024.seckill.entity.SeckillOrder;
import com.bug1024.seckill.entity.User;

import java.util.List;

public interface ItemService {
    public List<SeckillItem> getSeckillItems(Integer page,Integer limit);
    public Integer getItemsNum();
    public SeckillItem getSeckillItem(Integer id);

    public SeckillURL getSeckillUrl(Integer id);

    public Boolean verifyMd5(Integer id,String md5);
    public Integer excuteSeckill(User user, Integer seckillId);
}
