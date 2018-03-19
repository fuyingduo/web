package com.fuyd.web.dao;

import com.fuyd.web.dao.mapper.UserMapper;
import com.fuyd.web.entity.User;
import com.fuyd.web.exception.HandleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Repository
public class UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);
    private static final String USER_CLASS = "[UserDao]";

    @Resource
    private UserMapper userMapper;

    /**
     * 查询用户信息
     *
     * @param username
     * @return user
     * @throws HandleException
     */
    public User findUserByKey(String username) throws HandleException {
        if (StringUtils.isEmpty(username)) {
            throw new HandleException(10001);
        }
        try {
            return userMapper.selectUserByUsername(username);
        } catch (Exception e) {
            LOGGER.error(USER_CLASS + "[findUserByKey] error:{}", e.getMessage());
            throw new HandleException(10001);
        }
    }

    /**
     * 添加用户
     *
     * @param user
     * @throws HandleException
     */
    public User addUser(User user) throws HandleException {
        if (user == null) {
            throw new HandleException(10001);
        }
        try {
            boolean result = userMapper.addUser(user) > 0 ? true : false;
            if (!result) {
                throw new HandleException(10001);
            }
            return user;
        } catch (Exception e) {
            LOGGER.error(USER_CLASS + "[addUser] error:{}", e.getMessage());
            throw new HandleException(10001);
        }
    }
}
