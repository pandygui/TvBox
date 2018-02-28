package com.wangw.tvbox.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * Created by wangw on 2018/2/28.
 */

public class JsonUtils {

    public static <T> T json2Object(String json, TypeReference typeReference) {
        return (T) JSON.parseObject(json, typeReference.getType());
    }

    public static <T> T json2Object(String json, Class<T> clazz) {
        return (T) JSON.parseObject(json, (Class) clazz);
    }

    public static String object2Json(Object obj) {
        return JSON.toJSONString(obj);
    }

    public static <T> T map2Object(Map map, Class<T> clazz) {
        if (map != null) {
            return json2Object(object2Json(map), clazz);
        }
        return null;
    }
}
