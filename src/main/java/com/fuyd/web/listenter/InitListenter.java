package com.fuyd.web.listenter;

import com.fuyd.web.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
public class InitListenter implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(InitListenter.class);

    private static final String INIT_CLASS = "[InitListenter]";

    @Autowired
    private IUserService iUserService;

    /**
     * 项目启动后加载spring后启动监听
     *
     * @param event
     */
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info(INIT_CLASS + "[onApplicationEvent] listenter start");
    }
}
