package com.fuyd.web.controller;

import com.fuyd.web.entity.User;
import com.fuyd.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService iUserService;

    private static final String USER_CLASS = "[UserController]";

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    public String addUser(@RequestParam(value = "name") String name, Model model) {
        if (StringUtils.isEmpty(name)) {
            log.error(USER_CLASS + "[UserController] name:{}", name);
        }
        int i = iUserService.addUser(name);
        log.info(USER_CLASS + "[UserController] i:{}", i);
        model.addAttribute("id", i);
        return "index";
    }

    @RequestMapping(value = "getUser", method = RequestMethod.GET)
    public String getUser(@RequestParam(value = "id") Long id, Model model) {
        if (id == null) {
            log.error(USER_CLASS + "[getUser] id is null");
        }
        User user = iUserService.findUserById(id);
        model.addAttribute("user", user);
        return "index";
    }
}
