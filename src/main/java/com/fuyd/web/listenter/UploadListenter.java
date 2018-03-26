package com.fuyd.web.listenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * created by fuyd 2018-03-26
 */
public class UploadListenter implements ServletContextListener{

    private static final Logger log = LoggerFactory.getLogger(UploadListenter.class);

    private static final String LOAD_CLASS = "[UploadListenter]";

    /**
     * 在没有启动spring之前先加载监听器
     *
     * @param servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info(LOAD_CLASS + "[contextInitialized] listenter start ");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
