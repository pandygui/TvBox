package com.wangw.tvbox.mangguo.database;

import com.wangw.tvbox.net.AbstractRequestParams;

import org.xutils.http.app.ParamsBuilder;

/**
 * Created by wangw on 2018/2/28.
 */

public class MangGuoRequestParams extends AbstractRequestParams {

    public MangGuoRequestParams(String uri) {
        super(uri);
    }

    public MangGuoRequestParams(String uri, ParamsBuilder builder, String[] signs, String[] cacheKeys) {
        super(uri, builder, signs, cacheKeys);
    }

    @Override
    protected void setHeader() {
        setHeader("accept-encoding","gzip, deflate, br");
        setHeader("accept-language","zh-CN,zh;q=0.9,en;q=0.8");
        setHeader("user-agent","Mozilla/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Mobile Safari/537.36");
        setHeader("accept","*/*");
        setHeader("authority","pianku.api.mgtv.com");
        setCharset("GB2312");
    }


}
