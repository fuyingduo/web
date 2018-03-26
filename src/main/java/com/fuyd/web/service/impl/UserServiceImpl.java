package com.fuyd.web.service.impl;

import com.fuyd.web.dao.UserDao;
import com.fuyd.web.ehcache.RedisManager;
import com.fuyd.web.entity.User;
import com.fuyd.web.exception.HandleException;
import com.fuyd.web.service.IUserService;
import com.fuyd.web.thread.ThreadPoolExecute;
import com.fuyd.web.utils.GainUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;


@Service("iUserService")
public class UserServiceImpl extends RedisManager implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private static final String USER_CLASS = "[UserServiceImpl]";

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Resource
    private UserThreadPoolService userThreadPoolService;

    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    public void register(String username, String password, String name) throws HandleException {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(name)) {
            LOGGER.error(USER_CLASS + "[register] username:{}, password:{}, name:{}", username, password, name);
            throw new HandleException(10001);
        }
        String id = GainUID.getRandomReqNo();
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setName(name);
        LOGGER.info(USER_CLASS + "[register] user:{}", user.toString());
        User resultUser = userDao.addUser(user);
        if (resultUser == null) {
            LOGGER.error(USER_CLASS + "[register] resultUser is null");
            throw new HandleException(10001);
        }
        setUser(user);
    }

    public void executeUserJob() {
        executeJob();
    }

    public void executeJob() {
        int num = 20;
        for (int i = 0; i < num; i++) {
            taskExecutor.execute(new ThreadPoolExecute(userThreadPoolService));
            LOGGER.info(USER_CLASS + "[executeJob] ow threadpool active threads totalnum:{}", taskExecutor.getActiveCount());
        }
        try {
            System.in.read();
        } catch (IOException e) {
            LOGGER.error(USER_CLASS + "[executeJob] error:{}", e.getMessage());
        }

    }
}
