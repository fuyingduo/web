package com.fuyd.web.service.impl;

import com.fuyd.web.dao.UserMapper;
import com.fuyd.web.entity.User;
import com.fuyd.web.exception.HandleException;
import com.fuyd.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper iUserDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String USER_CLASS = "[UserServiceImpl]";

    public int addUser(String name) {
        if (StringUtils.isEmpty(name)) {
            log.error(USER_CLASS + "[addUser] name:{}", name);
        }
        User u = new User();
        u.setId(2L);
        u.setName(name);
        int result = 0;
        try {
            result = iUserDao.addUser(u);
        } catch (Exception e) {
            log.error(USER_CLASS + "[addUser] error:{}", e.getMessage());
        }
        return result;
    }

    public User findUserById(Long id) {
        log.error(USER_CLASS + "[findUserById] id:{}", id);
        if (id == null) {
            return null;
        }

        redisTemplate.opsForValue().set("id", "1");
        User user = iUserDao.selectUserById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    public Boolean register(User user) throws HandleException {
        if (user == null) {
            log.error(USER_CLASS + "[register] user is null");
            throw new HandleException(10001);
        }

        return false;
    }

}
