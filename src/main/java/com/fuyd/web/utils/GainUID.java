package com.fuyd.web.utils;

import com.fuyd.web.exception.HandleException;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GainUID {

    private static final String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Integer len = 10;
    private static final DateFormat DATE = new SimpleDateFormat("HHmm");
    private static final DateFormat YEARS = new SimpleDateFormat("ddMMyyyy");

    /**
     * 获取随机ID
     *
     * @return
     * @throws HandleException
     */
    public static String getRandomReqNo() throws HandleException {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        if (StringUtils.isEmpty(sb)) {
            throw new HandleException(10001);
        }
        return getTheCurrentDate(sb.toString());
    }

    /**
     * 获取时间戳
     *
     * @param sb
     * @return
     * @throws HandleException
     */
    public static String getTheCurrentDate(String sb) throws HandleException {
        try {
            return YEARS.format(new Date()) + sb + DATE.format(new Date());
        } catch (Exception e) {
            throw new HandleException(10001);
        }
    }
}
