package com.fuyd.web.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserThreadPoolService {

    private static final Logger log = LoggerFactory.getLogger(UserThreadPoolService.class);

    private static final String USER_CLASS = "[UserThreadPoolService]";

    /**
     * 处理流程耗时1000ms
     */
    public void executeJob() {
        log.debug(USER_CLASS + "[executeJob] SRAET:{}", Thread.currentThread());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error(USER_CLASS + "[executeJob] error:{}", e.getMessage());
        }
        log.debug(USER_CLASS + "[executeJob] END:{}", Thread.currentThread());
    }
}
