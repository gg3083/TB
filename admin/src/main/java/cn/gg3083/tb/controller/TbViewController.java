package cn.gg3083.tb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "admin")
public class TbViewController {

    @RequestMapping("/")
    public String index(){
        System.err.println("------------进入后台--------------");
        return "admin/index";
    }

    @RequestMapping(value = "logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "view/index";
    }
}
