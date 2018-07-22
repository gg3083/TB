package com.gg3083.tb.controller;

import com.gg3083.tb.domain.log.Log;
import com.gg3083.tb.mapper.log.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class IndexController {

    @Autowired
    private LogMapper logMapper;

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, String loginName, String loginPwd, HttpSession session) {
        Log log = new Log();
        String id = UUID.randomUUID().toString().replace("-", "");
        log.setPkLogId(id);
        log.setIp(request.getRemoteAddr());
        log.setOper("访问主页");
        logMapper.insert(log);
        request.setAttribute("ip", request.getRemoteAddr());
        return "view/index";
    }
}
