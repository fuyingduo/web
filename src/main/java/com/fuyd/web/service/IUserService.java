package com.fuyd.web.service;

import com.fuyd.web.exception.HandleException;

public interface IUserService {

    /**
     * 添加用户
     *
     * @param username
     * @param password
     * @param name
     * @throws HandleException
     */
    void register(String username, String password, String name) throws HandleException;

    /**
     * 执行job
     *
     * @throws HandleException
     */
    void executeUserJob() throws HandleException;

}
