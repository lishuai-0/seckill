package com.bug1024.seckill.domain;

import java.util.Date;

public class SeckillURL {

    /**
     * enable秒杀是否有效，seckillURL秒杀url，beginTime秒杀开始时间，endTime秒杀结束时间
     * msg秒杀说明，id秒杀商品id
     */
    private Boolean enable;
    private String seckillUrl;
    private Date beginTime;
    private Date endTime;
    private String msg;
    private Integer id;

    public SeckillURL(Boolean enable, String seckillUrl, Date beginTime, Date endTime, String msg, Integer id) {
        this.enable = enable;
        this.seckillUrl = seckillUrl;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.msg = msg;
        this.id = id;
    }

    public SeckillURL(Boolean enable,  String msg, Integer id) {
        this.enable = enable;
        this.seckillUrl = seckillUrl;
        this.msg = msg;
        this.id = id;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public String getSeckillUrl() {
        return seckillUrl;
    }

    public void setSeckillUrl(String seckillUrl) {
        this.seckillUrl = seckillUrl;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
