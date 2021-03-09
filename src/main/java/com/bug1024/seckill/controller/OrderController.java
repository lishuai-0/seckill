package com.bug1024.seckill.controller;

import com.bug1024.seckill.domain.RequestResult;
import com.bug1024.seckill.entity.SeckillOrder;
import com.bug1024.seckill.entity.User;
import com.bug1024.seckill.service.OrderService;
import com.bug1024.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/getOrder/{orderId}")
    @ResponseBody
    public SeckillOrder getUser(@PathVariable("orderId") Integer orderId){
        SeckillOrder order = orderService.selectOrderByOrderId(orderId);
        return order;
    }


    @RequestMapping("/pay/{orderId}")
    public String pay(Model model, @PathVariable("orderId")Integer orderId){
        RequestResult<SeckillOrder> result = new RequestResult<SeckillOrder>();
        //默认请求失败，
        result.setCode(100);

        SeckillOrder order = orderService.selectOrderByOrderId(orderId);
        if (ObjectUtils.isEmpty(orderId)||ObjectUtils.isEmpty(order)){
            result.setCode(100);
            result.setMsg("订单不存在");
            result.setUrl("/");
            return "page/jsp";
        }
        if (order.getState()==1){
            //订单已支付
            result.setCode(200);
            result.setMsg("订单已支付");
            result.setUrl("/");
            result.setData(order);
            return "page/pay";
        }else if (order.getState()==2){
            //订单已超时
            //订单已支付
            result.setCode(200);
            result.setMsg("订单已超时，请重新下单");
            result.setUrl("/");
            result.setData(order);
            return "page/pay";
        }else if (order.getState()==1){
            //支付接口
            //订单已超时
            //订单已支付
            result.setCode(200);
            result.setMsg("订单支付成功");
            result.setUrl("/");
            result.setData(order);
            return "page/pay";
        }else{
            result.setCode(100);
            result.setMsg("订单状态异常");
            result.setUrl("/");
            return "page/jsp";
        }
    }
}
