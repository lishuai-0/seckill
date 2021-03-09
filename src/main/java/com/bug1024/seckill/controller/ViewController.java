package com.bug1024.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/view")
public class ViewController {

    @RequestMapping(value = "/{jsp}",method = RequestMethod.GET)
    public String getView(@PathVariable String jsp){
        return "page/"+jsp;
    }
}
