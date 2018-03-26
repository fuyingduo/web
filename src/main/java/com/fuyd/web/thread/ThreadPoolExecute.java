package com.fuyd.web.thread;

import com.fuyd.web.service.impl.UserThreadPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * created by fuyd 2018/03/26
 */
public class ThreadPoolExecute implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(ThreadPoolExecute.class);
    private static final String TH_CLASS = "[ThreadPoolExecute]";

    @Resource
    private UserThreadPoolService userThreadPoolService;

    public ThreadPoolExecute() {
    }

    public ThreadPoolExecute(UserThreadPoolService userThreadPoolService) {
        this.userThreadPoolService = userThreadPoolService;
    }

    public void run() {
        userThreadPoolService.executeJob();
    }
}
