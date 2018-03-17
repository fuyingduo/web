package com.fuyd.web.service.impl;

import com.fuyd.web.dao.UserMapper;
import com.fuyd.web.entity.User;
import com.fuyd.web.service.IUserService;
import com.fuyd.web.wx.method.GetAccessToken;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service("iUserService")
public class UserServiceImpl implements IUserService{

    @Resource
    private UserMapper iUserDao;

    @Resource
    private GetAccessToken getAccessToken;

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
        JSONObject json = getAccessToken.getToken();
        String accessToken = (String)json.get("access_token");
        Integer expiresIn = (Integer)json.get("expires_in");
        log.info(USER_CLASS + "[findUserById] json:{}", json);
        User user = iUserDao.selectUserById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    public Boolean register(User user) {
        if (user == null) {
            log.error(USER_CLASS + "[register] user is null");
        }

        return false;
    }
}
