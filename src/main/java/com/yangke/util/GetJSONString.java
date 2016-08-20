package com.yangke.util;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by yangke on 16/8/20.
 */
public class GetJSONString {
    public static String getJSONString(int code,String msg) {
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toJSONString();
    }
}
