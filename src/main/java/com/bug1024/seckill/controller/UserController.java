package com.bug1024.seckill.controller;

import com.bug1024.seckill.entity.User;
import com.bug1024.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(Integer id){
        User user = userService.getUser(id);
        System.out.println(user);
        return user;
    }

    @RequestMapping("/login")
    public ModelAndView login(Integer id, String password, HttpSession session){
        ModelAndView mv = new ModelAndView();
        //通过id和密码获取用户
        User user = userService.login(id,password);
        if (user!=null){//登录成功
            mv.setViewName("/page/welcome");
            mv.addObject("user",user);
            session.setAttribute("user",user);
        }else {//登录失败
            mv.setViewName("/page/index");
        }
        return mv;

    }
}
