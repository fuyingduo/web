package com.fuyd.web.controller;

import com.fuyd.web.entity.User;
import com.fuyd.web.exception.HandleException;
import com.fuyd.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @Resource
    private IUserService iUserService;

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
    private static final String REGISTER_CLASS = "[RegisterController]";

    @RequestMapping(value = "/userRegister", method = RequestMethod.POST)
    public String accountRegistration(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, Model model) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            model.addAttribute("msg", "error");
            return "/view/register";
        }
        try {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            iUserService.register(user);
        } catch (HandleException e) {
            log.error(REGISTER_CLASS + "accountRegistration error:{}", e.getMessage());
            return "/view/register-error";
        }
        return "/index";
    }
}
