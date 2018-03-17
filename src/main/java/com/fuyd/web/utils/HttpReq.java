package com.fuyd.web.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * http request
 *
 * @author fuyd
 * 2018-03-13
 */
public class HttpReq {


    /**
     * get
     *
     * @param urlParam
     * @param params
     * @param charset
     * @return
     */
    public String sendGet(String urlParam, Map<String, Object> params, String charset) {
        StringBuffer resultBuffer = null;
        // 拼接字段
        StringBuffer sbParams = setParams(params);
        HttpURLConnection con = null;
        BufferedReader br = null;
        // url与拼接好的字段连接在一起
        try {
            URL url = null;
            if (sbParams != null && sbParams.length() > 0) {
                url = new URL(urlParam + "?" + sbParams.substring(0, sbParams.length() - 1));
            } else {
                url = new URL(urlParam);
            }
            // 打开连接
            con = (HttpURLConnection) url.openConnection();
            // 设置请求头
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 开启连接
            con.connect();
            // 将返回的数据转成StringBuffer对象
            resultBuffer = new StringBuffer();
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
            String temp;
            while ((temp = br.readLine()) != null) {
                resultBuffer.append(temp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeBr(con, br);
        }
        return resultBuffer.toString();
    }

    /**
     * post
     *
     * @param urlParam
     * @param params
     * @param charset
     * @return
     */
    public String sendPost(String urlParam, Map<String, Object> params, String charset) {

        StringBuffer resultBuffer = null;
        // 拼接字段
        StringBuffer sbParams = setParams(params);
        HttpURLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        // 设置请求相应的参数
        try {
            URL url = new URL(urlParam);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (sbParams != null && sbParams.length() > 0) {
                osw = new OutputStreamWriter(con.getOutputStream(), charset);
                osw.write(sbParams.substring(0, sbParams.length() - 1));
                osw.flush();
            }
            // 将返回的数据转成StringBuffer对象
            resultBuffer = new StringBuffer();
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
            if (contentLength > 0) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeOsw(con, osw);
            closeBr(con, br);
        }
        return resultBuffer.toString();
    }

    /**
     * post
     *
     * @param urlParam
     * @param Json
     * @param charset
     * @return
     */
    public String sendPost(String urlParam, String Json, String charset) {
        StringBuffer resultBuffer = null;
        HttpURLConnection con = null;
        OutputStreamWriter osw = null;
        BufferedReader br = null;
        // 发送请求
        try {
            URL url = new URL(urlParam);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setUseCaches(false);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            if (Json != null && Json.length() > 0) {
                osw = new OutputStreamWriter(con.getOutputStream(), charset);
                osw.write(Json);
                osw.flush();
            }
            // 读取返回内容
            resultBuffer = new StringBuffer();
            int contentLength = Integer.parseInt(con.getHeaderField("Content-Length"));
            if (contentLength > 0) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream(), charset));
                String temp;
                while ((temp = br.readLine()) != null) {
                    resultBuffer.append(temp);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeOsw(con, osw);
            closeBr(con, br);
        }

        return resultBuffer.toString();
    }

    /**
     * set params
     *
     * @param params
     * @return
     */
    private StringBuffer setParams(Map<String, Object> params) {
        StringBuffer sbParams = new StringBuffer();
        if (params != null && params.size() > 0) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sbParams.append(entry.getKey());
                sbParams.append("=");
                sbParams.append(entry.getValue());
                sbParams.append("&");
            }
        }
        return sbParams;
    }

    /**
     * 关闭IO
     *
     * @param con
     * @param osw
     */
    private void closeOsw(HttpURLConnection con, OutputStreamWriter osw) {
        if (osw != null) {
            try {
                osw.close();
            } catch (IOException e) {
                osw = null;
                throw new RuntimeException(e);
            } finally {
                if (con != null) {
                    con.disconnect();
                    con = null;
                }
            }
        }
    }

    /**
     * 关闭IO
     *
     * @param con
     * @param br
     */
    private void closeBr(HttpURLConnection con, BufferedReader br) {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                br = null;
                throw new RuntimeException(e);
            } finally {
                if (con != null) {
                    con.disconnect();
                    con = null;
                }
            }
        }
    }
}
