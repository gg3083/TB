package cn.gg3083.tb.service;

import cn.gg3083.tb.domain.User;

public interface ILoginService {

    User login(String loginName, String loginPwd);
}
