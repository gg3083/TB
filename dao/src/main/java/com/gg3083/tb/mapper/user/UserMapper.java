package com.gg3083.tb.mapper.user;

import com.gg3083.tb.common.mapper.BaseMapper;
import com.gg3083.tb.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {
    User login(@Param("loginName") String loginName, @Param("loginPwd") String loginPwd);
}