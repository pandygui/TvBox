package com.wangw.tvbox.net;

import org.xutils.common.Callback;
import org.xutils.x;

/**
 * Created by wangw on 2018/2/28.
 */

public class HttpHelp {

    public static Callback.Cancelable post(final AbstractRequestParams requestParams, final TvRequestCallback callback){
        return x.http().post(requestParams, new Callback.CommonCallback<String>() {
            private TvResponse mResponse = new TvResponse(requestParams);
            @Override
            public void onSuccess(String result) {
                mResponse.setResult(result);
                callback.onSuccess(mResponse);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mResponse.setError(ex);
                callback.onError(mResponse);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onCancel(mResponse);
            }

            @Override
            public void onFinished() {
                callback.onFinish(mResponse);
            }
        });
    }

    public static Callback.Cancelable get(final AbstractRequestParams requestParams, final TvRequestCallback callback){
        return x.http().get(requestParams, new Callback.CommonCallback<String>() {
            private TvResponse mResponse = new TvResponse(requestParams);
            @Override
            public void onSuccess(String result) {
                mResponse.setResult(result);
                callback.onSuccess(mResponse);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                mResponse.setError(ex);
                callback.onError(mResponse);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onCancel(mResponse);
            }

            @Override
            public void onFinished() {
                callback.onFinish(mResponse);
            }
        });
    }

}
