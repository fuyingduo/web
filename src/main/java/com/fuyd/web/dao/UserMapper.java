package com.fuyd.web.dao;

import com.fuyd.web.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 添加用户
    int addUser(User user);

    // 查找用户
    User selectUserById(Long id);

    // 查找用户
    User selectUserByUsername(String name);
}
