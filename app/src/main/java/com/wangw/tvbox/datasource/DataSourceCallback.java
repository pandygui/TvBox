package com.wangw.tvbox.datasource;

import com.wangw.tvbox.net.TvResponse;

/**
 * Created by wangw on 2018/2/28.
 */

public interface DataSourceCallback<T> extends SafeCallback{

    void onError(TvResponse response);

    void onSuccess(TvResponse response,T o);

    void onFinish(TvResponse response);

    void onCancel(TvResponse response);





}
