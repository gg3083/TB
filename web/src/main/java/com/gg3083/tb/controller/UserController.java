package com.gg3083.tb.controller;

import com.gg3083.tb.mapper.log.LogMapper;
import com.gg3083.tb.service.log.impl.LogServiceImpl;
import com.gg3083.tb.service.user.impl.LoginServiceImpl;
import com.gg3083.tb.common.controller.BaseController;
import com.gg3083.tb.domain.log.Log;
import com.gg3083.tb.domain.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
@RequestMapping(value = "tb")
public class UserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private LoginServiceImpl loginServiceImpl;

    @RequestMapping(value = "login")
    public String tologin() {
        return "/view/login";
    }

    @RequestMapping(value = "checkLogin")
    public String login(String loginName, String loginPwd, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject(); // 获取Subject单例对象
        try {
            if (subject.isAuthenticated()) { //已登录
                System.err.println( "用户[" + loginName + "]已登录过了)");
                return "/view/index";
            } else {
                UsernamePasswordToken token = new UsernamePasswordToken(loginName, loginPwd);
                subject.login( token );
                Session session = SecurityUtils.getSubject().getSession();
                User user = (User) subject.getPrincipal();
                session.setAttribute("user", user);//成功则放入session
                System.err.println(user);
                System.out.println("用户[" + loginName + "]登录成功");
                return "/view/index";
            }
        } catch ( UnknownAccountException uae ) {
            logger.info("对用户[" + loginName + "]进行登录验证..验证失败-username wasn't in the system");
        } catch ( IncorrectCredentialsException ice ) {
            logger.info("对用户[" + loginName + "]进行登录验证..验证失败-password didn't match");
        } catch ( LockedAccountException lae ) {
            logger.info("对用户[" + loginName + "]进行登录验证..验证失败-account is locked in the system");
        } catch ( AuthenticationException ae ) {
            logger.error(ae.getMessage());
        }
        return "/view/login";
    }

}
