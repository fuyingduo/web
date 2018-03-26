package com.fuyd.web.listenter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

/**
 * created by fuyd 2018-03-26
 */
public class UploadListenter implements ServletContextListener {

    private static final Logger log = LoggerFactory.getLogger(UploadListenter.class);

    private static final String LOAD_CLASS = "[UploadListenter]";

    private static final String URI = "/Volumes/fuydWork/work/web/src/main/resources/handleException.properties";

    public static Map<String, String> proMap = null;

    /**
     * 在没有启动spring之前先加载监听器
     *
     * @param servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        log.info(LOAD_CLASS + "[contextInitialized] listenter start ");
        try {
            proMap = new HashMap<String, String>();
            Properties pro = new Properties();
            FileInputStream in = new FileInputStream(new File(URI));
            pro.load(in);
            for (Object o : pro.keySet()) {
                String key = (String) o;
                log.info(LOAD_CLASS + "[contextInitialized] key:{}", key);
                proMap.put(key, pro.getProperty(key));
            }
        } catch (FileNotFoundException e) {
            log.error(LOAD_CLASS + "[contextInitialized] error:{}", e.getMessage());
        } catch (IOException e) {
            log.error(LOAD_CLASS + "[contextInitialized] error:{}", e.getMessage());
        }

    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
