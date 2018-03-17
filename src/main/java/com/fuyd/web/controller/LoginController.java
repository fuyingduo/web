package com.fuyd.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/shiro-login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "/view/login";
        }
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            // 验证操作
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("登陆失败: " + e.getMessage());
            return "/index";
        }
        return "/view/shiro-success";
    }
}
