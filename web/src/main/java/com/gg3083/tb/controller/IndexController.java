package com.gg3083.tb.controller;

import com.gg3083.tb.common.controller.BaseController;
import com.gg3083.tb.common.exception.GGException;
import com.gg3083.tb.domain.log.Log;
import com.gg3083.tb.domain.user.User;
import com.gg3083.tb.json.JsonResult;
import com.gg3083.tb.mapper.log.LogMapper;
import com.gg3083.tb.service.log.impl.LogServiceImpl;
import com.gg3083.tb.util.NoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private LogMapper logMapper;

    @Autowired
    private LogServiceImpl logService;

    @RequestMapping(value = "/")
    public String index(HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Log log = new Log();
        String ip = request.getRemoteAddr();
        log.setPkLogId(NoUtil.getDateNo());
        log.setIp(ip);
        log.setOper("访问主页");
        if (user!=null){
            log.setFkUserId( user.getPkUserId() );
            log.setUserName( user.getUserName() );
        }
        logMapper.insertSelective(log);
        request.setAttribute("ip", ip);
        return "view/index";
    }

    @RequestMapping("/demo")
    @ResponseBody
    public JsonResult demo(){
        //throw new GGException("自定义异常");
        return buildSuccess(new JsonResult(),logService.list());
    }
}
