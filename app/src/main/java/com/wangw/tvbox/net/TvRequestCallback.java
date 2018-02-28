package com.wangw.tvbox.net;

/**
 * Created by wangw on 2018/2/28.
 */

public interface TvRequestCallback {

    void onError(TvResponse response);

    void onSuccess(TvResponse response);

    void onFinish(TvResponse response);

    void onCancel(TvResponse response);

}
