package com.fuyd.web.exception;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Data
public class HandleException extends Exception{

    private static final Logger LOGGER = LoggerFactory.getLogger(HandleException.class);

    private static final String PROFILE = "/Volumes/fuydWork/work/web/src/main/resources/handleException.properties";

    private String message;

    private Integer code;

    private static Map<Integer, String> map = null;

    public HandleException() {
        super();
    }

    public HandleException(Integer code) {
        super();
        this.code = code;
        this.message = gainExceptionMessage(code);
    }

    public static String gainExceptionMessage(Integer code) {
        String result = null;
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(new File(PROFILE));
            properties.load(inputStream);
            result = properties.getProperty(code.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void test() throws HandleException {
        throw new HandleException(10001);
    }

    public static void main(String[] args) {
        HandleException handleException = new HandleException();
    }

    static {
        map = new HashMap<Integer, String>();
        Properties properties = new Properties();
        try {
            InputStream inputStream = new FileInputStream(new File(PROFILE));
            properties.load(inputStream);
            Set<Map.Entry<Object, Object>> set = properties.entrySet();
            for (Map.Entry<Object, Object> objectObjectEntry : set) {
                String key = (String)objectObjectEntry.getKey();
                String value = (String)objectObjectEntry.getValue();
                map.put(Integer.parseInt(key), value);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
