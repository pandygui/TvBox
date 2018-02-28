package com.wangw.tvbox.datasource;

import com.alibaba.fastjson.TypeReference;
import com.wangw.tvbox.net.AbstractRequestParams;
import com.wangw.tvbox.net.HttpHelp;
import com.wangw.tvbox.net.JsonRequestCallback;
import com.wangw.tvbox.net.TvRequestCallback;
import com.wangw.tvbox.net.TvResponse;

import org.xutils.common.Callback;

/**
 * Created by wangw on 2018/2/28.
 */

public abstract class BaseDataSource {


    protected Callback.Cancelable get(AbstractRequestParams requestParams, TvRequestCallback callback){
        return HttpHelp.get(requestParams,callback);
    }

    protected Callback.Cancelable getJson(String url, final BaseDataSourceCallback callback, TypeReference type){
        return getJson(getRequestParams(url), callback,type);
    }

    protected Callback.Cancelable getJson(AbstractRequestParams requestParams, final BaseDataSourceCallback callback, final TypeReference type){
        return get(requestParams,new JsonRequestCallback() {
            @Override
            protected void onSuccess(TvResponse response, Object obj) {
                if (callback != null && !callback.isCancel())
                    callback.onSuccess(response,obj);
            }

            @Override
            protected TypeReference getJsonClz() {
                return type;
            }

            @Override
            public void onError(TvResponse response) {
                if (callback != null && !callback.isCancel())
                    callback.onError(response);
            }

            @Override
            public void onFinish(TvResponse response) {
                if (callback != null && !callback.isCancel())
                    callback.onFinish(response);
            }

            @Override
            public void onCancel(TvResponse response) {
                if (callback != null && !callback.isCancel())
                    callback.onCancel(response);
            }
        });
    }

    protected abstract AbstractRequestParams getRequestParams(String url);


}
