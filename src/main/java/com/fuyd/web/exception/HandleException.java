package com.fuyd.web.exception;

import com.fuyd.web.listenter.UploadListenter;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.Map;
import java.util.Properties;

@Data
public class HandleException extends Exception {

    private static final String PROFILE = "/Volumes/fuydWork/work/web/src/main/resources/handleException.properties";

    private static final String UNUSUAL = "未知名异常";

    private String message;

    private Integer code;

    private static Map<String, String> map = null;

    static {
        map = UploadListenter.proMap;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public HandleException(Integer code) {
        super();
        this.code = code;
        this.message = gainExceptionMessage(code);
    }

    public HandleException(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public static String gainExceptionMessage(Integer code) {
        String result = null;
        try {
            if (map == null) {
                Properties properties = new Properties();
                InputStream inputStream = new FileInputStream(new File(PROFILE));
                properties.load(inputStream);
                result = properties.getProperty(code.toString());
                if (StringUtils.isEmpty(result)) {
                    result = UNUSUAL;
                }
            } else {
                result = map.get(code.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
