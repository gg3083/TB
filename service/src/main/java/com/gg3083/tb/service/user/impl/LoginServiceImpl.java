package com.gg3083.tb.service.user.impl;

import com.gg3083.tb.json.JsonResult;
import com.gg3083.tb.mapper.user.UserMapper;
import com.gg3083.tb.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gg3083.tb.domain.user.User;

@Service
public class LoginServiceImpl {

    @Autowired
    private UserMapper userMapper;


    public User login(String loginName, String loginPwd) {
        loginPwd = Md5Util.getMd5("GG", loginPwd);
        System.err.println(loginPwd);
        User u = userMapper.login(loginName, loginPwd);
        if (u != null) {
            return u;
        }
        return null;
    }


    public JsonResult register(String loginName, String userPwd) {
        // TODO Auto-generated method stub
        //JsonResultjsonResult=new JsonResult<>(500,"注册失败");
        User user = new User();
        user.setLoginName(loginName);
        User u = userMapper.selectOne(user);
        if (u == null) {
            User user2 = new User();
            user2.setLoginName(loginName);
            //user2.setUserPwd(getMd5("GG", userPwd));
            userMapper.insertSelective(user2);
        }
        return null;
    }

}
