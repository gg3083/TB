package com.gg3083.tb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class TbHelperController {

    /**
     *  后台首页
     * @return
     */
    @RequestMapping("/")
    public String index(){
        System.err.println("------------进入后台--------------");
        return "/admin/index";
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "/view/index";
    }


    /**
     * 角色管理
     * @return
     */
    @RequestMapping("/role")
    public String toRole(){
        return "/admin/role/role";
    }

    /**
     * 菜单管理
     * @return
     */
    @RequestMapping("/menu")
    public String toMenu(){
        return "/admin/role/menu";
    }


}
