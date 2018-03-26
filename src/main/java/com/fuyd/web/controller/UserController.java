package com.fuyd.web.controller;

import com.fuyd.web.exception.HandleException;
import com.fuyd.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService iUserService;

    private static final String USER_CLASS = "[UserController]";

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final String KEY = "code";
    private static final String VALUE = "message";

    private static final Integer CODE = 200;
    private static final String MESSAGE = "success";

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "name") String name) {
        ModelAndView model = new ModelAndView();
        try {
            iUserService.register(username, password, name);
            model.addObject(KEY, CODE).addObject(VALUE, MESSAGE).setViewName("/view/reguster-success");
        } catch (HandleException e) {
            model.addObject(KEY, e.getCode()).addObject(VALUE, e.getMessage()).setViewName("/view/register-error");
            log.error(USER_CLASS + "[register] error:{}", e.getMessage());
        }
        return model;
    }

    @RequestMapping(value = "/job", method = RequestMethod.GET)
    public void executeJob() {
        iUserService.executeUserJob();
    }

}
