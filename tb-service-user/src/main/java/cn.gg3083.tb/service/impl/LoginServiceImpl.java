package cn.gg3083.tb.service.impl;

import cn.gg3083.tb.domain.User;
import cn.gg3083.tb.mapper.UserMapper;
import cn.gg3083.tb.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String loginName, String loginPwd) {
        //loginPwd = Md5Util.getMd5("GG", loginPwd);
        System.err.println(loginPwd);
        User u = userMapper.login(loginName, loginPwd);
        if (u != null) {
            return u;
        }
        return null;
    }

}
