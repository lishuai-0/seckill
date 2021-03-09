package com.bug1024.seckill.service.imp;

import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.druid.util.StringUtils;
import com.bug1024.seckill.dao.OrderDao;
import com.bug1024.seckill.dao.RedisDao;
import com.bug1024.seckill.dao.SeckillItemDao;
import com.bug1024.seckill.domain.SeckillURL;
import com.bug1024.seckill.entity.SeckillItem;
import com.bug1024.seckill.entity.SeckillOrder;
import com.bug1024.seckill.entity.User;
import com.bug1024.seckill.service.ItemService;
import com.bug1024.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImp implements ItemService {

    @Autowired
    SeckillItemDao seckillItemDao;

    @Autowired
    RedisDao redisDao;

    @Autowired
    OrderService orderService;

    public List<SeckillItem> getSeckillItems(Integer page,Integer limit) {
        if (page>0){
            Map map = new HashMap();
            map.put("start",(page-1)*limit);
            map.put("end",page*limit);
            return seckillItemDao.getSeckillItems(map);
        }
        return null;
    }

    public Integer getItemsNum() {
        return seckillItemDao.getItemsNum();
    }
    public SeckillItem getSeckillItem(Integer id){
        return seckillItemDao.getSeckillItem(id);
    }

    //返回秒杀URL
    //md5加密
    public SeckillURL getSeckillUrl(Integer id) {
        if (ObjectUtils.isEmpty(id)){
            return new SeckillURL(false,"秒杀商品不存在",id);
        }
        SeckillItem seckillItem= (SeckillItem) redisDao.get(String.valueOf(id));
        if (ObjectUtils.isEmpty(seckillItem)){
            //如果redis中不存在，则从数据库中取出，存入redis
            seckillItem = seckillItemDao.getSeckillItem(id);
            Long time = seckillItem.getEndTime().getTime()-seckillItem.getStartTime().getTime();
            redisDao.set(String.valueOf(id),seckillItem,time);
            //redis中存储库存的key,value
            String stockKey = "stock_"+id;
            Integer stockNum= seckillItemDao.getSeckillItem(id).getNumber();
            redisDao.set(stockKey,stockNum,new Long(10*60));
        }
        Date startTime = seckillItem.getStartTime();
        Date endTime = seckillItem.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime()<startTime.getTime()||nowTime.getTime()>endTime.getTime()){
            return new SeckillURL(false,"不在秒杀时间",id);
        }
        String seckillUrlMD5 = getSeckillUrlMD5(id);
        System.out.println(seckillUrlMD5+"--------------------");
        return new SeckillURL(true,seckillUrlMD5,startTime,endTime,"秒杀开始",id);
    }

    private static final String  md5str = "fsdfa&*&$#58fds";
    public String getSeckillUrlMD5(Integer id){
        return DigestUtil.md5Hex(id+"|"+md5str);
    }

    /**
     * 验证id和md5是否对应
     * @param id
     * @param md5
     * @return
     */
    public Boolean verifyMd5(Integer id,String md5){
        String str = getSeckillUrlMD5(id);
        if (StringUtils.isEmpty(md5) || !md5.equals(str)){
            return false;
        }
        return true;
    }

    //执行秒杀业务

    /**
     *
     * @param user
     * @param seckillId
     * @return
     * 返回0，表示已经商品已经抢完了，返回1，表示已经抢到，返回2，秒杀商品不存在,3用户重复点击
     */
    @Transactional
    public Integer excuteSeckill(User user, Integer seckillId) {
        if (ObjectUtils.isEmpty(seckillId)){
            return 2;
        }
        //redis中存储用户是否成功参与秒杀的key
        String key1 = user.getPhone()+"_"+seckillId;
        //检查redis里面是否有用户信息，如果没有，存进去
        Integer redisSeckillId = (Integer) redisDao.get(key1);
        if (!ObjectUtils.isEmpty(redisSeckillId)){
            return 2;
        }
        Boolean setResult = redisDao.set(user.getPhone() + "_" + seckillId, seckillId, new Long(10 * 60));
        if (!setResult) {
            return 3;
        }
        //redis中存储库存的key
        String stockKey= "stock_"+seckillId;
        //减库存
        // 返回结果 -1 库存不足   -2 不存在  整数是正常操作，减库存成功
        Integer result = redisDao.stockDecr(stockKey);
        if (result == -1){
            //商品已经抢完
            return 0;
        }else if (result == -2){
            //秒杀商品不存在
            return 2;
        }
        //成功抢到商品，暂时存入redis，等待支付
        SeckillOrder order = new SeckillOrder(null,seckillId,user.getId(),0,new Date());
        orderService.insert(order);
        order = orderService.selectOrderByItemIdAndUserId(seckillId,user.getId());
        redisDao.set(String.valueOf(order.getId()),order,10*60L);
        //订单库存减一
        seckillItemDao.stockSub(seckillId);
        return 1;

    }

}
