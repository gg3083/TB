package cn.gg3083.tb.mapper;

import cn.gg3083.tb.common.mapper.BaseMapper;
import cn.gg3083.tb.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    User login(@Param("loginName") String loginName, @Param("loginPwd") String loginPwd);

    User getByLoginName(@Param("loginName") String loginName);
}