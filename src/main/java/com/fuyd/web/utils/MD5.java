package com.fuyd.web.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * MD5 加密
 *
 * @author fuyd
 * 2018-03-14
 */
public class MD5 {

    private static final String MD5 = "MD5";

    private static final String B_LOG = "abcdefghizklmnopqrstuvwxyz0123456789";

    /**
     * 加密
     *
     * @param pass
     * @return
     */
    public static String md5(String pass) {
        String saltSource = B_LOG;
        String hashAlgorithmName = MD5;
        Object salt = new Md5Hash(saltSource);
        int hashIterations = 1024;
        Object result = new SimpleHash(hashAlgorithmName, pass, salt, hashIterations);
        String password = result.toString();
        return password;
    }
}
