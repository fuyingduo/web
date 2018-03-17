package com.fuyd.web.wx.method;

import com.fuyd.web.utils.HttpReq;
import com.fuyd.web.wx.entity.WeixParam;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GetAccessToken {

    private static final String url = "https://api.weixin.qq.com/cgi-bin/token";
    private static final String charset = "UTF-8";

    public JSONObject getToken() {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpReq req = new HttpReq();
        map.put("grant_type", "client_credential");
        map.put("appid", WeixParam.appid);
        map.put("secret", WeixParam.token);
        String tokenStr = req.sendGet(url, map, charset);
        JSONObject json = JSONObject.fromObject(tokenStr);
        return json;
    }

}
