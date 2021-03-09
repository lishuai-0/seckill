package com.bug1024.seckill.entity;


import java.util.Date;

/**
 * 秒杀订单
 */
public class SeckillOrder {


    private Integer id;
    private Integer seckillItemId;
    private Integer userId;
    private Integer state;//0未支付，1已支付，2订单超时
    private Date createTime;

    public SeckillOrder(Integer id, Integer seckillItemId, Integer userId, Integer state, Date createTime) {
        this.id = id;
        this.seckillItemId = seckillItemId;
        this.userId = userId;
        this.state = state;
        this.createTime = createTime;
    }

    public SeckillOrder() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeckillItemId() {
        return seckillItemId;
    }

    public void setSeckillItemId(Integer seckillItemId) {
        this.seckillItemId = seckillItemId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
