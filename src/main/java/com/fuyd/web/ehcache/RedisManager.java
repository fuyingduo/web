package com.fuyd.web.ehcache;

import com.fuyd.web.constant.UserConstant;
import com.fuyd.web.entity.User;
import com.fuyd.web.exception.HandleException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public abstract class RedisManager {

    @Resource
    private RedisTemplate<String, User> redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(RedisManager.class);
    private static final String USER_MANAGE = "[RedisManager]";

    /**
     * 缓存user
     *
     * @param user
     * @throws HandleException
     */
    public void setUser(User user) throws HandleException {
        if (user == null) {
            log.error(USER_MANAGE + "[setUser] user is null");
            throw new HandleException(10001);
        }
        try {
            redisTemplate.opsForValue().set(UserConstant.USER_EHCACHE_ID + user.getUsername(), user);
            log.info(USER_MANAGE + "[setUser] user:{}", redisTemplate.opsForValue().get(UserConstant.USER_EHCACHE_ID + user.getUsername()).toString());
        } catch (Exception e) {
            log.error(USER_MANAGE + "[setUser] error:{}", e.getMessage());
            throw new HandleException(10001);
        }

    }
}
