package com.fuyd.web.service;

import com.fuyd.web.entity.User;

public interface IUserService {

    int addUser(String name);

    User findUserById(Long id);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    Boolean register(User user);

}
