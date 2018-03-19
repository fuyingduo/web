package com.fuyd.web.shiro;

import com.fuyd.web.dao.mapper.UserMapper;
import com.fuyd.web.entity.User;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserMapper userMapper;

    private String password;
    private static final String ENCRYPT = "MD5";
    private static final Integer CYCLE_NUM = 1024;
    private static final String SALT_FIGURE = "ABCDEFGHIZKLMNOPQRSTUVWXYZ0123456789";
    private static final Logger log = LoggerFactory.getLogger(MyRealm.class);
    private static final String REALM_CLASS = "[MyRealm]";

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Object principal = principalCollection.getPrimaryPrincipal();//获取登录的用户名
        if ("admin".equals(principal)) {               //两个if根据判断赋予登录用户权限
            info.addRole("admin");
        }
        if ("user".equals(principal)) {
            info.addRole("list");
        }

        info.addRole("user");

        return info;
    }

    /**
     * 登陆
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1. token 中获取登录的 username! 注意不需要获取password.
        Object principal = token.getPrincipal();
        //2. 利用 username 查询数据库得到用户的信息.
        User user = null;
        user = userMapper.selectUserByUsername((String) principal);
        if (user != null) {
            password = user.getPassword();
        }
        String credentials = password;
        //3.设置盐值 ，（加密的调料，让加密出来的东西更具安全性，一般是通过数据库查询出来的。 简单的说，就是把密码根据特定的东西而进行动态加密，如果别人不知道你的盐值，就解不出你的密码）
        String source = SALT_FIGURE;
        ByteSource credentialsSalt = new Md5Hash(source);
        //当前 Realm 的name
        String realmName = getName();
        //返回值实例化
        SimpleAuthenticationInfo info =
                new SimpleAuthenticationInfo(principal, credentials,
                        credentialsSalt, realmName);
        return info;
    }

    // init-method 配置.
    public void setCredentialMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName(ENCRYPT);//MD5算法加密
        credentialsMatcher.setHashIterations(CYCLE_NUM);//1024次循环加密
        setCredentialsMatcher(credentialsMatcher);
    }

}
