package com.gg3083.tb.controller;

import com.gg3083.tb.mapper.log.LogMapper;
import com.gg3083.tb.service.log.impl.LogServiceImpl;
import com.gg3083.tb.service.user.impl.LoginServiceImpl;
import com.gg3083.tb.common.controller.BaseController;
import com.gg3083.tb.domain.log.Log;
import com.gg3083.tb.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class UserController extends BaseController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private LogServiceImpl logServiceImpl;

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, String loginName, String loginPwd, HttpSession session) {
        Log log = new Log();
        String id = UUID.randomUUID().toString().replace("-", "");
        log.setPkLogId(id);
        log.setIp(request.getRemoteAddr());
        log.setOper("访问主页");
        logMapper.insert(log);
        request.setAttribute("ip", request.getRemoteAddr());
        if (loginServiceImpl.login(loginName,loginPwd)!=null){
            session.setAttribute("user", loginServiceImpl.login(loginName, loginPwd));
            System.err.println("==========登录成功==========");
        }
        return "view/index";
    }

    @RequestMapping(value = "/tb/login")
    public String tologin() {
        return "/view/login";
    }

    @RequestMapping(value = "/tb/checkLogin")
    public String login(String loginName, String loginPwd, HttpSession session) {
        User user = loginServiceImpl.login(loginName, loginPwd);
        if (user != null) {
            session.setAttribute("user", user);
            return "/view/index";
        } else {
            return "/view/login";
        }
    }

}
