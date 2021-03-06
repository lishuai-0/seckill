package com.bug1024.seckill.controller;

import cn.hutool.db.sql.Order;
import com.bug1024.seckill.domain.RequestResult;
import com.bug1024.seckill.domain.SeckillURL;
import com.bug1024.seckill.entity.SeckillItem;
import com.bug1024.seckill.entity.SeckillOrder;
import com.bug1024.seckill.entity.User;
import com.bug1024.seckill.service.ItemService;
import com.bug1024.seckill.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/seckill")
public class ItemController {

    @Autowired
    ItemService itemService;

    @Autowired
    OrderService orderService;

    @RequestMapping("/getSecKillItems")
    @ResponseBody
    public Map getSecKillItems(Integer page, Integer limit){
        List<SeckillItem> seckillItems = itemService.getSeckillItems(page, limit);
        HashMap<String,Object> map = new HashMap<String, Object>();
        Integer count = itemService.getItemsNum();
        map.put("count",count);
        map.put("code",0);
        map.put("msg","");
        map.put("data",seckillItems);
        return  map;
    }
    @RequestMapping(value = "/{seckillItemId}",method = RequestMethod.GET)
    @ResponseBody
    public Map seckillItem(@PathVariable("seckillItemId") Integer seckillItemId){
        System.out.println(seckillItemId);
        return null;
    }

    @RequestMapping("/getSecKillItem")
    @ResponseBody
    public SeckillItem getSecKillItem(Integer id){
        return itemService.getSeckillItem(id);
    }
    @RequestMapping("/getSecKillItemNum")
    @ResponseBody
    public Integer getSecKillItemNum(Integer id){
        return itemService.getSeckillItem(id).getNumber();
    }

    @RequestMapping("/getSeckillURL")
    @ResponseBody
    public SeckillURL getSeckillURL(Integer id){
        return itemService.getSeckillUrl(id);
    }

    @RequestMapping("/excuteSeckill/{seckillItemId}/{secKillURL}")
    @ResponseBody
    public RequestResult excuteSeckill(@PathVariable("seckillItemId") Integer seckillItemId
                                    , @PathVariable("secKillURL") String secKillURL, HttpSession session){
        Boolean access = itemService.verifyMd5(seckillItemId,secKillURL);
        if (access){
            User user = (User) session.getAttribute("user");
            if (!ObjectUtils.isEmpty(user)){
                //?????????????????????????????????
                Integer seckillResult = itemService.excuteSeckill(user,seckillItemId);
                System.out.println(seckillResult);
                if (seckillResult==1){
                    //????????????????????????????????????
                    SeckillOrder order = orderService.selectOrderByItemIdAndUserId(seckillItemId,user.getId()) ;
                    return new RequestResult<String>(200,"ok","????????????,??????????????????????????????","/view/pay?orderId="+order.getId());
                }else if (seckillResult==0){
                    return new RequestResult<String>(200,"false","?????????????????????");
                }else if (seckillResult==2){
                    return new RequestResult<String>(200,"false","?????????????????????");
                }else if (seckillResult==3){
                    return new RequestResult<String>(200,"false","?????????????????????");
                }
            }else{
                return new RequestResult<String>(300,"false","????????????");
            }

        }
        return new RequestResult<String>(100,"no","??????????????????????????????");
    }


}
