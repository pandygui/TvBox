package com.wangw.tvbox.net;

import org.xutils.http.RequestParams;
import org.xutils.http.app.ParamsBuilder;

/**
 * Created by wangw on 2018/2/28.
 */

public abstract class AbstractRequestParams extends RequestParams {

    public AbstractRequestParams() {
        setHeader();
    }

    public AbstractRequestParams(String uri) {
        super(uri);
        setHeader();
    }

    public AbstractRequestParams(String uri, ParamsBuilder builder, String[] signs, String[] cacheKeys) {
        super(uri, builder, signs, cacheKeys);
        setHeader();
    }


    protected abstract void setHeader();
}
