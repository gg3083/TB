package com.gg3083.tb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("admin")
public class TbUserController {

    @RequestMapping("/")
    public String index(){
        System.err.println("------------进入后台--------------");
        return "/admin/index";
    }
}
